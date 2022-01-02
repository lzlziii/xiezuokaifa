package xx.yy.qian;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.zqx.chart.data.PieChartData;
import com.zqx.chart.event.OnPieItemClickListener;
import com.zqx.chart.view.PieChart;

public class TfragmentPie extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.bingtu, container, false);

        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        int[] colors = new int[]{Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY,
                Color.YELLOW, Color.LTGRAY, Color.CYAN, Color.MAGENTA};
        float[] datas = {(float) 0.2, (float) 0.2, (float) 0.8};
        PieChartData pieChartData = PieChartData.builder()
                .setDatas(datas)//饼图各部分所占比例
                //.setColors(colors)
                .setTextColor(Color.RED)//饼图中心文字颜色
                //.setTextSize(36)
                //.setSeparationDegree(3)
                .setPieItemClickListener(new OnPieItemClickListener() {
                    @Override
                    public void onPieItemClick(int position) {
                        //点击元素的操作
                    }
                }).build();
        pieChart.setChartData(pieChartData);

        return view;
    }
}
