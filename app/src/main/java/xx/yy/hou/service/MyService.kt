package xx.yy.hou.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Binder
import android.util.Log


class MyService : Service() {
  var data: MutableList<String?> = ArrayList()

  inner class LocalBinder : Binder() {
    val service: MyService
      get() = this@MyService

    fun getData(): List<String?> {
      return data
    }
  }

  private val localBinder: IBinder = LocalBinder()
  override fun onBind(intent: Intent): IBinder? {
    val s = intent.getStringExtra("data")
    Log.i(TAG, "onBind: 接收 $s")
    data.add(s)
    return localBinder
  }

  companion object {
    private const val TAG = "MyService"
  }

  init {
    data.add("This is some msg from $TAG")
  }
}