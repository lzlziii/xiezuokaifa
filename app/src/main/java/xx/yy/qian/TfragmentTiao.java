package xx.yy.qian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.view.Histogram;


public class TfragmentTiao extends Fragment {
    private View view;
    float[] ydata = new float[]{1,2,3,4,5,6,7};
    String[] xdata = new String[]{"06-11","06-12","06-13","06-13","06-13","06-12","06-12"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.tiaoxingtu, container, false);

//        Histogram histogramChart = (Histogram) view.findViewById(R.id.histogramchart);
//        HistogramData histogramData = HistogramData.builder()
//                .setXdata(xdata)//x轴元素
//                .setYdata(ydata)//y轴元素（可用于显示类别）
//                .setYpCount(7)//y轴元素个数(只能为7)
//                .setAnimType(Anim.ANIM_ALPHA)
//                .build();
//        histogramChart.setChartData(histogramData);

        return view;
    }
}
