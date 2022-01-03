package xx.yy.hou.lz.aaa

import xx.yy.hou.lz.define.Period
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.define.XJob
import kotlin.collections.ArrayList

object Queue {
  var q1 = ArrayList<PeriodJob>().apply {
    add(PeriodJob(0L, 10L, "下午开会", "早到10分钟", ArrayList<Period>(), 30))
    add(PeriodJob(1L, 11L, "学习编程", "cccc", ArrayList<Period>(), 30))
    add(PeriodJob(1L, 12L, "学习数学", "math", ArrayList<Period>(), 30))
    add(PeriodJob(2L, 13L, "下午开会", "laksjdlfj", ArrayList<Period>(), 30))
    add(PeriodJob(3L, 14L, "下午开会", "iasn", ArrayList<Period>(), 30))
  }
  var q2 = ArrayList<SingleJob>()
  var q4 = ArrayList<XJob>()
  var q5 = ArrayList<XJob>()
  var q6 = ArrayList<PeriodJob>()
}
