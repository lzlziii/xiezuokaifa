package xx.yy.hou.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import xx.yy.hou.lz.dispatch.autoDispatch
import xx.yy.qian.R
import xx.yy.qian.activity.MainActivity
import kotlin.concurrent.thread

class MyService999999 : Service() {

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  lateinit var timeChangeReceiver: TimeChangeReceiver

  override fun onCreate() {
    super.onCreate()
    Log.e("MyService", "onCreate executed")
    val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel("my_service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
      manager.createNotificationChannel(channel)
    }
    val intent = Intent(this, MainActivity::class.java)
    val pi = PendingIntent.getActivity(this, 0, intent, 0)
    val notification = NotificationCompat.Builder(this, "my_service")
      .setContentTitle("shi jian guan li da shi")
      .setContentText("shi jian guan li ing")
      .setSmallIcon(R.drawable.ic_launcher_foreground)
      .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_foreground))
      .setContentIntent(pi)
      .build()
    startForeground(1, notification)

    timeChangeReceiver = TimeChangeReceiver()

    registerReceiver(timeChangeReceiver, IntentFilter().apply {
      addAction("android.intent.action.TIME_TICK")
    })
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    Log.d("MyService", "onStartCommand executed")
    thread {
      // 处理具体的逻辑
      stopSelf()
    }
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(timeChangeReceiver)
    Log.e("MyService", "onDestroy executed")
  }

  inner class TimeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
      Log.e("MyService", "时间改变了")
//      Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
      autoDispatch()
    }
  }

}