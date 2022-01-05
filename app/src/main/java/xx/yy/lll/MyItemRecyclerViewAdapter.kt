package xx.yy.lll

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xx.yy.qian.R

import xx.yy.lll.placeholder.PlaceholderContent.PlaceholderItem
import xx.yy.qian.databinding.FragmentItem222222Binding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
  private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(FragmentItem222222Binding.inflate(LayoutInflater.from(parent.context), parent, false))

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = item.id
    holder.contentView.text = item.content
  }

  override fun getItemCount(): Int = values.size

  inner class ViewHolder(binding: FragmentItem222222Binding) : RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
      return super.toString() + " '" + contentView.text + "'"
    }
  }

}