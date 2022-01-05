package xx.yy.qian.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.define.Period
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.queue.*
import xx.yy.hou.lz.util.debug
import xx.yy.hou.lz.util.parseDate
import xx.yy.hou.service.MyService
import xx.yy.hou.service.MyService999999
import xx.yy.qian.R
import xx.yy.qian.databinding.*
import xx.yy.qian.fragment.MyAdapter
import xx.yy.qian.fragment.PeriodAdapter
import xx.yy.qian.fragment.TypeFragment
import xx.yy.qian.fragment.WorkFragment
import java.lang.Exception
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

  private val periodList = ArrayList<Period>()

  private val typeList = ArrayList<LeiXing>().apply { this.add(getLxById(0)) }
  private val singleJob = SingleJob(-1, -1, "事务", "", 5L, Date(), Date(), 30L)
  var whichQueue = 0 // 第一个页面当前显示哪个队列

  fun showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
  }

  // 检查信息是否正确
  private fun checkSingleJobInfo(): Boolean {
    singleJob.name = singleJob.name.trim()
    singleJob.apply {
      if (name.isEmpty()) return false
      if (singleJob.priority < 0) return false
      if (singleJob.st.after(singleJob.ed)) return false
      if (singleJob.alarm < 0) return false
    }
    return true
  }

  // 当前显示的是哪一个事务
  fun setCurrentJobText() {
    currentJob.text = arrayListOf("周期性事务", "未开始的普通事务", "即将开始事务", "正在进行事务", "已完成事务", "暂停的周期性事务")[whichQueue]
  }

  private fun hideAll() {
    buttonAdd.visibility = View.INVISIBLE
    currentJob.visibility = View.INVISIBLE
    sortType.visibility = View.INVISIBLE
  }

  private lateinit var workFragment: WorkFragment

  @SuppressLint("SetTextI18n")
  private fun loadPage(page: Int) {
    hideAll()
    if (page == 0) {
      currentJob.visibility = View.VISIBLE
      sortType.visibility = View.VISIBLE
      setCurrentJobText()
      workFragment = WorkFragment(-1, this)
      supportFragmentManager.beginTransaction().replace(R.id.frameLayout, workFragment).commit()
    } else if (page == 1) {
      buttonAdd.visibility = View.VISIBLE
      supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TypeFragment(this, typeList)).commit()
    }
  }

  // 栈返回
  fun backToIndex(index: Int) {
    while (typeList.size > index + 1) {
      typeList.removeAt(typeList.size - 1)
    }
    loadPage(1)
  }

  // 压栈
  fun pushLx(lx: LeiXing) {
    typeList.add(lx)
    loadPage(1)
  }

  private var askId = -1L

  lateinit var alertRemoveLx: Dialog

  // 删除类型前询问
  fun askDropLx(id: Long) {
    alertRemoveLx.show()
    askId = id
  }

  // 确定按钮点击后删除类型
  @RequiresApi(Build.VERSION_CODES.N)
  private fun doDropLx() {
    removeLxById(askId)
    loadPage(1)
  }

  lateinit var alertRenameLx: Dialog

  // 询问重新命名类型
  fun askRenameLx(id: Long) {
    alertRenameLx.show()
    askId = id
  }

  // 重新命名类型
  private fun doRenameLx(name: String) {
    renameLx(askId, name)
    loadPage(1)
  }

  lateinit var builder: AlertDialog.Builder

  // 用于第一个页面更新信息
  fun updateJob(id: Long) {
    debug("未实现")
  }

  lateinit var buttonAdd: Button
  lateinit var currentJob: TextView
  private lateinit var sortType: Button

  lateinit var alertJobOperation: Dialog

  private val connection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName, service: IBinder) {
    }
    override fun onServiceDisconnected(name: ComponentName) {
    }
  }
  @SuppressLint("SetTextI18n")
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)

    val intent = Intent(this, MyService999999::class.java)
    bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service

    buttonAdd = activityMainBinding.add
    currentJob = activityMainBinding.currentJob
    sortType = activityMainBinding.sortType

    val dialogTimeBinding = DialogTimeBinding.inflate(layoutInflater)
    val dialogTypeBinding = DialogTypeBinding.inflate(layoutInflater)
    val dialogRenameBinding = DialogRenameBinding.inflate(layoutInflater)
    val dialogAddWorkBinding = DialogAddWorkBinding.inflate(layoutInflater)
    val dialogAddWorkPeroidBinding = DialogAddWorkPeroidBinding.inflate(layoutInflater)
    val dialogSortBinding = DialogSortBinding.inflate(layoutInflater)
    val dialogPeroidBinding = DialogPeroidBinding.inflate(layoutInflater)

    startService(Intent(this, MyService::class.java))

    supportActionBar?.hide()

    // 刚开始加载第一页
    loadPage(0)

    // 底部三个按钮
    activityMainBinding.bnv.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.wm -> loadPage(0)
        R.id.tm -> loadPage(1)
        R.id.tj -> {}
      }
      true
    }

    builder = AlertDialog.Builder(this)

    alertJobOperation = builder.create()

    val alertSort = builder.setTitle("选择排序方式")
      .setView(dialogSortBinding.root)
      .setPositiveButton("关闭") { _, _ -> }
      .create()

    dialogSortBinding.also {
      arrayListOf(it.sortTimeSt, it.sortTimeStInv, it.sortTimeEd, it.sortTimeEdInv, it.sortPriority, it.sortPriorityInv).forEachIndexed { index, textView ->
        textView.setOnClickListener {
          workFragment.resetAdapter(index)
          alertSort.hide()
        }
      }
    }

    // 排序方式点击后
    activityMainBinding.sortType.setOnClickListener {
      alertSort.show()
    }

    fun setNowTime(x: ArrayList<Int>) {
      x.apply {
        clear()
        SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH).format(Date()).split(' ').forEach { s ->
          this.add(s.toInt())
        }
      }
    }

    // 刚开始的时间
    val time = ArrayList<Int>().apply {
      setNowTime(this)
    }

    // 更改年月日
    dialogTimeBinding.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
      arrayListOf(year, monthOfYear, dayOfMonth).forEachIndexed { index, i ->
        time[index] = i
      }
      ++time[1] // 离谱,月份是从零开始的
    }

    // 更改时分
    dialogTimeBinding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
      arrayListOf(hourOfDay, minute).forEachIndexed { index, i ->
        time[3 + index] = i
      }
    }

    // 现在编辑的是开始时间还是结束时间
    var isSt = true
    fun dawbTime() { // 年月日时分
      if (isSt) {
        dialogAddWorkBinding.timeSt.setText(StringBuilder().apply {
          time.forEachIndexed { index, i ->
            this.append(i)
            this.append("年月日时分"[index])
          }
        }.toString())
      } else {
        dialogAddWorkBinding.timeEd.setText(StringBuilder().apply {
          time.forEachIndexed { index, i ->
            this.append(i)
            this.append("年月日时分"[index])
          }
        }.toString())
      }
    }


    // 点击确定后显示时间
    val alertTime = builder.setTitle("编辑时间")
      .setView(dialogTimeBinding.root)
      .setPositiveButton("确定") { _, _ ->
        dawbTime()
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    // 设置开始时间
    dialogAddWorkBinding.setst.setOnClickListener {
      isSt = true
      alertTime.show()
    }

    // 设置结束时间
    dialogAddWorkBinding.seted.setOnClickListener {
      isSt = false
      alertTime.show()
    }

    // 添加新类型, 输入名称
    val alertType = builder.setTitle("编辑类型")
      .setView(dialogTypeBinding.root)
      .setPositiveButton("确定") { _, _ ->
        val name = dialogTypeBinding.editLxName.text.toString().trim()
        if (name.isEmpty()) {
          showToast("类型名称不合法")
        } else {
          addLx(typeList.last().id, name)
          loadPage(1)
        }
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    // 删除类型提示
    alertRemoveLx = builder.setTitle("确定级联删除所有类型和事务?")
      .setView(null)
      .setPositiveButton("确定") { _, _ ->
        doDropLx()
      }
      .setNegativeButton("取消") { _, _ ->
      }
      .create()


    // 重新命名
    alertRenameLx = builder.setTitle("类型重新命名")
      .setView(dialogRenameBinding.root)
      .setPositiveButton("确定") { _, _ ->
        val name = dialogRenameBinding.lxReName.text.toString().trim()
        if (name.isEmpty()) {
          showToast("类型名称不合法")
        } else {
          doRenameLx(name)
        }
      }
      .setNegativeButton("取消") { _, _ ->
      }
      .create()

    // 添加普通事务
    val alertAddWork = AlertDialog.Builder(this).setTitle("添加普通事务")
      .setView(dialogAddWorkBinding.root)
      .setPositiveButton("确定") { _, _ ->
        try {
          singleJob.name = dialogAddWorkBinding.addSwName.text.toString().trim()
          if (singleJob.name.isEmpty()) {
            throw Exception()
          }
          singleJob.statement = dialogAddWorkBinding.addSwStatement.text.toString()
          singleJob.priority = dialogAddWorkBinding.alarmTime.text.toString().toLong()
          singleJob.st = dialogAddWorkBinding.timeSt.text.toString().parseDate()
          singleJob.ed = dialogAddWorkBinding.timeEd.text.toString().parseDate()
          singleJob.alarm = dialogAddWorkBinding.alarmTime.text.toString().toLong()
        } catch (e: Exception) {
          showToast("事务信息不合法")
        }
        singleJob.type = typeList.last().id
        debug("now:: $singleJob")
        if (!checkSingleJobInfo()) {
          showToast("事务信息不合法")
        } else {
          addSingleJob(singleJob)
          loadPage(1)
        }
      }
      .setNegativeButton("取消") { _, _ ->
      }
      .create()


    // 添加周期性事务
    val alertAddWorkPeroid = builder.setTitle("添加周期性事务")
      .setView(dialogAddWorkPeroidBinding.root)
      .setPositiveButton("确定") { _, _ ->
        val name = dialogAddWorkPeroidBinding.pjobname.text.toString()
        val sta = dialogAddWorkPeroidBinding.pjobstatement.text.toString()
        val you = dialogAddWorkPeroidBinding.pjyou.text.toString().toLong()
        val ti = dialogAddWorkPeroidBinding.pjti.text.toString().toLong()
        addPJ(typeList.last().id, name, sta, you, ti, periodList)
        periodList.clear()
      }
      .setNegativeButton("取消") { _, _ ->

        periodList.clear()
      }
      .create()

    dialogAddWorkPeroidBinding.peroidList.layoutManager = LinearLayoutManager(this)
    dialogAddWorkPeroidBinding.peroidList.adapter = PeriodAdapter(periodList)

    var periodT = 0L
    val stst = arrayListOf("", "", "", "", "")
    val eded = arrayListOf("", "", "", "", "")

    dialogPeroidBinding.spinnerType.also {
      it.adapter = MyAdapter(this, 0)
      it.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
          periodT = 4L - position
          debug(periodT)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
      }

    }

    dialogPeroidBinding.spinnerSt.also {
      it.adapter = MyAdapter(this, 1)
      it.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
          stst[4] = ("Mon Tue Wed Thu Fri Sat Sun").split(' ')[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
      }
    }

    dialogPeroidBinding.spinnerEd.also {
      it.adapter = MyAdapter(this, 2)
      it.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
          eded[4] = ("Mon Tue Wed Thu Fri Sat Sun").split(' ')[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
      }

    }


    val alertPeroid = builder.setTitle("AAA")
      .setView(dialogPeroidBinding.root)
      .setPositiveButton("确定添加") { _, _ ->
        dialogPeroidBinding.apply {
          arrayListOf(
            yue1.text.toString(),
            ri1.text.toString(),
            shi1.text.toString(),
            fen1.text.toString(),
          ).forEachIndexed { index, s ->
            stst[index] = s
          }
        }

        dialogPeroidBinding.apply {
          arrayListOf(
            yue2.text.toString(),
            ri2.text.toString(),
            shi2.text.toString(),
            fen2.text.toString(),
          ).forEachIndexed { index, s ->
            eded[index] = s
          }
        }
        periodList.add(Period(-1, Date(), Date()).apply {
          type = periodT
          fun f(): String {
            return when (type) {
              0L -> "MM dd HH mm"
              1L -> "dd HH mm"
              2L -> "HH mm EEEE"
              3L -> "HH mm"
              4L -> "mm"
              else -> ""
            }
          }
          st = SimpleDateFormat(f(), Locale.ENGLISH).parse(StringBuilder().apply {
            stst.forEach {
              append(" ")
              append(it)
            }
          }.toString())!!
          ed = SimpleDateFormat(f(), Locale.ENGLISH).parse(StringBuilder().apply {
            eded.forEach {
              append(" ")
              append(it)
            }
          }.toString())!!
        })
        debug(periodList)
        dialogAddWorkPeroidBinding.peroidList.adapter = PeriodAdapter(periodList)
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()

    dialogAddWorkPeroidBinding.tianP.setOnClickListener {
      alertPeroid.show()
    }


    // 自动填充信息
    fun loadJob() {
      singleJob.name = "事务-" + abs(Random().nextLong() % (1L shl 16)).toString()
      dialogAddWorkBinding.addSwName.setText(singleJob.name)
      dialogAddWorkBinding.addSwStatement.setText(singleJob.statement)
      dialogAddWorkBinding.priorityNum.setText("5")
      setNowTime(time)
      isSt = true
      dawbTime()
      isSt = false
      dawbTime()
      dialogAddWorkBinding.alarmTime.setText(singleJob.alarm.toString())
    }

    // 三选一
    val alertSelection = builder.setTitle("在此目录下添加")
      .setView(null)
      .setNeutralButton("添加类型") { _, _ ->
        alertType.show()
      }
      .setNegativeButton("添加普通事务") { _: DialogInterface?, _: Int ->
        loadJob()
        alertAddWork.show()
      }
      .setPositiveButton("添加周期性事务") { _, _ ->
        alertAddWorkPeroid.show()
      }
      .create()

    // 显示三选一dialog
    activityMainBinding.add.setOnClickListener {
      alertSelection.show()
    }

  }

}