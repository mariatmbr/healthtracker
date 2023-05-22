package com.example.healthtracker

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.healthtracker.ui.main.MainActivity
import com.example.healthtracker.ui.main.timer.ALARM_CHANNEL_ID
import com.example.healthtracker.ui.main.timer.ALARM_CHANNEL_NAME
import com.example.healthtracker.ui.main.timer.ALARM_REQUEST_CODE

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("qwe", "received broadcast")
        if (intent != null && context != null) {
            createNotificationChannel(context)
            createNotification(context)
        }
    }

    private fun createNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(
                context,
                ALARM_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val builder =
            NotificationCompat.Builder(context, ALARM_CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Recipe Timer").setContentText("Your time is up!")
                .setPriority(NotificationCompat.PRIORITY_MAX).setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("qwe", "Show notification")
                val notificationId = (System.currentTimeMillis() / 1000).toInt()
                notify(notificationId, builder.build())
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    ALARM_CHANNEL_ID,
                    ALARM_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}