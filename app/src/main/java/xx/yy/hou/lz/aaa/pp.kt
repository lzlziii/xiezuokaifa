package xx.yy.hou.lz.aaa

import android.app.Service
import android.content.Context
import xx.yy.hou.lz.util.SerializeUtils
import xx.yy.hou.lz.util.debug
import java.io.*

// 全局id数量
// 一个类型队列
// 六个队列

fun saveAll(service: Service) {
  try {
    val output = service.openFileOutput("data", Context.MODE_PRIVATE)
    val writer = BufferedWriter(OutputStreamWriter(output))
    writer.use {
      debug("存入了文件data")
      it.write("asdfasdf")
    }
  } catch (e: IOException) {
    e.printStackTrace()
  }
}

fun loadAll(service: Service): String {
  val content = StringBuilder()
  try {
    val input = service.openFileInput("data")
    val reader = BufferedReader(InputStreamReader(input))
    reader.use {
      while (true) {
        val ch = it.read()
        if (ch == -1) break
        else content.append(ch.toChar())
      }
    }
  } catch (e: IOException) {
    e.printStackTrace()
  }
  val output = content.toString()
  debug(output + " :: " + output.length)
  val o = SerializeUtils.serializeToObject(content.toString())
  debug((o as ArrayList<Int>).toString())
  return content.toString()
}