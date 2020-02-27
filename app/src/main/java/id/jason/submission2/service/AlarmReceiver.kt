package id.jason.submission2.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import id.jason.submission2.R
import id.jason.submission2.connection.RetrofitService
import id.jason.submission2.helper.Constants.API.API_KEY
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.ShowsResponse
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class AlarmReceiver : BroadcastReceiver() {

    private var listResponse: List<ShowsDetail>? = listOf()
    private var title=""
    private var notifId=0
    private var message=""

    companion object {
        const val TYPE_RELEASE = "ReleaseAlarm"
        const val TYPE_NEW = "NewAlarm"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"
        // Siapkan 2 id untuk 2 macam alarm, onetime dan repeating
        private const val ID_RELEASE = 101
        private const val ID_NEW = 102
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val type = intent.getStringExtra(EXTRA_TYPE)
        var title=""
        var notifId=0
        var message=""
        if (type == TYPE_RELEASE) {
            message = intent.getStringExtra(EXTRA_MESSAGE)
            title = TYPE_RELEASE
            notifId = ID_RELEASE
            showAlarmNotification(context, title, message, notifId)
        }
        else if(type == TYPE_NEW){
            val cDate = Date()
            val stringDate = SimpleDateFormat("yyyy-MM-dd").format(cDate)
            title = TYPE_NEW
            notifId = ID_NEW
            getData(stringDate, stringDate, title, notifId, context)
        }
    }

    fun cancelAlarm(context: Context, type: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val requestCode = if (type.equals(TYPE_RELEASE, ignoreCase = true)) ID_RELEASE else ID_NEW
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "Repeating alarm dibatalkan", Toast.LENGTH_SHORT).show()
    }

    fun setRepeatingAlarm(context: Context, time: String, type: String, message: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        val putExtra = intent.putExtra(EXTRA_TYPE, type)
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            if (type.equals(TYPE_RELEASE, ignoreCase = true)) ID_RELEASE else ID_NEW,
            intent,
            0
        )
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show()
    }

    private fun showAlarmNotification(
        context: Context,
        title: String,
        message: String,
        notifId: Int
    ) {

        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"

        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite_red_48dp)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)

    }

    private fun getData(
        gteDate: String,
        lteDate: String,
        title: String,
        notifId: Int,
        context: Context
    ){
        RetrofitService().api().newRelease(API_KEY, gteDate, lteDate)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ShowsResponse>,
                    response: Response<ShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        listResponse = response.body()!!.results
                        var hasil=""
                        for (i in 0 until listResponse?.size!!-1)
                        hasil += (listResponse!![i].showTitle + " ")
                        showAlarmNotification(context, title, hasil, notifId)
                    }
                }
            })
    }
}
