package xx.yy.qian.fragment;

import static xx.yy.hou.lz.queue.JobOperationKt.getAllJob;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import xx.yy.qian.R;

public class TotalFragment extends Fragment {
    private View view;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    //public List<Thing> thinglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.tongji,container,false);
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.tfragment,new MPFragmentPiez(getAllJob())).commit();

        button1=view.findViewById(R.id.buttont1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.replace(R.id.tfragment, new MPFragmentPiez(getAllJob())).commit();
                }
        });

        button2=view.findViewById(R.id.buttont2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new MPFragmentPiez(getAllJob())).commit();
            }
        });

        button3=view.findViewById(R.id.buttont3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new MPFragmentPiez(getAllJob())).commit();
            }
        });

        button4=view.findViewById(R.id.buttont4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new MPFragmentPiez(getAllJob())).commit();
            }
        });

        button5=view.findViewById(R.id.buttont5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.tfragment,new MPFragmentPie(getAllJob())).commit();
            }
        });

        return view;
    }
}

