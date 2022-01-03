package xx.yy.hou.lz.define

import java.io.Serializable

open class Job(
  var type: Long = -1,
  var id: Long = -1,
  var name: String = "",
  var statement: String = "",
): Serializable {

  override fun toString(): String {
    return "Job(type=$type, id=$id, name='$name', statement='$statement')"
  }
}