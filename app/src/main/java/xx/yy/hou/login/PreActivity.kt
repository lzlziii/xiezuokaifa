package xx.yy.hou.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import xx.yy.hou.lz.aaa.Utils
import xx.yy.hou.lz.aaa.saveAll
import xx.yy.hou.service.MyService999
import xx.yy.qian.activity.MainActivity
import xx.yy.qian.databinding.ActivityMain2Binding
import xx.yy.qian.databinding.ActivityPreBinding

class PreActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy)

    val activityPreBinding = ActivityPreBinding.inflate(layoutInflater)
    setContentView(activityPreBinding.root)

    activityPreBinding.loginButton.setOnClickListener {
      if (Utils.check(activityPreBinding.inputText1.text.toString(), activityPreBinding.inputText2.text.toString()) == false) {
        Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show()
      } else {
        startActivity(Intent(this, MainActivity::class.java))
      }
    }

  }
}