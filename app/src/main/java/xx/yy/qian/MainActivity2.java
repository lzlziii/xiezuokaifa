package xx.yy.qian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.data.PieChartData;
import com.zqx.chart.event.OnPieItemClickListener;
import com.zqx.chart.view.Histogram;
import com.zqx.chart.view.PieChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private View view;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    public List<Thing> thinglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tongji);

        button1=findViewById(R.id.buttont1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ceshi","aaaaaaaaaaaaaaaaaaaaaa");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new TfragmentPie()).commit();
            }
        });

        button2=findViewById(R.id.buttont2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new TfragmentTiao()).commit();
            }
        });

        button3=findViewById(R.id.buttont3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button4=findViewById(R.id.buttont4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button5=findViewById(R.id.buttont5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button6=findViewById(R.id.buttont6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
//        int[] colors = new int[]{Color.RED,Color.BLACK,Color.BLUE,Color.GREEN,Color.GRAY,
//                Color.YELLOW,Color.LTGRAY,Color.CYAN, Color.MAGENTA};
//        float[] datas={1,2,3};
//        PieChartData pieChartData = PieChartData.builder()
//                .setDatas(datas)
//                //.setColors(colors)
//                .setTextColor(Color.RED)
//                //.setTextSize(36)
//                //.setSeparationDegree(3)
//                .setPieItemClickListener(new OnPieItemClickListener() {
//                    @Override
//                    public void onPieItemClick(int position) {
//                        Toast.makeText(MainActivity2.this,"click->"+position,Toast.LENGTH_SHORT).show();
//                    }
//                }).build();
//        pieChart.setChartData(pieChartData);

//        LinearLayout layout=findViewById(R.id.layout1);
//        for(int i=0;i<25;i++)
//        {
//            TextView textView = new TextView(this);
//            textView.setText("测试");
//            layout.addView(textView);
//        }
//        LinearLayout layout2=findViewById(R.id.layout2);
//        for(int i=0;i<25;i++)
//        {
//            TextView textView = new TextView(this);
//            textView.setText("测试");
//            layout2.addView(textView);
//        }
//        LinearLayout layout3=findViewById(R.id.layout3);
//        for(int i=0;i<25;i++)
//        {
//            TextView textView = new TextView(this);
//            textView.setText("测试");
//            layout3.addView(textView);
//        }
    }


}