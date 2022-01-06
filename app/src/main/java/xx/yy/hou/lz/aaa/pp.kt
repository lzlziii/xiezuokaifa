package xx.yy.hou.lz.aaa

import android.app.Service
import android.content.Context
import xx.yy.hou.lz.define.*
import xx.yy.hou.lz.util.SerializeUtils
import xx.yy.hou.lz.util.debug
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


// 全局id数量
// 两个关于类型的STL
// 六个队列

fun saveAll(context: Context) {
  val output = context.openFileOutput("data", Context.MODE_PRIVATE)
  val writer = BufferedWriter(OutputStreamWriter(output))

  writer.use {
    it.write(SerializeUtils.serialize(ArrayList<Any>().apply {
      add(Cnt.cnt)
      add(Pair(LX.leiXing, LX.leiXingLink))
      add(Queue.q)
    }))
  }

}

fun loadAll(service: Service): String {

  val content = StringBuilder()
  val input = service.openFileInput("data")
  val reader = BufferedReader(InputStreamReader(input))
  reader.use {
    while (true) {
      val ch = it.read()
      if (ch == -1) break
      else content.append(ch.toChar())
    }
  }

  val o = SerializeUtils.serializeToObject(content.toString()) as ArrayList<*>

  o.apply {
    Cnt.cnt = o[0] as Long
    LX.leiXing = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).first
    LX.leiXingLink = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).second
    Queue.q = (o[2] as ArrayList<ArrayList<Job>>)
  }

  return content.toString()
}

fun loadAll(context: Context): String {

  val content = StringBuilder()
  val input = context.openFileInput("data")
  val reader = BufferedReader(InputStreamReader(input))
  reader.use {
    while (true) {
      val ch = it.read()
      if (ch == -1) break
      else content.append(ch.toChar())
    }
  }

  val o = SerializeUtils.serializeToObject(content.toString()) as ArrayList<*>

  o.apply {
    Cnt.cnt = o[0] as Long
    LX.leiXing = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).first
    LX.leiXingLink = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).second
    Queue.q = (o[2] as ArrayList<ArrayList<Job>>)
  }

  return content.toString()
}

//
//fun saveAll(context: Context) {
//  val content = SerializeUtils.serialize(ArrayList<Any>().apply {
//    add(Cnt.cnt)
//    add(Pair(LX.leiXing, LX.leiXingLink))
//    add(Queue.q)
//  })
//  Utils.fa("123", "abc", content)
//
//  debug("save :: $content")
//}
//
//fun loadAll(service: Service): String {
//
//  val content = Utils.jie("123", "abc")
//
//  val o = SerializeUtils.serializeToObject(content.toString()) as ArrayList<*>
//
//  o.apply {
//    Cnt.cnt = o[0] as Long
//    LX.leiXing = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).first
//    LX.leiXingLink = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).second
//    Queue.q = (o[2] as ArrayList<ArrayList<Job>>)
//  }
//
//
//  return content.toString()
//}
//
//fun loadAll(context: Context): String {
//
//  val content = Utils.jie("123", "abc")
//
//  debug("load :: $content")
//
//  val o = SerializeUtils.serializeToObject(SerializeUtils.serializeToObject(content.toString()) as String?) as ArrayList<*>
//
//  o.apply {
//    Cnt.cnt = o[0] as Long
//    LX.leiXing = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).first
//    LX.leiXingLink = (o[1] as Pair<TreeMap<Long, LeiXing>, TreeMap<Long, TreeSet<Long>>>).second
//    Queue.q = (o[2] as ArrayList<ArrayList<Job>>)
//  }
//
//  debug("load :: $content")
//
//  return content.toString()
//}