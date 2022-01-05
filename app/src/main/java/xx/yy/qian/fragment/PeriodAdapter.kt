package xx.yy.qian.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import xx.yy.hou.lz.define.*
import xx.yy.hou.lz.queue.parLxName
import xx.yy.hou.lz.queue.parStrByZhou
import xx.yy.qian.databinding.FragmentTypeBinding
import xx.yy.qian.databinding.PeroidItemBinding

class PeriodAdapter(
  private val peroidList: ArrayList<Period>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  inner class ViewHolder(binding: PeroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val pName = binding.pName
    val pSt = binding.pSt
    val pEd = binding.pEd
  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

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

    val now = peroidList[position]

    (holder as ViewHolder).apply {
      pName.text = now.type.parLxName()
      pSt.text = now.st.parStrByZhou(now.type)
      pEd.text = now.ed.parStrByZhou(now.type)
    }


  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return ViewHolder(PeroidItemBinding.inflate(LayoutInflater.from(parent.context)))
  }

  override fun getItemCount() = peroidList.size
}