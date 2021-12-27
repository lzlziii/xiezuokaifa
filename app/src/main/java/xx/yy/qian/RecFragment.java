package xx.yy.qian;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
    Context context = getActivity();
    public List<Thing> thinglist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载新闻布局
        view = inflater.inflate(R.layout.rec_fragment,container,false);

        recyclerView = view.findViewById(R.id.rv_successive_dynasties_things);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        Connector.getDatabase();
        thinglist = DataSupport.findAll(Thing.class);
        recyclerAdapter = new RecyclerAdapter(thinglist);
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }
    public void refresh(){
        thinglist = DataSupport.findAll(Thing.class);
        recyclerAdapter = new RecyclerAdapter(thinglist);
        recyclerView.setAdapter(recyclerAdapter);
    }
}

