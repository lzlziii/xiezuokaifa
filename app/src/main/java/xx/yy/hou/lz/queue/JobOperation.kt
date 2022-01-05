package xx.yy.hou.lz.queue

import android.os.Build
import androidx.annotation.RequiresApi
import xx.yy.hou.lz.aaa.Queue
import xx.yy.hou.lz.define.Job
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.define.XJob
import xx.yy.hou.lz.util.debug

fun getAllJob(): ArrayList<Job> {
  return ArrayList<Job>().apply {
    Queue.q.forEach { x ->
      x.forEach { y ->
        add(y)
      }
    }
  }
}

fun getJobById(id: Long): Job? {
  for (i in getAllJob()) {
    if (i.id == id) return i
  }
  return null
}

fun getJobTypeNameById(id: Long): String {
  return getLxById(getJobById(id)!!.type).name
}

fun mysortPeriodJob(p: ArrayList<PeriodJob>, sortMethod: Int): Boolean {
  when (sortMethod) {
    4 -> p.sortBy { it.priority }
    5 -> p.sortByDescending { it.priority }
    else -> {
      return false
    }
  }
  return true
}

fun mysortSingleJob(p: ArrayList<SingleJob>, sortMethod: Int): Boolean {
  when (sortMethod) {
    0 -> p.sortBy { it.st }
    1 -> p.sortByDescending { it.st }
    2 -> p.sortBy { it.ed }
    3 -> p.sortByDescending { it.ed }
    4 -> p.sortBy { it.priority }
    5 -> p.sortByDescending { it.priority }
    else -> {
      return false
    }
  }
  return true
}

fun mysortXJob(p: ArrayList<XJob>, sortMethod: Int): Boolean {
  when (sortMethod) {
    0 -> p.sortBy { it.st }
    1 -> p.sortByDescending { it.st }
    2 -> p.sortBy { it.ed }
    3 -> p.sortByDescending { it.ed }
    4 -> p.sortBy { it.priority }
    5 -> p.sortByDescending { it.priority }
    else -> {
      return false
    }
  }
  return true
}

@RequiresApi(Build.VERSION_CODES.N)
fun removeJobById(id: Long) {
  Queue.q.forEach { z ->
    z.removeIf { it.id == id }
  }
}

fun getJobInQueue(jobId: Long, whichQueue: Int): Job {
  val z = ArrayList<Job>().apply {
    Queue.q[whichQueue].forEach {
      if (it.id == jobId) {
        add(it)
      }
    }
  }
  if (z.size != 1) debug("na ni")
  return z.first()
}