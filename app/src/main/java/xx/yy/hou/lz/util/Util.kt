package xx.yy.hou.lz.util;

import xx.yy.hou.lz.job.Period
import xx.yy.hou.lz.job.PeriodType
import java.text.SimpleDateFormat
import java.util.*

fun debug(x: Any?) {
  System.err.println(x.toString())
}

fun preMn(date: Date, mn: Long): Date {
  return Date(date.time - mn * 60L * 1000L)
}

fun Date.getMn(): String {
  val sdf = SimpleDateFormat("mm")
  return sdf.format(this)
}

fun Date.getHoMn(): String {
  val sdf = SimpleDateFormat("HHmm")
  return sdf.format(this)
}

fun Date.getWeHoMn(): String {
  val sdf = SimpleDateFormat("EEEEHHmm")
  return sdf.format(this)
}

fun isInPeriod(date: Date, period: Period): Boolean {
  when (period.type) {
    PeriodType.WEEK -> {
      return date.getWeHoMn() in period.st.getWeHoMn()..period.ed.getWeHoMn()
    }
    PeriodType.HOUR -> {
      return date.getMn() in period.st.getMn()..period.ed.getMn()
    }
    PeriodType.DAY -> {
      val now = date.getHoMn()
      return now in period.st.getHoMn()..period.ed.getHoMn()
    }
    else -> {
      debug("未实现")
    }
  }
  return true
}

// 注意，周六和周日属于不同的周
// 用于PeriodType.WEEK, 获得一个只含(周, 时, 分)的日期的补全
fun oWeek(x: Date, y: Date): Date { // x用y补全
  // 2021.12.29用ww会出现01, 因为这天时2022年的第一周, 用这种方法会出错
  val sdf1 = SimpleDateFormat("EEEE HH mm")
  val sdf2 = SimpleDateFormat("yyyy ww")
  val s1 = sdf1.format(x)
  var s2 = sdf2.format(y)
  // 当前是第12月, 然而本周属于一年中的第一周，那么只能算到下一年
  if (SimpleDateFormat("MM").format(y) == "12" && SimpleDateFormat("ww").format(y) == "01") {
    // 这一周算到下一年
    s2 = (Integer.parseInt(s2.subSequence(0, 4) as String) + 1).toString() + " " + s2.subSequence(5, 7) as String
    debug(s2)
  }
  val s = "$s2 $s1"
  val sdf3 = SimpleDateFormat("yyyy ww EEEE HH mm")
  return sdf3.parse(s)
}

// 用于PeriodType.WEEK, 获得一个补全的前u分钟的日期
fun ooWeek(x: Date, y: Date, u: Long): Date {
  val t = oDay(x, y)
  return Date(t.time - u * 60L * 1000L)
}

// 用于PeriodType.DAY, 获得一个只含(时, 分)的日期的补全
fun oDay(x: Date, y: Date): Date { // x用y补全
  val sdf1 = SimpleDateFormat("HH mm")
  val sdf2 = SimpleDateFormat("yyyy MM dd")
  val s1 = sdf1.format(x)
  val s2 = sdf2.format(y)
  val s = "$s2 $s1"
  val sdf3 = SimpleDateFormat("yyyy MM dd HH mm")
  return sdf3.parse(s)
}

// 用于PeriodType.Day, 获得一个补全的前u分钟的日期
fun ooDay(x: Date, y: Date, u: Long): Date {
  val t = oDay(x, y)
  return Date(t.time - u * 60L * 1000L)
}

// 用于PeriodType.HOUR, 获得一个只含(分)的日期的补全
fun oHour(x: Date, y: Date): Date { // x用y补全
  val sdf1 = SimpleDateFormat("mm")
  val sdf2 = SimpleDateFormat("yyyy MM dd HH")
  val s1 = sdf1.format(x)
  val s2 = sdf2.format(y)
  val s = "$s2 $s1"
  val sdf3 = SimpleDateFormat("yyyy MM dd HH mm")
  return sdf3.parse(s)
}

// 用于PeriodType.Day, 获得一个补全的前u分钟的日期
fun ooHour(x: Date, y: Date, u: Long): Date {
  val t = oHour(x, y)
  return Date(t.time - u * 60L * 1000L)
}