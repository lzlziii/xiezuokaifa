package xx.yy.qian;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MPFragmentPie extends Fragment {
    private View view;
    private Button buttonst;
    private Button buttoned;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TimePicker timepicker;
    private DatePicker datepicker;

    public MPFragmentPie() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.mppie, container, false);
        View dialog1 = getLayoutInflater().inflate(R.layout.dialog_view, null);
        timepicker = dialog1.findViewById(R.id.time_picker);
        datepicker = dialog1.findViewById(R.id.date_picker);
        buttonst=view.findViewById(R.id.buttonst);
        buttoned=view.findViewById(R.id.buttoned);
        textView1=view.findViewById(R.id.textView4);
        textView3=view.findViewById(R.id.textView44);
        textView2=view.findViewById(R.id.textView5);
        textView4=view.findViewById(R.id.textView55);
        Calendar calendar=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();
        final int[] tyear = new int[2];
        final int[] tmonth = new int[2];
        final int[] tday = new int[2];
        final int[] hour = new int[2];
        final int[] mine = new int[2];
        tyear[0] =tyear[1] =calendar.get(Calendar.YEAR);
        tmonth[0] =tmonth[1] =calendar.get(Calendar.MONTH);
        tday[0] =tday[1] =calendar.get(Calendar.DATE);
        hour[0] =hour[1] =calendar.get(Calendar.HOUR);
        mine[0] =mine[1] =calendar.get(Calendar.MINUTE);
        datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            tyear[0] =year;
            tmonth[0] =monthOfYear;
            tday[0] =dayOfMonth;
        }
        });

        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            hour[0] =hourOfDay;
            mine[0] =minute;
        }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("选择时间")
                .setView(dialog1)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        calendar.set(tyear[0],tmonth[0],tday[0],hour[0],mine[0]);
                        calendar2.set(tyear[1],tmonth[1],tday[1],hour[1],mine[1]);
                        textView1.setText(sdf.format(calendar.getTime()));
                        if(textView3.getVisibility()==View.VISIBLE&&textView4.getVisibility()==View.VISIBLE)
                        {
                            //选择好了开始结束时间
                            //Log.d("ceshi","asdfasd");
                        }
                    }
                });
        AlertDialog alert1 = builder.create();

        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("选择时间")
                .setView(dialog1)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        calendar.set(tyear[0],tmonth[0],tday[0],hour[0],mine[0]);
                        calendar2.set(tyear[1],tmonth[1],tday[1],hour[1],mine[1]);
                        textView2.setText(sdf.format(calendar.getTime()));
                        if(textView3.getVisibility()==View.VISIBLE&&textView4.getVisibility()==View.VISIBLE)
                        {
                            //选择好了开始结束时间
                            //Log.d("ceshi","asdfasd");
                        }
                    }
                });
        AlertDialog alert2 = builder2.create();


        buttonst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((FrameLayout)dialog1.getParent())!=null)
                    ((FrameLayout)dialog1.getParent()).removeAllViews();
                alert1.show();
                buttonst.setVisibility(View.GONE);
                textView3.setVisibility(View.VISIBLE);
            }
        });

        buttoned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((FrameLayout)dialog1.getParent())!=null)
                    ((FrameLayout)dialog1.getParent()).removeAllViews();
                alert2.show();
                buttoned.setVisibility(View.GONE);
                textView4.setVisibility(View.VISIBLE);
            }
        });

        PieChart picChart=view.findViewById(R.id.pic_chart);
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(10f,"bbb"));
        strings.add(new PieEntry(10f,"ccc"));
        strings.add(new PieEntry(10f,"ddd"));
        strings.add(new PieEntry(40f,"eee"));//数据项

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
