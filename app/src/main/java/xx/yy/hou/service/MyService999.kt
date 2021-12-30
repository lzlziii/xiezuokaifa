package xx.yy.hou.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

class MyService999 : Service() {

  class TT : TimerTask() {
    override fun run() {
      Log.e("asdf", "ssssssss")

    }
  }

  override fun onCreate() {
    Timer().schedule(TT(), 1000, 2000)
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.e("asdf", "结束")
  }
}

