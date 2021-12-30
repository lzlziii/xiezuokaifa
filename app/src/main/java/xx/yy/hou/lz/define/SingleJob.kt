package xx.yy.hou.lz.define

import java.util.*

// 还未开始的
class SingleJob(
  var type: Long,
  var id: Long,
  var name: String,
  var statement: String,
  var st: Date, // 开始时间, 如果是周期性事件无开始结束
  var ed: Date, // 结束时间
  var alarm: Long, // 提前多少分钟提醒
) : Comparable<SingleJob> {
  override fun compareTo(other: SingleJob): Int {
    return if (this.st.time != other.st.time) {
      if (this.st.before(other.st)) -1 else 1
    } else {
      if (this.ed.time == other.ed.time) 0
      else {
        if (this.ed.before(other.ed)) -1 else 1
      }
    }
  }
}