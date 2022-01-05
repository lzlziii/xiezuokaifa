package xx.yy.hou.lz.queue

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*


var YEAR = 0L
var MONTH = 1L
var WEEK = 2L
var DAY = 3L
var HOUR = 4L

@RequiresApi(Build.VERSION_CODES.N)
fun Date.parStrByZhou(x: Long): String {
  val a = arrayListOf("yyyy", "MM", "dd", "EEEE", "HH", "mm")
  val s = when (x) {
    0L -> "MM dd HH mm"
    1L -> "dd HH mm"
    2L -> "EEEE HH mm"
    3L -> "HH mm"
    4L -> "mm"
    else -> ""
  }
  return SimpleDateFormat(s, Locale.ENGLISH).format(this)
}