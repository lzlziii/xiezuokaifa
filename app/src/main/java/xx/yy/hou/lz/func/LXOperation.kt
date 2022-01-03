package xx.yy.hou.lz.func

import xx.yy.hou.lz.aaa.LX
import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.queue.*

fun getLeiXing(): ArrayList<LeiXing> {
  return LX.leiXing
}

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
    for (i in getLeiXing()) if (i.parentId == lxId) this.add(i)
  }
}

fun getLxById(lxId: Long): LeiXing? {
  for (i in getLeiXing()) {
    if (i.id == lxId) {
      return i
    }
  }
  return null
}

fun getParentLxById(lxId: Long): LeiXing? {
  for (i in getLeiXing()) {
    if (i.id == lxId) {
      return getLxById(i.parentId)
    }
  }
  return null
}

fun addLx(id: Long, parentId: Long, name: String) {
  LX.leiXing.add(LeiXing(id, parentId, name))
}