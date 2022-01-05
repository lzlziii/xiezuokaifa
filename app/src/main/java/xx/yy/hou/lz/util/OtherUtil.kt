package xx.yy.hou.lz.util

import java.util.*
import kotlin.math.absoluteValue

fun generateId(): Long {
  // 取模后相加, 防止爆Long
  return 10000000L + Random().nextLong().absoluteValue % (1L shl 50)
}