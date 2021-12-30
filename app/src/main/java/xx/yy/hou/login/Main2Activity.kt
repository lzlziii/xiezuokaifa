package xx.yy.hou.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xx.yy.hou.lz.util.SerializeUtils
import xx.yy.hou.lz.util.debug
import xx.yy.hou.service.MyService
import xx.yy.hou.service.MyService999
import xx.yy.qian.R
import xx.yy.qian.databinding.ActivityMain2Binding
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

class Main2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val activityMain2Binding = ActivityMain2Binding.inflate(layoutInflater)
    setContentView(activityMain2Binding.root)

    try {

      val output = openFileOutput("data", Context.MODE_PRIVATE)
      val writer = BufferedWriter(OutputStreamWriter(output))

      val data = SerializeUtils.serialize(

        ArrayList<Int>().apply {
          this.add(123)
          this.add(13)
          this.add(11111)
        }

      )

      writer.use {
        debug(data + " :: " + data.length)
        it.write(data)
      }
    } catch (e: IOException) {
      e.printStackTrace()
    }

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