package xx.yy.hou.lz.aaa

import xx.yy.hou.lz.define.LeiXing
import java.util.*

object LX {

  var leiXing = TreeMap<Long, LeiXing>().apply {
    put(0L, LeiXing(0L, -1L, "所有类型"))
    put(1L, LeiXing(1L, 0L, "学习"))
    put(2L, LeiXing(2L, 0L, "运动"))
    put(3L, LeiXing(3L, 0L, "游戏"))
  }

  var leiXingLink = TreeMap<Long, TreeSet<Long>>().apply {
    put(0L, TreeSet<Long>().apply {
      for (i in 1L..3L) add(i)
    })
  }

}