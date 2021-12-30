package xx.yy.hou.lz.queue

import xx.yy.hou.lz.job.PeriodJob
import xx.yy.hou.lz.job.PeriodType
import xx.yy.hou.lz.job.SingleJob
import xx.yy.hou.lz.job.XJob
import xx.yy.hou.lz.util.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

var q1 = ArrayList<PeriodJob>()
var q2 = ArrayList<SingleJob>()
var q4 = ArrayList<XJob>()
var q5 = ArrayList<XJob>()
var q6 = ArrayList<PeriodJob>()

fun getQueue1(): ArrayList<PeriodJob> {
  return q1
}

fun getQueue2(): ArrayList<SingleJob> {
  return q2
}

fun getQueue3(now: Date): ArrayList<XJob> {
  val q3 = ArrayList<XJob>()
  var sdf = SimpleDateFormat("yyyy MM dd HH mm")
  for (i in q1) {
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
        q3.add(XJob(i.type, i.id, i.name, i.statement, trueDateSt, trueDateEd))
      }
    }
  }
  for (i in q2) {
    var preDate = preMn(i.st, i.alarm)
    if (sdf.format(now) in sdf.format(preDate)..sdf.format(i.st)) {
      q3.add(XJob(i.type, i.id, i.name, i.statement, i.st, i.ed))
    }
  }
  return q3
}

fun getQueue4(): ArrayList<XJob> {
  return q4
}

fun getQueue5(): ArrayList<XJob> {
  return q5
}

fun getQueue6(): ArrayList<PeriodJob> {
  return q6
}

fun addJ() {

}

fun addPJ() {

}