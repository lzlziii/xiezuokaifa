package xx.yy.qian.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xx.yy.qian.R

class AddWorkActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.dialog_add_work)
    supportActionBar?.hide()
  }
}