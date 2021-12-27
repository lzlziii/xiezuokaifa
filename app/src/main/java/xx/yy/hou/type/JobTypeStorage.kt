package xx.yy.hou.type

import android.util.Log
import java.lang.Exception
import java.util.ArrayList

object JobTypeStorage {
  private var lj: MutableList<JobType> = ArrayList()

  // 连接服务器获得类型列表, 暂时未实现
  fun reset() {
    lj = ArrayList()
    lj.add(JobType(0, 0, "所有类型"))
    lj.add(JobType(1, 0, "作业"))
    lj.add(JobType(2, 0, "体育活动"))
    lj.add(JobType(3, 1, "Java作业"))
    lj.add(JobType(4, 1, "Android作业"))
    lj.add(JobType(5, 2, "跑步"))
    lj.add(JobType(6, 2, "打羽毛球"))
  }

  // 通过id获得所有的子类型
  fun getSons(id: Int): List<JobType> {
    val result: MutableList<JobType> = ArrayList()
    for (i in lj) {
      if (i.parentId == id) {
        result.add(i)
      }
    }
    if (result.isEmpty()) {
      Log.d("Lz", "注意! " + id + "没有子类型.")
    }
    return result
  }

  // 通过id获得父类型的id
  fun getParentId(id: Int): Int {
    for (i in lj) {
      if (i.id == id) {
        return i.parentId
      }
    }
    Exception("id无效啊").printStackTrace()
    return -1
  }

  // 通过id获得对应的JobType
  fun getJobType(id: Int): JobType? {
    for (i in lj) {
      if (i.id == id) {
        return i
      }
    }
    Exception("id无效").printStackTrace()
    return null
  }
}