package xx.yy.hou.lz.queue

import android.os.Build
import androidx.annotation.RequiresApi
import xx.yy.hou.lz.aaa.Queue
import xx.yy.hou.lz.define.*
import xx.yy.hou.lz.util.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// 周期性
fun getQueue0(): ArrayList<Job> {
  return Queue.q[0]
}

fun addQueue0(x: PeriodJob) {
  Queue.q[0].add(x)
}

@RequiresApi(Build.VERSION_CODES.N)
fun removeQueue0(id: Long) {
  Queue.q[0].removeIf {
    it.id == id
  }
}

// 未开始的普通事务
fun getQueue1(): ArrayList<Job> {
  return Queue.q[1]
}

fun addQueue1(x: SingleJob) {
  Queue.q[1].add(x)
}

@RequiresApi(Build.VERSION_CODES.N)
fun removeQueue1(id: Long) {
  Queue.q[1].removeIf {
    it.id == id
  }
}

fun getNoSecondDate(): Date {
  var now = Date()
  val sdf = SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH)
  now = sdf.parse(sdf.format(now))!!
  return now
}

// 提前提醒的事务
fun getQueue2(now: Date): ArrayList<Job> {
  val q2 = ArrayList<XJob>()
  val sdf = SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH)
  for (i in Queue.q[0]) {
    if (i !is PeriodJob) break
    for (j in i.periodList) {
      var preDateSt = now
      var trueDateSt = now
      var trueDateEd = now
      // 用提前的时间填充
      when (j.type) {
        PeriodType.WEEK -> {
          preDateSt = ooWeek(j.st, now, i.alarm)
          trueDateSt = oWeek(j.st, now)
          trueDateEd = oWeek(j.ed, now)
        }
        PeriodType.DAY -> {
          preDateSt = ooDay(j.st, now, i.alarm)
          trueDateSt = oDay(j.st, now)
          trueDateEd = oDay(j.ed, now)
        }
        PeriodType.HOUR -> {
          preDateSt = ooHour(j.st, now, i.alarm)
          trueDateSt = oHour(j.st, now)
          trueDateEd = oHour(j.ed, now)
        }
        else -> {
          debug("未实现")
        }
      }
      if (sdf.format(now) in sdf.format(preDateSt)..sdf.format(trueDateSt)) {
        q2.add(XJob(i.type, i.id, i.name, i.statement, i.priority, trueDateSt, trueDateEd))
      }
    }
  }
  for (i in Queue.q[1]) {
    if (i !is SingleJob) break
    val preDate = preMn(i.st, i.alarm)
    if (sdf.format(now) in sdf.format(preDate)..sdf.format(i.st)) {
      q2.add(XJob(i.type, i.id, i.name, i.statement, i.priority, i.st, i.ed))
    }
  }
  return q2 as ArrayList<Job>
}

// 正在进行的事务
fun getQueue3(): ArrayList<Job> {
  return Queue.q[3]
}

fun addQueue3(x: XJob) {
  Queue.q[3].add(x)
}


// 已经结束的事务
fun getQueue4(): ArrayList<Job> {
  return Queue.q[4]
}

fun addQueue4(x: XJob) {
  Queue.q[4].add(x)
}

// 暂停的周期性事务
fun getQueue5(): ArrayList<Job> {
  return Queue.q[5]
}

fun addQueue5(x: PeriodJob) {
  Queue.q[5].add(x)
}

@RequiresApi(Build.VERSION_CODES.N)
fun removeQueue5(id: Long) {
  Queue.q[5].removeIf {
    it.id == id
  }
}

fun getQueue(whichQueue: Int): ArrayList<Job> {
  debug("get queue :: $whichQueue")
  return when (whichQueue) {
    0 -> getQueue0()
    1 -> getQueue1()
    2 -> getQueue2(Date())
    3 -> getQueue3()
    4 -> getQueue4()
    else -> getQueue5()
  }
}

fun addSingleJob(x: SingleJob) {
  x.id = generateId()
  val now = getNoSecondDate()
  if (x.st.after(now)) { // 已经结束
    debug("jin 55555555555")
    addQueue4(XJob(x.type, x.id, x.name, x.statement, x.priority, x.st, x.ed))
  } else if (x.ed.time == now.time || x.ed.after(now)) { // 正在进行
    debug("jin 44444444 正在进行")
    addQueue3(XJob(x.type, x.id, x.name, x.statement, x.priority, x.st, x.ed))
  } else {
    debug("jin 22222222222")
    addQueue1(x) // 还未开始
  }
}