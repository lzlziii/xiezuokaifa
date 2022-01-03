package xx.yy.qian.activity

import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import xx.yy.hou.lz.aaa.LX
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.func.addLx
import xx.yy.hou.lz.func.getLxById
import xx.yy.hou.lz.func.removeLxById
import xx.yy.hou.lz.func.renameLx
import xx.yy.hou.lz.util.debug
import xx.yy.qian.R
import xx.yy.qian.databinding.*
import xx.yy.qian.fragment.TypeFragment
import xx.yy.qian.fragment.WorkFragment
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

  private val typeList = ArrayList<LeiXing>().apply { this.add(getLxById(0)) }

  private fun refreshPage(page: Int) {
    if (page == 1) {
      supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TypeFragment(this, typeList)).commit()
    }
  }

  fun backToIndex(index: Int) {
    while (typeList.size > index + 1) {
      typeList.removeAt(typeList.size - 1)
    }
    refreshPage(1)
  }

  fun pushLx(lx: LeiXing) {
    typeList.add(lx)
    refreshPage(1)
  }

  private var askId = -1L

  lateinit var alertRemoveLx: Dialog

  fun askDropLx(id: Long) {
    alertRemoveLx.show()
    askId = id
  }

  private fun doDropLx() {
    removeLxById(askId)
    refreshPage(1)
  }

  lateinit var alertRenameLx: Dialog

  fun askRenameLx(id: Long) {
    alertRenameLx.show()
    askId = id
  }

  fun doRenameLx(name: String) {
    renameLx(askId, name)
    refreshPage(1)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)

    supportActionBar?.hide()

    // 刚开始加载第一页
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
          supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TypeFragment(this, typeList)).commit()
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
    val dialogRenameBinding = DialogRenameBinding.inflate(layoutInflater)

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
      .setPositiveButton("确定") { _, _ ->
        val name = dialogTypeBinding.editLxName.text.toString().trim()
        if (name.isEmpty()) {
          Toast.makeText(this, "类型名称不合法", Toast.LENGTH_SHORT).show()
        } else {
          addLx(typeList.last().id, name)
          refreshPage(1)
        }
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    alertRemoveLx = builder.setTitle("确定级联删除所有类型和事务?")
      .setView(null)
      .setPositiveButton("确定") { _, _ ->
        debug("jin que ding")
        doDropLx()
      }
      .setNegativeButton("取消") { _, _ ->
      }
      .create()

    alertRenameLx = builder.setTitle("类型重新命名")
      .setView(dialogRenameBinding.root)
      .setPositiveButton("确定") { _, _ ->
        val name = dialogRenameBinding.lxReName.text.toString().trim()
        if (name.isEmpty()) {
          Toast.makeText(this, "类型名称不合法", Toast.LENGTH_SHORT).show()
        } else {
          doRenameLx(name)
        }
      }
      .setNegativeButton("取消") { _, _ ->
      }
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