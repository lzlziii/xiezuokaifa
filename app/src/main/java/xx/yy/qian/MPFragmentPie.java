package xx.yy.qian;

import android.graphics.Color;
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

public class MPFragmentPie extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.mppie, container, false);

        PieChart picChart=view.findViewById(R.id.pic_chart);
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(10f,"bbb"));
        strings.add(new PieEntry(10f,"bbb"));
        strings.add(new PieEntry(10f,"bbb"));
        strings.add(new PieEntry(40f,"bbb"));//数据项

        PieDataSet dataSet = new PieDataSet(strings,"Label");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.purple_500));
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
