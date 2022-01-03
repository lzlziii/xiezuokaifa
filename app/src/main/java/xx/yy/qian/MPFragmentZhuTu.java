package xx.yy.qian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MPFragmentZhuTu extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.mpzhuzhaungtu, container, false);
        BarChart mBarChart=view.findViewById(R.id.barChart);

        mBarChart.getDescription().setEnabled(false);
        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawGridBackground(false);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.animateY(2500);
        mBarChart.getLegend().setEnabled(false);//初始化

        setData();
        return view;
    }

    //设置数据
    private void setData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(1, 16));//X和Y的值


        BarDataSet set1;
        BarChart mBarChart=view.findViewById(R.id.barChart);
        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();//刷新数据的方法
        } else {
            set1 = new BarDataSet(yVals1, "日期设置");//每有一组柱子，就有一个BarDataSet
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);//设置多彩 也可以单一颜色
            set1.setDrawValues(true);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);//设置颜色

//            ArrayList<String> da = new ArrayList<String>();
//            da.add("ceshi1");
//            da.add("ceshi2");
//            da.add("ceshi3");

            Description description = new Description();
            description.setEnabled(true);
            mBarChart.setDescription(description);//右下角内容显示true，不显示false

            BarData data = new BarData(dataSets);
            mBarChart.setData(data);
            mBarChart.setFitBars(true);
        }
        mBarChart.invalidate();
    }
    //https://blog.csdn.net/qq_28643195/article/details/112539225
    //http://blog.sina.com.cn/s/blog_1382e29410102y3q7.html
}
