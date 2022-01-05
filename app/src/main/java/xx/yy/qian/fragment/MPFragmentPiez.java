package xx.yy.qian.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import xx.yy.hou.lz.define.Job;
import xx.yy.qian.R;

public class MPFragmentPiez extends Fragment {
    private View view;
    List<PieEntry> strings = new ArrayList<PieEntry>();

    public MPFragmentPiez(ArrayList<PieEntry> arrayList)
    {
        //strings.add(new PieEntry(100f,"null"));//add数据项
        //构建函数写进去List
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.mppiez, container, false);

        PieChart picChart=view.findViewById(R.id.pic_chart2);


        PieDataSet dataSet = new PieDataSet(strings,"统计");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.black));
        colors.add(getResources().getColor(R.color.purple_700));
        colors.add(getResources().getColor(R.color.white));
        colors.add(getResources().getColor(R.color.purple_200));
        colors.add(getResources().getColor(R.color.teal_200));//数据背景颜色
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        Description description = new Description();
        description.setText("");
        picChart.setDescription(description);
        picChart.setData(pieData);
        picChart.invalidate();
        //https://www.jianshu.com/p/802118e621d6
        return view;
    }
}
