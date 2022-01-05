package xx.yy.hou.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import xx.yy.hou.lz.aaa.loadAll
import xx.yy.hou.lz.aaa.saveAll
import xx.yy.hou.lz.util.debug
import java.util.*

class MyService999 : Service() {

  class TT : TimerTask() {
    override fun run() {
      debug("周期性事件")
    }
  }

  override fun onCreate() {
//    loadAll(this)
    debug("999服务创建了")
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  override fun onDestroy() {
//    saveAll(this)
    super.onDestroy()
    debug("999服务没了")
  }
}

