package xx.yy.qian.activity

import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.func.getLxById
import xx.yy.hou.lz.util.debug
import xx.yy.qian.R
import xx.yy.qian.databinding.ActivityMainBinding
import xx.yy.qian.databinding.DialogTimeBinding
import xx.yy.qian.databinding.DialogTypeBinding
import xx.yy.qian.databinding.DialogWorkBinding
import xx.yy.qian.fragment.TypeFragment
import xx.yy.qian.fragment.WorkFragment
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object TypeList {
  val typeList = ArrayList<LeiXing>().apply { this.add(getLxById(0)!!) }

  fun popToId(id: Long) {
  }

  fun pushLx() {
  }
}

class MainActivity : AppCompatActivity() {

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)

    supportActionBar?.hide()

    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, WorkFragment()).commit()

    var page = 0

    activityMainBinding.bnv.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.wm -> {
          page = 0
          supportFragmentManager.beginTransaction().replace(R.id.frameLayout, WorkFragment()).commit()
        }
        R.id.tm -> {
          page = 1
          debug("page to 1")
          supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TypeFragment(TypeList.typeList)).commit()
        }
        R.id.tj -> {
          page = 2
        }
      }
      true
    };

    val dialogWorkBinding = DialogWorkBinding.inflate(layoutInflater)
    val dialogTimeBinding = DialogTimeBinding.inflate(layoutInflater)
    val dialogTypeBinding = DialogTypeBinding.inflate(layoutInflater)

    val time = ArrayList<Int>().apply {
      SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH).format(Date()).split(' ').forEach { s ->
        this.add(s.toInt())
      }
    }

    dialogTimeBinding.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
      arrayListOf(year, monthOfYear, dayOfMonth).forEachIndexed { index, i ->
        time[index] = i
      }
      ++time[1]
    }

    dialogTimeBinding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
      arrayListOf(hourOfDay, minute).forEachIndexed { index, i ->
        time[3 + index] = i
      }
    }

    // 编辑的开始时间还是结束时间
    var isSt = true

    val builder = AlertDialog.Builder(this)

    val alertWork = builder.setTitle("编辑事务")
      .setView(dialogWorkBinding.root)
      .setPositiveButton("确定") { _, _ ->
      }
      .setNegativeButton("取消") { _: DialogInterface?, _: Int -> Log.e("asdf", "jin22") }
      .create()


    val alertTime = builder.setTitle("编辑时间")
      .setView(dialogTimeBinding.root)
      .setPositiveButton("确定") { _, _ ->
        if (isSt) {
          dialogWorkBinding.timeSt.setText(StringBuilder().apply {
            time.forEachIndexed { index, i ->
              this.append(i)
              this.append("年月日时分秒"[index])
            }
          }.toString())
        } else {
          dialogWorkBinding.timeEd.setText(StringBuilder().apply {
            time.forEachIndexed { index, i ->
              this.append(i)
              this.append("年月日时分秒"[index])
            }
          }.toString())
        }
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    val alertType = builder.setTitle("编辑类型")
      .setView(dialogTypeBinding.root)
      .setPositiveButton("确定") { _, _ -> }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    activityMainBinding.tianjiashiwu.setOnClickListener {
      if (page == 0) alertWork.show()
      else if (page == 1) alertType.show()
    }

    dialogWorkBinding.setst.setOnClickListener {
      isSt = true
      alertTime.show()
    }

    dialogWorkBinding.seted.setOnClickListener {
      isSt = false
      alertTime.show()
    }

  }
}