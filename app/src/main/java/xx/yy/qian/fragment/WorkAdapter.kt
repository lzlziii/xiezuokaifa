package xx.yy.qian.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.define.XJob
import xx.yy.hou.lz.queue.*
import xx.yy.hou.lz.util.debug
import xx.yy.hou.lz.util.generateString
import xx.yy.qian.activity.MainActivity
import xx.yy.qian.databinding.FragmentWorkItemBinding

class WorkAdapter(
  private val sortMethod: Int,
  private val workFragment: WorkFragment,
  private val mainActivity: MainActivity,
) : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

  inner class ViewHolder(binding: FragmentWorkItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val layout = binding.zong
    val jobName = binding.jobName
    val jobType = binding.jobType
    val jobSt = binding.jobSt
    val jobEd = binding.jobEd
    val jobPri = binding.jobPriority
    val jobSta = binding.jobStatement
    val shanchuJoooob = binding.shanchuJoooob
    val gengxinjoooob = binding.gengxinJoooob
    val zantingJoooob = binding.zantingJoooob
    val huifuJoooob = binding.huifuJooob
  }

  private val a = getQueue(mainActivity.whichQueue).apply {
    if (sortMethod != -1) {
      when (mainActivity.whichQueue) {
        in setOf(0, 5) -> if (!mysortPeriodJob((this as ArrayList<PeriodJob>), sortMethod)) {
          mainActivity.showToast("该事务不支持该方式排序")
        }
        in setOf(1) -> if (!mysortSingleJob((this as ArrayList<SingleJob>), sortMethod)) {
          mainActivity.showToast("该事务不支持该方式排序")
        }
        else -> if (!mysortXJob((this as ArrayList<XJob>), sortMethod)) {
          mainActivity.showToast("该种类的事务按此方式排序")
        }
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(FragmentWorkItemBinding.inflate(LayoutInflater.from(parent.context)))
  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.layout.background = GradientDrawable().apply {
      cornerRadius = 60f
      orientation = GradientDrawable.Orientation.RIGHT_LEFT
      colors = when (position % 3) {
        0 -> intArrayOf(Color.YELLOW, Color.GREEN)
        1 -> intArrayOf(Color.CYAN, Color.MAGENTA)
        else -> intArrayOf(Color.RED, Color.GRAY)
      }
    }

    val nowJob = a[position]

    holder.apply {
      jobName.text = nowJob.name
      jobType.text = nowJob.getLxName()
      if (mainActivity.whichQueue in setOf(1, 2, 3, 4)) {
        jobSt.text = when (mainActivity.whichQueue) {
          1 -> (nowJob as SingleJob).st.generateString()
          else -> (nowJob as XJob).st.generateString()
        }
        jobEd.text = when (mainActivity.whichQueue) {
          1 -> (nowJob as SingleJob).ed.generateString()
          else -> (nowJob as XJob).ed.generateString()
        }
      } else {
        jobSt.text = "################"
        jobEd.text = "################"
      }
      jobPri.text = nowJob.priority.toString()
      jobSta.text = nowJob.statement
    }

    holder.apply {
      shanchuJoooob.setOnClickListener { workFragment.askremove(nowJob.id) }
      gengxinjoooob.setOnClickListener { workFragment.askupdate(nowJob.id) }
      zantingJoooob.setOnClickListener { workFragment.askzan(nowJob.id) }
      huifuJoooob.setOnClickListener { workFragment.askhui(nowJob.id) }
    }

    fun Button.cannotClick() {
      setBackgroundColor(Color.GRAY)
      setOnClickListener { mainActivity.showToast("该事务不能${this.text}") }
    }

    if (mainActivity.whichQueue in setOf(2)) holder.shanchuJoooob.cannotClick()
    if (mainActivity.whichQueue in setOf(2)) holder.gengxinjoooob.cannotClick()
    if (mainActivity.whichQueue in setOf(1, 2, 3, 4, 5)) holder.zantingJoooob.cannotClick()
    if (mainActivity.whichQueue in setOf(0, 1, 2, 3, 4)) holder.huifuJoooob.cannotClick()
  }

  override fun getItemCount() = a.size
}