package xx.yy.qian.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.func.getJobByLx
import xx.yy.hou.lz.func.getLxById
import xx.yy.hou.lz.func.getSonLx
import xx.yy.hou.lz.util.debug
import xx.yy.qian.databinding.FragmentTypeListBinding

class TypeFragment(private val typeList: ArrayList<LeiXing>) : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    debug("type fragment on create")
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding = FragmentTypeListBinding.inflate(inflater, container, false)

    debug("here")

    // 当前类型栈
    typeList.forEach {
      binding.lxButtonList.addView(Button(context).apply {
        this.text = it.name
      })
    }

    debug("typeList kan kan:: $typeList")

    val recyclerView = binding.lstype
    with(recyclerView) {
      layoutManager = LinearLayoutManager(context)
      val lastId = typeList.last().id
      debug("sonson :: ${getSonLx(lastId)}")
      debug("son job:: ${getJobByLx(lastId)}")
      adapter = TypeAdapter(getLxById(lastId)!!.name, getSonLx(lastId), getJobByLx(lastId))
    }
    return binding.root
  }
}