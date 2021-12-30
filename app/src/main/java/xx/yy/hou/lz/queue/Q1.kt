package xx.yy.hou.lz.queue

import xx.yy.hou.lz.aaa.Cnt
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.aaa.Queue

fun getQueue1(): ArrayList<PeriodJob> {
  return Queue.q1
}

fun removeQueue1(id: Long): String {
  for (i in 0 until Queue.q1.size) {
    if (Queue.q1[i].id == id) {
      Queue.q1.removeAt(i)
      return "shan le yi ge"
    }
  }
  return "yi ge mei shan"
}

fun addQueue1(pj: PeriodJob): String {
  pj.id = ++Cnt.cnt
  Queue.q1.add(pj)
  return ""
}

fun updateQueue1(id: Long, pj: PeriodJob) {
  removeQueue1(id)
  pj.id = id
  addQueue1(pj)
}