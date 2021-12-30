package xx.yy.hou.lz.job

import java.io.Serializable
import java.util.*

object PeriodType {
  var YEAR = 0
  var MONTH = 1
  var WEEK = 2
  var DAY = 3
  var HOUR = 4
}

class Period(
  var type: Int,
  var st: Date,
  var ed: Date
) : Serializable

class PeriodJob(
  var type: Int,
  var id: Int,
  var name: String,
  var statement: String,
  var periodList: List<Period>, // 周期列表
  var alarm: Long, // 提前多少分钟提醒
) : Serializable