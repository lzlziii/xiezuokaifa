package xx.yy.hou.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xx.yy.hou.lz.aaa.saveAll
import xx.yy.hou.service.MyService999
import xx.yy.qian.databinding.ActivityMain2Binding

class Main2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val activityMain2Binding = ActivityMain2Binding.inflate(layoutInflater)
    setContentView(activityMain2Binding.root)

    saveAll(this)

    activityMain2Binding.startServiceBtn.setOnClickListener {
      val intent = Intent(this, MyService999::class.java)
      startService(intent)
    }

    activityMain2Binding.stopServiceBtn.setOnClickListener {
      val intent = Intent(this, MyService999::class.java)
      stopService(intent)
    }
  }
}