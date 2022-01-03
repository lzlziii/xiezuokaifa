package xx.yy.qian.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.func.getJobByLx
import xx.yy.hou.lz.func.getLxById
import xx.yy.hou.lz.func.getSonLx
import xx.yy.hou.lz.util.debug
import xx.yy.qian.activity.MainActivity
import xx.yy.qian.databinding.FragmentTypeListBinding

class TypeFragment(private val mainActivity: MainActivity, private val typeList: ArrayList<LeiXing>) : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    debug("type fragment on create")
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentTypeListBinding.inflate(inflater, container, false)

    // 当前类型栈
    typeList.forEachIndexed { index, it ->
      binding.lxButtonList.addView(Button(context).apply {
        this.text = it.name
        setOnClickListener {
          mainActivity.backToIndex(index)
        }
      })
    }

    val recyclerView = binding.lstype
    with(recyclerView) {
      layoutManager = LinearLayoutManager(context)
      val lastId = typeList.last().id
      adapter = TypeAdapter(mainActivity, getLxById(lastId)!!.name, getSonLx(lastId), getJobByLx(lastId))
    }
    return binding.root
  }


}