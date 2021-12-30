package xx.yy.qian;

import static xx.yy.hou.lz.util.TimeUtilKt.oWeek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

  public static void debug(Object o) {
    Log.e("lz", o.toString());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    try {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Date now = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("EEEE HH mm", Locale.ENGLISH);
      Date zhou = sdf.parse("Mon 19 30");
      assert zhou != null;
      debug(oWeek(zhou, now));
    } catch (Exception e) {
      e.printStackTrace();
      finish();
    }
  }
}