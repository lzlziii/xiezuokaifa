package xx.yy.hou.lz.queue

import xx.yy.hou.lz.define.PeriodType
import xx.yy.hou.lz.define.XJob
import xx.yy.hou.lz.aaa.Queue
import xx.yy.hou.lz.util.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun getQueue3(now: Date): ArrayList<XJob> {
  val q3 = ArrayList<XJob>()
  val sdf = SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH)
  for (i in Queue.q1) {
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
  for (i in Queue.q2) {
    val preDate = preMn(i.st, i.alarm)
    if (sdf.format(now) in sdf.format(preDate)..sdf.format(i.st)) {
      q3.add(XJob(i.type, i.id, i.name, i.statement, i.st, i.ed))
    }
  }
  return q3
}