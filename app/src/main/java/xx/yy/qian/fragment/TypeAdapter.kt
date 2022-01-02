package xx.yy.qian.fragment

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import xx.yy.qian.databinding.FragmentTypeBinding
import xx.yy.qian.databinding.FragmentType2Binding

class TypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      0 -> {
        ViewHolder(FragmentTypeBinding.inflate(LayoutInflater.from(parent.context)))
      }
      else -> {
        ViewHolder2(FragmentType2Binding.inflate(LayoutInflater.from(parent.context)))
      }
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val gd = GradientDrawable();

    // gd.setColor(Color.parseColor("#00ff00"));

    gd.cornerRadius = 8f

    // 描边的宽度和颜色
    // gd.setStroke(1, Color.parseColor("#ff0000")); // 描边的颜色和宽度

    gd.orientation = GradientDrawable.Orientation.RIGHT_LEFT

    gd.colors = when (position % 3) {
      0 -> intArrayOf(Color.YELLOW, Color.GREEN)
      1 -> intArrayOf(Color.CYAN, Color.MAGENTA)
      else -> intArrayOf(Color.RED, Color.GRAY)
    }
    if (position % 2 == 0) {
      (holder as ViewHolder).x.background = gd
    } else {
      (holder as ViewHolder2).x.background = gd
    }
  }

  override fun getItemViewType(position: Int): Int {
    return position % 2
  }

  override fun getItemCount() = 12

  inner class ViewHolder(binding: FragmentTypeBinding) : RecyclerView.ViewHolder(binding.root) {
    val x = binding.fragmentTypeLinearlayout
  }

  inner class ViewHolder2(binding: FragmentType2Binding) : RecyclerView.ViewHolder(binding.root) {
    val x = binding.fragmentTypeLinearlayout2
  }

}