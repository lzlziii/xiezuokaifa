package xx.yy.hou.lz.define

import java.io.Serializable
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
) : Serializable

class PeriodJob(
  var type: Long,
  var id: Long,
  var name: String,
  var statement: String,
  var periodList: List<Period>, // 周期列表
  var alarm: Long, // 提前多少分钟提醒
) : Serializable