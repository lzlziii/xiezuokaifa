package xx.yy.hou.lz.dispatch

import xx.yy.hou.lz.aaa.Queue.q
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.PeriodType
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.define.XJob
import xx.yy.hou.lz.queue.getQueue1
import xx.yy.hou.lz.util.debug
import xx.yy.hou.lz.util.oDay
import xx.yy.hou.lz.util.oHour
import xx.yy.hou.lz.util.oWeek
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun q1q4(now: Date) {
  for (i in getQueue1()) {
    if (i !is PeriodJob) break
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
      val sdf = SimpleDateFormat("yyyy MM dd HH mm", Locale.ENGLISH)
      if (sdf.format(now) == sdf.format(fillDateSt)) {
        q[3].add(XJob(i.type, i.id, i.name, i.statement, i.priority, fillDateSt, fillDateEd))
        break
      }
    }
  }
}

fun q2q4(now: Date) {
  val z = q[1] as ArrayList<SingleJob>
  z.sort()
  while (z.isNotEmpty() && z.first().st.time <= now.time) {
    val a = z.removeFirst()
    val b = XJob(a.type, a.id, a.name, a.statement, a.priority, a.st, a.ed)
    q[3].add(b)
  }
}

fun q4q5(now: Date) {
  val z = q[3] as ArrayList<XJob>
  z.sort()
  while (z.isNotEmpty() && z.first().ed.before(now)) {
    val a = z.removeFirst()
    q[4].add(a)
  }
}

fun autoDispatch() {
  val now = Date()
  q1q4(now)
  q2q4(now)
  q4q5(now)
}
