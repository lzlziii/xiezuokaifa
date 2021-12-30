package xx.yy.hou.lz.define

import java.io.Serializable

class LeiXing( // 0是根类型
  var id: Int,
  var parentId: Int = 0,
  var name: String
): Serializable