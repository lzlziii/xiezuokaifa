package xx.yy.qian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import xx.yy.hou.login.Check;

public class PreActivity extends AppCompatActivity {

  public static final int RESULT_REGISTER = 123;

  public static void debug(Object x) {
    Log.e("LZ", x.toString());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pre);

    Objects.requireNonNull(getSupportActionBar()).hide();

    CheckBox c1 = findViewById(R.id.checkBox);
    CheckBox c2 = findViewById(R.id.checkBox2);
    c2.setOnClickListener(v -> c1.setChecked(true));

    Button loginButton = findViewById(R.id.loginButton);
    loginButton.setOnClickListener(v -> {

      debug("jin");

      String account = Objects.requireNonNull(((TextInputEditText) findViewById(R.id.inputText1)).getText()).toString();
      String password = Objects.requireNonNull(((TextInputEditText) findViewById(R.id.inputText2)).getText()).toString();
      if (Check.check(account, password)) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
      } else {
        debug("bu xing");
      }
    });

    Button registerButton = findViewById(R.id.registerButton);
    registerButton.setOnClickListener(v -> {
      Intent intent = new Intent(this, RegisterActivity.class);
      this.startActivityForResult(intent, RESULT_REGISTER);
    });

  }
}



