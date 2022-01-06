package xx.yy.qian.fragment;

import static xx.yy.hou.lz.queue.JobOperationKt.getAllJob;
import static xx.yy.hou.lz.queue.JobOperationKt.getJobTypeNameById;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.data.PieEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import xx.yy.hou.lz.define.Job;
import xx.yy.hou.lz.define.SingleJob;
import xx.yy.hou.lz.define.XJob;
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
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//        ft.replace(R.id.tfragment,new MPFragmentPiez(getAllJob())).commit();

        button1=view.findViewById(R.id.buttont1);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                    List<PieEntry> strings = new ArrayList<PieEntry>();
                    List<Job> jobs = getAllJob();
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String string = dateFormat.format(date);//获取的时间为String类型
                Log.d("ceshi",string);
                Date date2 = null;//转换为date类型
                try {
                    date2 = dateFormat.parse(string);//今天一早
                    Date date3=new Date(date2.getTime()+24L*60*60*1000);
                    TreeMap<String, Long> x = new TreeMap<>();
                    for (Job job : jobs) {
                        if (job instanceof SingleJob) {
                            SingleJob sj = (SingleJob) job;
                            if (sj.getSt().after(date2) && sj.getEd().before(date3)) {
                                String name = getJobTypeNameById(sj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }
                        } else if (job instanceof XJob) {
                            XJob xj = (XJob) job;
                            if (xj.getSt().after(date2) && xj.getEd().before(date3)) {
                                String name = getJobTypeNameById(xj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }

                        }
                    }//删去没有时间的周期类
                    for (Map.Entry<String, Long> i : x.entrySet()) {
                        float ff = Float.parseFloat(i.getValue().toString());
                        strings.add(new PieEntry(ff, i.getKey()));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                //Log.d("ceshi",strings.toString());
                    ft.replace(R.id.tfragment, new MPFragmentPiez((ArrayList<PieEntry>) strings)).commit();
                }
        });

        button2=view.findViewById(R.id.buttont2);
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                List<PieEntry> strings = new ArrayList<PieEntry>();
                List<Job> jobs = getAllJob();
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-ww");
                String string = dateFormat.format(date);//获取的时间为String类型
                Date date2 = null;//转换为date类型
                try {
                    date2 = dateFormat.parse(string);
                    Date date3=new Date(date2.getTime()+24L*60*60*1000*7);
                    TreeMap<String, Long> x = new TreeMap<>();
                    for (Job job : jobs) {
                        if (job instanceof SingleJob) {
                            SingleJob sj = (SingleJob) job;
                            if (sj.getSt().after(date2) && sj.getEd().before(date3)) {
                                String name = getJobTypeNameById(sj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }
                        } else if (job instanceof XJob) {
                            XJob xj = (XJob) job;
                            if (xj.getSt().after(date2) && xj.getEd().before(date3)) {
                                String name = getJobTypeNameById(xj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }

                        }
                    }//删去没有时间的周期类
                    for (Map.Entry<String, Long> i : x.entrySet()) {
                        float ff = Float.parseFloat(i.getValue().toString());
                        strings.add(new PieEntry(ff, i.getKey()));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                //Log.d("ceshi",strings.toString());
                ft.replace(R.id.tfragment, new MPFragmentPiez((ArrayList<PieEntry>) strings)).commit();
            }
        });

        button3=view.findViewById(R.id.buttont3);
        button3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                List<PieEntry> strings = new ArrayList<PieEntry>();
                List<Job> jobs = getAllJob();
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String string = dateFormat.format(date);//获取的时间为String类型
                Date date2 = null;//转换为date类型
                try {
                    date2 = dateFormat.parse(string);//本月初
                    Date date4=new Date(date2.getTime()+24L*60*60*1000*31);//下个月的某一天
                    String string2 = dateFormat.format(date4);
                    Date date3=dateFormat.parse(string2);
                    TreeMap<String, Long> x = new TreeMap<>();
                    for (Job job : jobs) {
                        if (job instanceof SingleJob) {
                            SingleJob sj = (SingleJob) job;
                            if (sj.getSt().after(date2) && sj.getEd().before(date3)) {
                                String name = getJobTypeNameById(sj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }
                        } else if (job instanceof XJob) {
                            XJob xj = (XJob) job;
                            if (xj.getSt().after(date2) && xj.getEd().before(date3)) {
                                String name = getJobTypeNameById(xj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }

                        }
                    }//删去没有时间的周期类
                    for (Map.Entry<String, Long> i : x.entrySet()) {
                        float ff = Float.parseFloat(i.getValue().toString());
                        strings.add(new PieEntry(ff, i.getKey()));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                //Log.d("ceshi",strings.toString());
                ft.replace(R.id.tfragment, new MPFragmentPiez((ArrayList<PieEntry>) strings)).commit();
            }

        });

        button4=view.findViewById(R.id.buttont4);
        button4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                List<PieEntry> strings = new ArrayList<PieEntry>();
                List<Job> jobs = getAllJob();
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                String string = dateFormat.format(date);//获取的时间为String类型
                Date date2 = null;//转换为date类型
                try {
                    date2 = dateFormat.parse(string);//本月初
                    Date date4=new Date(date2.getTime()+24L*60*60*1000*31*12);//下年的某一天
                    String string2 = dateFormat.format(date4);
                    Date date3=dateFormat.parse(string2);
                    TreeMap<String, Long> x = new TreeMap<>();
                    for (Job job : jobs) {
                        if (job instanceof SingleJob) {
                            SingleJob sj = (SingleJob) job;
                            if (sj.getSt().after(date2) && sj.getEd().before(date3)) {
                                String name = getJobTypeNameById(sj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }
                        } else if (job instanceof XJob) {
                            XJob xj = (XJob) job;
                            if (xj.getSt().after(date2) && xj.getEd().before(date3)) {
                                String name = getJobTypeNameById(xj.getId());
                                x.put(name, x.getOrDefault(name, 0L) + 1);
                            }

                        }
                    }//删去没有时间的周期类
                    for (Map.Entry<String, Long> i : x.entrySet()) {
                        float ff = Float.parseFloat(i.getValue().toString());
                        strings.add(new PieEntry(ff, i.getKey()));
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                //Log.d("ceshi",strings.toString());
                ft.replace(R.id.tfragment, new MPFragmentPiez((ArrayList<PieEntry>) strings)).commit();
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

