package xx.yy.hou.lz.aaa

import android.app.Service
import android.content.Context
import xx.yy.hou.lz.define.LeiXing
import xx.yy.hou.lz.define.PeriodJob
import xx.yy.hou.lz.define.SingleJob
import xx.yy.hou.lz.define.XJob
import xx.yy.hou.lz.util.SerializeUtils
import java.io.*

// 全局id数量
// 一个类型队列
// 六个队列

fun saveAll(context: Context) {
  val output = context.openFileOutput("data", Context.MODE_PRIVATE)
  val writer = BufferedWriter(OutputStreamWriter(output))

  writer.use {
    it.write(SerializeUtils.serialize(ArrayList<Any>().apply {
      add(Cnt.cnt)
      add(LX.leiXing)
      add(Queue.q1)
      add(Queue.q2)
      add(Queue.q4)
      add(Queue.q5)
      add(Queue.q6)
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

    LX.leiXing = ArrayList<LeiXing>().apply {
      for (i in o[1] as ArrayList<*>) this.add(i as LeiXing)
    }

    Queue.q1 = ArrayList<PeriodJob>().apply {
      for (i in o[2] as ArrayList<*>) this.add(i as PeriodJob)
    }

    Queue.q2 = ArrayList<SingleJob>().apply {
      for (i in o[3] as ArrayList<*>) this.add(i as SingleJob)
    }

    Queue.q4 = ArrayList<XJob>().apply {
      for (i in o[4] as ArrayList<*>) this.add(i as XJob)
    }

    Queue.q5 = ArrayList<XJob>().apply {
      for (i in o[5] as ArrayList<*>) this.add(i as XJob)
    }

    Queue.q6 = ArrayList<PeriodJob>().apply {
      for (i in o[5] as ArrayList<*>) this.add(i as PeriodJob)
    }

  }

  return content.toString()
}