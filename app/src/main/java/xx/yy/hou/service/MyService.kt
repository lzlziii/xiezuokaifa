package xx.yy.hou.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
  override fun onCreate() {
    super.onCreate()
    Log.e(TAG, "onCreate")
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

    Log.e(TAG, "onStartCommand")
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.e(TAG, "onDestroy")
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  companion object {
    const val TAG = "SimpleService"
  }
}