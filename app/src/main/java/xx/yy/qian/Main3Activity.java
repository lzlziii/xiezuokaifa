package xx.yy.qian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private List<String> list =new ArrayList<>();
    private List<String> list2 =new ArrayList<>();
    private List<String> list3 =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        list.add("java");
        list.add("cpp");

        list2.add("周一");
        list2.add("周二");
        list2.add("周三");
        list2.add("周四");
        list2.add("周五");
        list2.add("周六");
        list2.add("周日");

        list3.add("周一");
        list3.add("周二");
        list3.add("周三");
        list3.add("周四");
        list3.add("周五");
        list3.add("周六");
        list3.add("周日");

        Spinner spinner=findViewById(R.id.spinner);
        BaseAdapter adapter =new MyAdapter();//adapter适配器
        spinner.setAdapter(adapter);

        Spinner spinner2=findViewById(R.id.spinner2);
        BaseAdapter adapter2 =new MyAdapter2();//adapter适配器
        spinner2.setAdapter(adapter2);

        Spinner spinner3=findViewById(R.id.spinner3);
        BaseAdapter adapter3 =new MyAdapter3();//adapter适配器
        spinner3.setAdapter(adapter3);

        //可以监听器看看
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择了不同的选项，调用这个
                Toast.makeText(Main3Activity.this,list.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //在下拉选项种选择了本来选的东西，也就是说没改变选项，调用这个

            }
        });

    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //return ss.length;
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(Main3Activity.this);
            //textView.setText(ss[position]);
            textView.setText(list.get(position));
            return textView;
        }
    }
    private class MyAdapter2 extends BaseAdapter{

        @Override
        public int getCount() {
            //return ss.length;
            return list2.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(Main3Activity.this);
            //textView.setText(ss[position]);
            textView.setText(list2.get(position));
            return textView;
        }
    }
    private class MyAdapter3 extends BaseAdapter{

        @Override
        public int getCount() {
            //return ss.length;
            return list3.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(Main3Activity.this);
            //textView.setText(ss[position]);
            textView.setText(list3.get(position));
            return textView;
        }
    }
}