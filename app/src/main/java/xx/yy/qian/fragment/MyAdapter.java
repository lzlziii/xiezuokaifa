package xx.yy.qian.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xx.yy.qian.activity.MainActivity;

public class MyAdapter extends BaseAdapter {

  public MainActivity mainActivity;
  public List<String> list;

  public MyAdapter(MainActivity mainActivity, int xx) {
    this.mainActivity = mainActivity;
    list = new ArrayList<String>();
    if (xx == 0) {
      list.add("时");
      list.add("日");
      list.add("周");
      list.add("月");
      list.add("年");
    } else {
      list.add("周一");
      list.add("周二");
      list.add("周三");
      list.add("周四");
      list.add("周五");
      list.add("周六");
      list.add("周日");
    }
  }

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
    TextView textView = new TextView(mainActivity);
    textView.setTextSize(20f);
    //textView.setText(ss[position]);
    textView.setText(list.get(position));
    return textView;
  }
}