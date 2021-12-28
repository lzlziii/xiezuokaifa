package xx.yy.qian;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class TypeEditFragment extends Fragment {

  public static TypeEditFragment newInstance(String param1, String param2) {
    TypeEditFragment fragment = new TypeEditFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  public void addRadioButton(RadioGroup rg, String s) {
    RadioButton rb = new RadioButton(rg.getContext());
    rb.setText(s);
    rg.addView(rb);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View x = inflater.inflate(R.layout.fragment_type_edit, container, false);
    RadioGroup rg = x.findViewById(R.id.radioGroup);
    addRadioButton(rg, "sssssss");
    addRadioButton(rg, "alkshfasf");
    addRadioButton(rg, "lkaflsaasdlf");
    addRadioButton(rg, "asdfasdf");
    addRadioButton(rg, "lkjsdf");
    addRadioButton(rg, "kjhsdfskjhfd");
    addRadioButton(rg, "khfdkgjhd");
    return x;
  }
}
