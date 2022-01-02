package xx.yy.qian.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xx.yy.qian.R
import xx.yy.qian.databinding.FragmentTypeListBinding
import xx.yy.qian.fragment.TypeAdapter

class TypeFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    val binding = FragmentTypeListBinding.inflate(inflater, container, false)
    val recyclerView = binding.lstype
    with(recyclerView) {
      layoutManager = LinearLayoutManager(context)
      adapter = TypeAdapter()
    }
    return binding.root
  }
}