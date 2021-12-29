package xx.yy.qian;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private static RecyclerAdapter recyclerAdapter;
    public List<Thing> thinglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.rec_fragment2,container,false);

//        recyclerView = view.findViewById(R.id.rv_successive_dynasties_things);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        Connector.getDatabase();
//        thinglist = DataSupport.findAll(Thing.class);
//        recyclerAdapter = new RecyclerAdapter(thinglist);
//        recyclerView.setAdapter(recyclerAdapter);

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
//        thinglist = DataSupport.findAll(Thing.class);
//        recyclerAdapter = new RecyclerAdapter(thinglist);
//        recyclerView.setAdapter(recyclerAdapter);
    }
}

