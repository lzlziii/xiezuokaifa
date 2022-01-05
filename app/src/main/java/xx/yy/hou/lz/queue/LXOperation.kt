package xx.yy.hou.lz.queue

import android.os.Build
import androidx.annotation.RequiresApi
import xx.yy.hou.lz.aaa.LX
import xx.yy.hou.lz.aaa.Queue
import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.util.generateId
import java.util.*
import kotlin.collections.ArrayList

fun Job.getLxName(): String {
  return getLxById(this.type).name
}

fun getJobByLx(lxId: Long): ArrayList<Job> {
  return ArrayList<Job>().apply {
    getAllJob().forEach {
      if (it.type == lxId) {
        add(it)
      }
    }
  }
}

fun getSonLx(lxId: Long): ArrayList<LeiXing> {
  return ArrayList<LeiXing>().apply {
    LX.leiXingLink.getOrDefault(lxId, TreeSet()).forEach {
      add(LX.leiXing[it]!!)
    }
  }
}

fun getLxById(lxId: Long): LeiXing {
  return LX.leiXing[lxId]!!
}

fun getParentLxById(lxId: Long): LeiXing {
  val pId = LX.leiXing[lxId]!!.parentId
  return LX.leiXing[pId]!!
}

fun addLx(parentId: Long, name: String) {
  val newId = generateId()
  LX.leiXing[newId] = LeiXing(newId, parentId, name)
  if (LX.leiXingLink[parentId] == null) {
    LX.leiXingLink[parentId] = TreeSet<Long>().apply {
      add(newId)
    }
  } else {
    LX.leiXingLink.getValue(parentId).add(newId)
  }
}

@RequiresApi(Build.VERSION_CODES.N)
fun removeLxById(id: Long) {

  // 删除所有该类型的事务
  Queue.q.forEach { x ->
    x.removeIf {
      it.type == id
    }
  }

  // 删除父类型的孩子
  LX.leiXingLink[getParentLxById(id).id]!!.remove(id)

  // 递归删除子类型
  for (sonId in LX.leiXingLink.getOrDefault(id, TreeSet())) {
    removeLxById(sonId)
  }

  // 删除自己的信息
  LX.leiXing.remove(id)

  // 删除自己的连接信息
  LX.leiXingLink.remove(id)
}

fun renameLx(id: Long, name: String) {
  LX.leiXing[id]!!.name = name
}