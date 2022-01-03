package xx.yy.hou.lz.define

import java.io.Serializable

class LeiXing( // 0是根类型
  var id: Long,
  var parentId: Long = 0L,
  var name: String = ""
) : Serializable {

  override fun toString(): String {
    return "LeiXing(id=$id, parentId=$parentId, name='${name}')"
  }

}