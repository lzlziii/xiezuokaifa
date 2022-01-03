package xx.yy.hou.lz.define

import java.io.Serializable
import java.util.*

// 还未开始的
class SingleJob(
  type: Long = -1,
  id: Long = -1,
  name: String = "",
  statement: String = "",
  var st: Date = Date(), // 开始时间, 如果是周期性事件无开始结束
  var ed: Date = Date(), // 结束时间
  var alarm: Long = -1, // 提前多少分钟提醒
) : Job(type, id, name, statement), Serializable, Comparable<SingleJob> {

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

  override fun toString(): String {
    return "SingleJob(type=$type, id=$id, name='$name', statement='$statement', st=$st, ed=$ed, alarm=$alarm)"
  }

}