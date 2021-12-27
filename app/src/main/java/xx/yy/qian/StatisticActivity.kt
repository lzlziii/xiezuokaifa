package xx.yy.qian

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import xx.yy.qian.databinding.ActivityStatisticBinding

class StatisticActivity : AppCompatActivity() {

  lateinit var activityStatisticBinding: ActivityStatisticBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityStatisticBinding = ActivityStatisticBinding.inflate(layoutInflater)
    setContentView(activityStatisticBinding.root)
    Log.e("lz", "jin")

    val pieChart = activityStatisticBinding.pieChart
    pieChart.setNoDataText("老哥，我还没吃饭呢，快给我数据")

    val pieEntry1 = PieEntry(10f, "北京")
    val pieEntry2 = PieEntry(40f, "上海")
    val pieEntry3 = PieEntry(15f, "杭州")
    val pieEntry4 = PieEntry(35f, "深圳")
    val list = mutableListOf(pieEntry1, pieEntry2, pieEntry3, pieEntry4)
    val pieDataSet = PieDataSet(list, "")

    //一般有多少项数据，就配置多少个颜色的，少的话会复用最后一个颜色，多的话无大碍
    pieDataSet.colors = mutableListOf(
      Color.parseColor("#feb64d"),
      Color.parseColor("#ff7c7c"),
      Color.parseColor("#9287e7"),
      Color.parseColor("#60acfc")
    )

    val pieData = PieData(pieDataSet)

    pieChart.data = pieData

    //显示值格式化，这里使用Api,添加百分号
    pieData.setValueFormatter(PercentFormatter())
    //设置值得颜色
    pieData.setValueTextColor(Color.parseColor("#FFFFFF"))
    //设置值得大小
    pieData.setValueTextSize(15f)

    val description = Description()

    description.text = ""
    //把右下边的Description label 去掉，同学也可以设置成饼图说明
    pieChart.description = description

    //去掉中心圆，此时中心圆半透明
    pieChart.holeRadius = 0f
    //去掉半透明
    pieChart.setTransparentCircleAlpha(0)

    pieChart.setDrawEntryLabels(true)

    pieChart.invalidate()

    pieChart.setEntryLabelTextSize(15F)
  }
}