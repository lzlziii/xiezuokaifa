package xx.yy.hou.lz.define

import java.io.Serializable
import java.sql.CallableStatement
import java.util.*

object PeriodType {
  var YEAR = 0L
  var MONTH = 1L
  var WEEK = 2L
  var DAY = 3L
  var HOUR = 4L
}

class Period(
  var type: Long,
  var st: Date,
  var ed: Date
) : Serializable {

  override fun toString(): String {
    return "Period(type=$type, st=$st, ed=$ed)"
  }

}

class PeriodJob(
  type: Long = -1,
  id: Long = -1,
  name: String = "",
  statement: String = "",
  var periodList: List<Period>, // 周期列表
  var alarm: Long, // 提前多少分钟提醒
) : Job(type, id, name, statement), Serializable {

  override fun toString(): String {
    return "PeriodJob(type=$type, id=$id, name='$name', statement='$statement', periodList=$periodList, alarm=$alarm)"
  }
}