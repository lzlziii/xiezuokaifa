package xx.yy.qian;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

  private EditText editText;
  private ImageView imageButton1;
  private ImageView imageButton2;
  private ImageView imageButton3;
  private TimePicker timepicker;
  private DatePicker datepicker;
  private TimePicker timepicker2;
  private DatePicker datepicker2;
  Context context = this;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.recfragment, new RecFragment(), "RecFragment")
            .commit();

    Toolbar toolbar = findViewById(R.id.toolbar);

    View dialog = getLayoutInflater().inflate(R.layout.dialog_view, null);
    View dialog2 =getLayoutInflater().inflate(R.layout.dialog, null);
    editText = dialog.findViewById(R.id.editText1);
    timepicker = dialog.findViewById(R.id.time_picker);
    datepicker = dialog.findViewById(R.id.date_picker);
    //timepicker2 = dialog.findViewById(R.id.time_picker2);
    //datepicker2 = dialog.findViewById(R.id.date_picker2);

    imageButton1=findViewById(R.id.tabimg1);
    imageButton2=findViewById(R.id.tabimg2);
    imageButton3=findViewById(R.id.tabimg3);
    imageButton1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageButton1.setImageDrawable(getResources().getDrawable(R.drawable.chose));
        imageButton2.setImageDrawable(getResources().getDrawable(R.drawable.type));
        imageButton3.setImageDrawable(getResources().getDrawable(R.drawable.total));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.recfragment, new RecFragment(), "RecFragment").commit();
        toolbar.setNavigationIcon(R.drawable.ic_baseline_add_24);
      }
    });
    imageButton2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageButton1.setImageDrawable(getResources().getDrawable(R.drawable.rec));
        imageButton2.setImageDrawable(getResources().getDrawable(R.drawable.chose));
        imageButton3.setImageDrawable(getResources().getDrawable(R.drawable.total));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.recfragment,new TypeFragment()).commit();
        toolbar.setNavigationIcon(null);
      }
    });
    imageButton3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        imageButton1.setImageDrawable(getResources().getDrawable(R.drawable.rec));
        imageButton2.setImageDrawable(getResources().getDrawable(R.drawable.type));
        imageButton3.setImageDrawable(getResources().getDrawable(R.drawable.chose));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.recfragment,new TotalFragment()).commit();
        toolbar.setNavigationIcon(null);
      }
    });


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
//    datepicker2.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//      @Override
//      public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        tyear[1] =year;
//        tmonth[1] =monthOfYear;
//        tday[1] =dayOfMonth;
//      }
//    });
//
//    timepicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
//      @Override
//      public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//        hour[1] =hourOfDay;
//        mine[1] =minute;
//      }
//    });

    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle("添加事务")
            .setView(dialog)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Thing thing = new Thing("ceshi001");
                thing.setDetil(editText.getText().toString());
                calendar.set(tyear[0],tmonth[0],tday[0],hour[0],mine[0]);
                calendar2.set(tyear[1],tmonth[1],tday[1],hour[1],mine[1]);
                LinearLayout layout=(LinearLayout)dialog2.findViewById(R.id.layout01);
                TextView te=new TextView(context);
                te.setText(sdf.format(calendar.getTime()));
                layout.addView(te);

                //thing.setCal(sdf.format(calendar.getTime()));
                //thing.setCal2(sdf.format(calendar2.getTime()));
                //thing.save();
                //((RecFragment) getSupportFragmentManager().findFragmentByTag("RecFragment")).refresh();
                //getSupportFragmentManager().beginTransaction().replace(R.id.left_fragment, new RecFragment()).commit();

              }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                //取消不做任何处理
              }
            });
    AlertDialog alert1 = builder.create();

    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
    builder.setTitle("添加事务")
            .setView(dialog2)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

              }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                //取消不做任何处理
              }
            });
    AlertDialog alert = builder.create();
    dialog2.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        alert1.show();
      }
    });
    dialog2.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        alert1.show();
      }
    });

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        alert.show();
      }
    });
    //DataSupport.deleteAll(Thing.class);

  }

}
