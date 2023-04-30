package com.example.healthtracker.ui.main.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthtracker.databinding.FragmentTimerBinding
import android.graphics.Color
import android.os.CountDownTimer
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import nl.dionsegijn.konfetti.core.models.Size
import java.util.concurrent.TimeUnit

class TimerFragment : Fragment() {

    var START_MILLI_SECONDS = 60000L
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;
    var time_in_milli_seconds = 0L

    private var _binding: FragmentTimerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.button.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            } else {
                val time = binding.timeEditText.text.toString()
                time_in_milli_seconds = (time.toLongOrNull() ?: 1) * 60000L
                START_MILLI_SECONDS = time_in_milli_seconds
                startTimer(time_in_milli_seconds)
            }
        }

        binding.reset.setOnClickListener {
            resetTimer()
        }
        return root
    }

    private fun pauseTimer() {

        binding.button.text = "Start"
        countdown_timer.cancel()
        isRunning = false
        binding.reset.visibility = View.VISIBLE
    }

    private fun startTimer(time_in_seconds: Long) {
        countdown_timer = object : CountDownTimer(time_in_seconds, 1000) {
            override fun onFinish() {
                time_in_milli_seconds = 0
                updateTextUI()
                loadConfeti()
            }

            override fun onTick(p0: Long) {
                time_in_milli_seconds = p0
                updateTextUI()
            }
        }
        countdown_timer.start()

        isRunning = true
        binding.button.text = "Pause"
        binding.reset.visibility = View.INVISIBLE

    }

    private fun resetTimer() {
        time_in_milli_seconds = START_MILLI_SECONDS
        updateTextUI()
        binding.reset.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minute = (time_in_milli_seconds / 1000) / 60
        val seconds = (time_in_milli_seconds / 1000) % 60

        binding.timer.text = "$minute:$seconds"
    }

    private fun loadConfeti() {
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}