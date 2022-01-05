package xx.yy.qian.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.queue.*
import xx.yy.hou.lz.util.debug
import xx.yy.qian.R
import xx.yy.qian.activity.MainActivity
import xx.yy.qian.databinding.FragmentWorkBinding

class WorkFragment(
  private var mySortMethod: Int,
  private val mainActivity: MainActivity,
) : Fragment() {

  private lateinit var recyclerView: RecyclerView
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val fragmentWorkBinding = FragmentWorkBinding.inflate(inflater, container, false)
    val f = fragmentWorkBinding

    recyclerView = fragmentWorkBinding.recyclerView

    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = WorkAdapter(-1, this, mainActivity)

    listOf(f.q1, f.q2, f.q3, f.q4, f.q5, f.q6).forEachIndexed { index, button ->
      button.setOnClickListener {
        mainActivity.whichQueue = index
        mainActivity.setCurrentJobText()
        recyclerView.adapter = WorkAdapter(-1, this, mainActivity)
      }
    }

    return fragmentWorkBinding.root
  }

  private fun resetAdapter() {
    recyclerView.adapter = WorkAdapter(mySortMethod, this, mainActivity)
  }

  fun resetAdapter(sortMethod: Int) {
    mySortMethod = sortMethod
    recyclerView.adapter = WorkAdapter(mySortMethod, this, mainActivity)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun askJobOperation(id: Long, operation: Int) {
    debug("jin123123")
    val s = "确定" + arrayListOf("删除", "更新", "暂停", "恢复")[operation] + "该事务?"
    AlertDialog.Builder(mainActivity)
      .setTitle(s)
      .setPositiveButton("确定") { _, _ ->
        when (operation) {
          0 -> removeJobF(id)
          1 -> updateJobF(id)
          2 -> zanJobF(id)
          else -> huiJobF(id)
        }
      }
      .setNegativeButton("取消") { _, _ -> }
      .create()
      .show()
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun askremove(id: Long) {
    askJobOperation(id, 0)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun askupdate(id: Long) {
    askJobOperation(id, 1)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun askzan(id: Long) {
    askJobOperation(id, 2)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun askhui(id: Long) {
    askJobOperation(id, 3)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun removeJobF(id: Long) {
    removeJobById(id)
    resetAdapter()
    mainActivity.showToast("事务已删除")
  }

  fun updateJobF(id: Long) {
    mainActivity.showToast("未实现")
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun zanJobF(id: Long) {
    val x = getJobInQueue(id, 0)
    addQueue5(x as PeriodJob)
    removeQueue0(id)
    resetAdapter()
    mainActivity.showToast("事务已暂停")
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun huiJobF(id: Long) {
    val x = getJobInQueue(id, 5)
    addQueue0(x as PeriodJob)
    removeQueue5(id)
    resetAdapter()
    mainActivity.showToast("事务已恢复")
  }

}