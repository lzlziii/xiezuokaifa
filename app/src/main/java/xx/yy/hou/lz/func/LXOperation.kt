package xx.yy.hou.lz.func

import xx.yy.hou.lz.aaa.LX
import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.queue.*
import xx.yy.hou.lz.util.debug
import java.util.*
import kotlin.collections.ArrayList

fun getJobByLx(lxId: Long): ArrayList<Job> {
  return ArrayList<Job>().apply {
    for (i in getQueue1()) if (i.type == lxId) this.add(i)
    for (i in getQueue2()) if (i.type == lxId) this.add(i)
    for (i in getQueue4()) if (i.type == lxId) this.add(i)
    for (i in getQueue5()) if (i.type == lxId) this.add(i)
    for (i in getQueue6()) if (i.type == lxId) this.add(i)
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
  val newId = 10000000L + Random().nextLong()
  debug("newId $newId")
  LX.leiXing[newId] = LeiXing(newId, parentId, name)
  if (LX.leiXingLink[parentId] == null) {
    LX.leiXingLink[parentId] = TreeSet<Long>().apply {
      add(newId)
    }
  } else {
    LX.leiXingLink.getValue(parentId).add(newId)
  }
}

fun removeLxById(id: Long) {

  // 删除所有该类型的事务
  getQueue1().dropWhile { it.type == id }

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