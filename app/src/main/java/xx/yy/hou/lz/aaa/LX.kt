package xx.yy.hou.lz.aaa

import xx.yy.hou.lz.define.LeiXing

object LX {
  var leiXing = ArrayList<LeiXing>().apply {
    // 调试使用数据
    this.add(LeiXing(0L, -1L, "所有类型"))
    this.add(LeiXing(1L, 0L, "学习"))
    this.add(LeiXing(2L, 0L, "运动"))
    this.add(LeiXing(3L, 0L, "游戏"))
  }
}