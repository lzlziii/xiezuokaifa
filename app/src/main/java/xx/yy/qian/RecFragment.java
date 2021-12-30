package xx.yy.qian;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class RecFragment extends Fragment {
    private View view;
    private static RecyclerView recyclerView;
    private static Adapter1 adapter1;
    private static Adapter2 adapter2;
    private static Adapter3 adapter3;
    private static Adapter6 adapter4;
    private static Adapter6 adapter5;
    private static Adapter6 adapter6;
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
        view = inflater.inflate(R.layout.rec_fragment2,container,false);

        recyclerView = view.findViewById(R.id.thingsrecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        Connector.getDatabase();
        thinglist = DataSupport.findAll(Thing.class);
        adapter1 = new Adapter1(thinglist);
        recyclerView.setAdapter(adapter1);

        button1=view.findViewById(R.id.buttont1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter1 = new Adapter1(thinglist);
                recyclerView.setAdapter(adapter1);
            }
        });

        button2=view.findViewById(R.id.buttont2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter2 = new Adapter2(thinglist);
                recyclerView.setAdapter(adapter2);
            }
        });

        button3=view.findViewById(R.id.buttont3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter3 = new Adapter3(thinglist);
                recyclerView.setAdapter(adapter3);
            }
        });

        button4=view.findViewById(R.id.buttont4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter4 = new Adapter6(thinglist);
                recyclerView.setAdapter(adapter4);
            }
        });

        button5=view.findViewById(R.id.buttont5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter5 = new Adapter6(thinglist);
                recyclerView.setAdapter(adapter5);
            }
        });

        button6=view.findViewById(R.id.buttont6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thinglist = DataSupport.findAll(Thing.class);
                adapter6 = new Adapter6(thinglist);
                recyclerView.setAdapter(adapter6);
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

