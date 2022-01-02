package xx.yy.qian.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import xx.yy.qian.R
import xx.yy.qian.databinding.ActivityMainBinding
import xx.yy.qian.fragment.TypeFragment
import xx.yy.qian.fragment.WorkFragment

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)

    supportActionBar?.hide()

    supportFragmentManager
      .beginTransaction()
      .replace(R.id.frameLayout, WorkFragment())
      .commit()

    activityMainBinding.bnv.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.wm -> {
          supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, WorkFragment())
            .commit()
        }
        R.id.tm -> {
          supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, TypeFragment())
            .commit()

        }
        R.id.tj -> {

        }
      }
      true
    }
  }
}