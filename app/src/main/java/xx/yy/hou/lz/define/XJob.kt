package xx.yy.hou.lz.define

import java.util.*

class XJob(
  var type: Long,
  var id: Long,
  var name: String,
  var statement: String,
  var st: Date, // 开始时间
  var ed: Date, // 结束时间
) : Comparable<XJob> {
  override fun compareTo(other: XJob): Int {
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