package xx.yy.hou.lz.leixing

import java.io.Serializable

class LeiXing( // 0是根类型
  var id: Int,
  var parentId: Int = 0,
  var name: String
): Serializable

var leiXing = ArrayList<LeiXing>()
