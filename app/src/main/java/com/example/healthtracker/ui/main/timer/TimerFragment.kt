package com.example.healthtracker.ui.main.timer

import com.example.healthtracker.AlarmReceiver
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthtracker.databinding.FragmentTimerBinding
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.core.widget.doOnTextChanged
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

const val ALARM_REQUEST_CODE = 101
const val ALARM_CHANNEL_ID = "RECIPE_ALARM_CHANNEL_ID";
const val ALARM_CHANNEL_NAME = "Recipe Alarm";

val MINUTE = 5000L

class TimerFragment : Fragment() {
    lateinit var countdownTimer: CountDownTimer
    lateinit var alarmManager: AlarmManager
    lateinit var binding: FragmentTimerBinding;

    var isRunning: Boolean = false;
    var timeRemaining = 1 * MINUTE;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        binding.button.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        binding.reset.setOnClickListener {
            resetTimer()
        }

        binding.timeEditText.doOnTextChanged { text, _, _, _ ->
            timeRemaining = (text.toString().toLongOrNull() ?: 1) * MINUTE
        }

        return binding.root
    }

    private fun pauseTimer() {
        binding.button.text = "Continue"
        countdownTimer.cancel()
        cancelAlarm()
        isRunning = false
    }

    private fun startTimer() {
        countdownTimer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onFinish() {
                timeRemaining = 0;
                updateTextUI()
                loadConfetti()
            }

            override fun onTick(p0: Long) {
                Log.d("qwe", "tik $p0")
                timeRemaining = p0;
                updateTextUI()
            }
        }

        countdownTimer.start()
        startAlarm(timeRemaining)

        isRunning = true
        binding.button.text = "Pause"
        binding.reset.visibility = View.VISIBLE
    }

    private fun resetTimer() {
        countdownTimer.cancel()
        cancelAlarm()
        isRunning = false
        timeRemaining = getTimeToCountMillis();
        updateTextUI()
        binding.button.text = "Start"
        binding.reset.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minute = (timeRemaining / 1000) / 60
        val seconds = (timeRemaining / 1000) % 60

        binding.timer.text = "$minute:$seconds"
    }

    private fun loadConfetti() {
        binding.viewKonfetti.start(
            Party(
                speed = 0f,
                maxSpeed = 30f,
                damping = 0.9f,
                spread = 360,
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                position = Position.Relative(0.5, 0.3),
                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
            )
        )
    }


    private fun getTimeToCountMillis(): Long {
        return (binding.timeEditText.text.toString().toLongOrNull() ?: 1) * MINUTE;
    }

    private fun startAlarm(interval: Long) {
        val alarmIntent = Intent(
            context,
            AlarmReceiver::class.java
        ).setAction("com.example.healthtracker.alarm_notification")

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ALARM_REQUEST_CODE,
            alarmIntent,
            FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        Log.d("qwe", "pending intent initiated");
        alarmManager.setExact(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval,
            pendingIntent
        )
    }

    fun cancelAlarm() {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getService(
                context,
                ALARM_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_NO_CREATE
            )

        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
        }
    }
}