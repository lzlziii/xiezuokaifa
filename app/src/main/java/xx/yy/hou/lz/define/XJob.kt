package xx.yy.hou.lz.define

import java.io.Serializable
import java.util.*

class XJob(
  type: Long,
  id: Long,
  name: String,
  statement: String,
  var st: Date, // 开始时间
  var ed: Date, // 结束时间
) : Job(type, id, name, statement), Serializable, Comparable<XJob> {

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

  override fun toString(): String {
    return "XJob(type=$type, id=$id, name='$name', statement='$statement', st=$st, ed=$ed)"
  }

}