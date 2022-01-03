package xx.yy.qian.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import xx.yy.hou.lz.define.*
import xx.yy.hou.lz.func.getJobByLx
import xx.yy.hou.lz.func.getSonLx
import xx.yy.hou.lz.util.debug
import xx.yy.qian.databinding.FragmentTypeBinding
import xx.yy.qian.databinding.FragmentType2Binding
import java.lang.StringBuilder

class TypeAdapter(
  private val nowCat: String,
  private val typeList: ArrayList<LeiXing>,
  private val workList: ArrayList<Job>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  inner class ViewHolder(binding: FragmentTypeBinding) : RecyclerView.ViewHolder(binding.root) {
    val layout = binding.fragmentTypeLinearlayout
    val lxName = binding.lxName
    val parentLxName = binding.parentLxName
    val sonName = binding.sonName
    val sonWorkName = binding.sonWorkName
  }

  inner class ViewHolder2(binding: FragmentType2Binding) : RecyclerView.ViewHolder(binding.root) {
    val x = binding.fragmentTypeLinearlayout2
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      0 -> ViewHolder(FragmentTypeBinding.inflate(LayoutInflater.from(parent.context)))
      else -> ViewHolder2(FragmentType2Binding.inflate(LayoutInflater.from(parent.context)))
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    debug(position)
    val gd = GradientDrawable()
    // gd.setColor(Color.parseColor("#00ff00"));
    gd.cornerRadius = 40f
    // gd.setStroke(1, Color.parseColor("#ff0000")); // 描边的颜色和宽度
    gd.orientation = GradientDrawable.Orientation.RIGHT_LEFT
    gd.colors = when (position % 3) {
      0 -> intArrayOf(Color.YELLOW, Color.GREEN)
      1 -> intArrayOf(Color.CYAN, Color.MAGENTA)
      else -> intArrayOf(Color.RED, Color.GRAY)
    }

    if (position < typeList.size) {
      val me = typeList[position]
      (holder as ViewHolder).apply {

        // 类型名称
        lxName.text = me.name

        // 父类型名称
        parentLxName.text = nowCat

        // 子类型名称
        sonName.text = StringBuilder().apply {
          getSonLx(me.id).forEachIndexed { index, i ->
            if (index > 0) this.append(", ")
            this.append(i.name)
          }
        }.toString()

        // 子事务名称
        sonWorkName.text = StringBuilder().apply {
          getJobByLx(me.id).forEachIndexed { index, i ->
            if (index > 0) append(", ")
            append(
              when (i) {
                is PeriodJob -> i.name
                is SingleJob -> i.name
                is XJob -> i.name
                else -> "na ni"
              }
            )
          }
        }.toString()

        // 当前item的背景
        layout.background = gd
      }
    } else {
      val me = workList[position - typeList.size]
      (holder as ViewHolder2).apply {
        x.background = gd
      }
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (position < typeList.size) 0
    else 1
  }

  override fun getItemCount() = typeList.size + workList.size
}