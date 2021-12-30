package xx.yy.qian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import xx.yy.qian.PreActivity.debug
import xx.yy.qian.databinding.ActivityLinBinding
import xx.yy.qian.databinding.FragmentTypeEditBinding

class LinActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val activityLinBinding = ActivityLinBinding.inflate(layoutInflater)
    setContentView(activityLinBinding.root)
    supportFragmentManager
      .beginTransaction()
      .replace(activityLinBinding.frameLayout.id, TypeEditFragment(), "zz")
      .commit()
  }
}