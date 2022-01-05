package xx.yy.hou.lz.aaa

import xx.yy.hou.lz.define.*
import java.util.*
import kotlin.collections.ArrayList

object Queue {
  var q = arrayListOf(
    ArrayList<PeriodJob>().apply {
      add(PeriodJob(0L, 10L, "下午开会", "早到10分钟", 5L, ArrayList<Period>(), 30))
      add(PeriodJob(1L, 11L, "学习编程", "cccc", 2L, ArrayList<Period>(), 30))
      add(PeriodJob(1L, 12L, "学习数学", "math", 5L, ArrayList<Period>(), 30))
      add(PeriodJob(2L, 13L, "LKJASLJ", "laksjdlfj", 12299900099L, ArrayList<Period>(), 30))
      add(PeriodJob(3L, 14L, "LLLLLLLLLL", "iasn", 5L, ArrayList<Period>(), 30))
    } as ArrayList<Job>, // 1 周期性事务
    ArrayList<SingleJob>().apply {
      add(SingleJob(0L, 90L, "这是个还未开始的事务XYZ", "描述描述", 3L, Date(Date().time + 100000000000L + 5000000L), Date(Date().time + 100000000000L), 20L))
      add(SingleJob(1L, 91L, "这是个还未开始的事务KKKXYZ", "描述描述ikkk", 4L, Date(Date().time + 100000000000L - 100000000L), Date(Date().time + 100000000000L + 100000000L), 20L))
      add(SingleJob(2L, 92L, "这是个还未开始的事务LKJLKXYZ", "描述描述asdf", 2L, Date(Date().time + 100000000000L), Date(Date().time + 100000000000L + 5000000L), 20L))
    } as ArrayList<Job>, // 2 未开始的普通事务
    ArrayList<Job>(), // 即将开始的事务, 占位, 动态生成, size永远是0
    ArrayList<XJob>() as ArrayList<Job>, // 4 正在进行的事务
    ArrayList<XJob>() as ArrayList<Job>, // 5 已经结束的事务
    ArrayList<PeriodJob>() as ArrayList<Job>, // 6 暂停的周期性事务
  )
}
