package xx.yy.qian;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class TFragment extends Fragment {
    private View view;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    public List<Thing> thinglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.tongji,container,false);


        button1=view.findViewById(R.id.buttont1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ceshi","aaaaaaaaaaaaaaaaaaaaaa");
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new TfragmentPie()).commit();
            }
        });

        button2=view.findViewById(R.id.buttont2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new MPFragmentPie()).commit();
            }
        });

        button3=view.findViewById(R.id.buttont3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button4=view.findViewById(R.id.buttont4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button5=view.findViewById(R.id.buttont5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button6=view.findViewById(R.id.buttont6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        LinearLayout layout=view.findViewById(R.id.layout1);
//        for(int i=0;i<25;i++)
//        {
//            TextView textView = new TextView(getActivity());
//            textView.setText("测试");
//            layout.addView(textView);
//        }

        return view;
    }
    public void refresh(){

    }
}

