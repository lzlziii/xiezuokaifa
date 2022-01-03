package xx.yy.hou.lz.func

import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.queue.*

fun getAllJob(): ArrayList<Job> {
  return ArrayList<Job>().apply {
    getQueue1().forEach { add(it as Job) }
    getQueue2().forEach { add(it as Job) }
    getQueue4().forEach { add(it as Job) }
    getQueue5().forEach { add(it as Job) }
    getQueue6().forEach { add(it as Job) }
  }
}

fun getJobTypeNameById(id: Long): String {
  return getLxById(getJobById(id)!!.type)!!.name
}

fun getJobById(id: Long): Job? {
  for (i in getAllJob()) {
    if (i.id == id) return i
  }
  return null
}