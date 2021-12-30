package xx.yy.hou.lz.dispatch

import xx.yy.hou.lz.job.PeriodType
import xx.yy.hou.lz.job.XJob
import xx.yy.hou.lz.queue.q1
import xx.yy.hou.lz.queue.q2
import xx.yy.hou.lz.queue.q4
import xx.yy.hou.lz.queue.q5
import xx.yy.hou.lz.util.debug
import xx.yy.hou.lz.util.oDay
import xx.yy.hou.lz.util.oHour
import xx.yy.hou.lz.util.oWeek
import java.text.SimpleDateFormat
import java.util.*

fun q1q4(now: Date) {
  for (i in q1) {
    for (j in i.periodList) {
      var fillDateSt = now
      var fillDateEd = now
      // 用现在的时间填充
      when (j.type) {
        PeriodType.WEEK -> {
          fillDateSt = oWeek(j.st, now)
          fillDateEd = oWeek(j.ed, now)
        }
        PeriodType.DAY -> {
          fillDateSt = oDay(j.st, now)
          fillDateEd = oDay(j.ed, now)
        }
        PeriodType.HOUR -> {
          fillDateSt = oHour(j.st, now)
          fillDateEd = oHour(j.ed, now)
        }
        else -> {
          debug("未实现按小时的按周的...")
        }
      }
      var sdf = SimpleDateFormat("yyyy MM dd HH mm")
      if (sdf.format(now) == sdf.format(fillDateSt)) {
        q4.add(XJob(i.type, i.id, i.name, i.statement, fillDateSt, fillDateEd))
        break
      }
    }
  }
}

fun q2q4(now: Date) {
  q2.sort()
  while (q2.isNotEmpty() && q2.first().st.time <= now.time) {
    var a = q2.removeFirst()
    var b = XJob(a.type, a.id, a.name, a.statement, a.st, a.ed)
    q4.add(b)
  }
}

fun q4q5(now: Date) {
  q4.sort()
  while (q4.isNotEmpty() && q4.first().ed.before(now)) {
    var a = q4.removeFirst()
    q5.add(a)
  }
}

fun autoDispatch() {
  val now = Date()
  q1q4(now)
  q2q4(now)
  q4q5(now)
}
