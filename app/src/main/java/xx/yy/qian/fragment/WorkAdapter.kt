package xx.yy.qian.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import xx.yy.qian.R

class WorkAdapter : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val x: ConstraintLayout = view.findViewById(R.id.zong)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_work_item_period, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val gd = GradientDrawable();

    // gd.setColor(Color.parseColor("#00ff00"));

    gd.cornerRadius = 60f

    // 描边的宽度和颜色
    // gd.setStroke(1, Color.parseColor("#ff0000")); // 描边的颜色和宽度

    gd.orientation = GradientDrawable.Orientation.RIGHT_LEFT

    gd.colors = when (position % 3) {
      0 -> intArrayOf(Color.YELLOW, Color.GREEN)
      1 -> intArrayOf(Color.CYAN, Color.MAGENTA)
      else -> intArrayOf(Color.RED, Color.GRAY)
    }
    holder.x.background = gd
  }

  override fun getItemCount(): Int {
    return 15
  }

}