package work.idontlike.idontlikework.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hyogeun.gradationpager.GradationViewPager;

import java.util.ArrayList;
import java.util.Arrays;

import work.idontlike.idontlikework.Adapters.ReasonFragmentAdapter;
import work.idontlike.idontlikework.Constants;
import work.idontlike.idontlikework.Models.Reason;
import work.idontlike.idontlikework.R;

public class Reasons extends AppCompatActivity {

  GradationViewPager viewPager;
  ArrayList<Reason> reasons;
  ConstraintLayout constraintLayout;
  TextView headerTextView;
  Button copyButton;
  int reasonCount = 1;

  int selectedReason = 0;

  String[] dummyReasons = new String[]{"Cat died after eating marijuna", "Ate too much spicy food last night", "My cylinder is leaking. Need to get it fixed", "Reason 4", "Reason 5", "Reason 6", "Reason 7", "Reason 8", "Reason 9"};
  int[] colorIdStore = new int[]{R.color.colorAccent2, R.color.colorAccent, R.color.colorPrimary};
  int[] colorStore = new int[]{Color.parseColor("#ffab40"), Color.parseColor("#db504a"), Color.parseColor("#19da4f"),
      Color.parseColor("#40A379"), Color.parseColor("#F4555D"), Color.parseColor("#3A7290"),
      Color.parseColor("#795548"), Color.parseColor("#607D8B"), Color.parseColor("#9CCC65"),
      Color.parseColor("#78586F")
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reasons);

    viewPager = findViewById(R.id.viewpager);
    copyButton = findViewById(R.id.copy_reason_button);
    headerTextView = findViewById(R.id.headerTextView);
    constraintLayout = findViewById(R.id.constraintLayout);


    reasons = (ArrayList<Reason>) getIntent().getSerializableExtra(Constants.BundleKeys.REASON_ARRAY_LIST);
    reasonCount = reasons.size();

    final int[] colorList = new int[reasonCount];
    for (int i = 0; i < reasonCount; i++) {
//      colorList[i] = getResources().getColor(colorIdStore[i % colorIdStore.length], null);
      colorList[i] =colorStore[i%colorStore.length];
    }

    ReasonFragmentAdapter adapter = new ReasonFragmentAdapter(this, reasons);
    viewPager.setAdapter(adapter);
    viewPager.setBackGroundColors(colorList);

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        position = position - 1;
        if(position < 0){
          position = 0;
        }
        if(position >= reasonCount){
          position = 0;
        }

        Log.e("Reason count", "" + reasonCount + " " + selectedReason + " " + position);
        selectedReason = (position)%reasonCount;
        copyButton.setTextColor(colorList[selectedReason]);
        headerTextView.setTextColor(colorList[selectedReason]);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });

    constraintLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        viewPager.setCurrentItem((selectedReason+1)%reasons.size());
      }
    });

    copyButton.setTextColor(colorList[0]);
    headerTextView.setTextColor(colorList[0]);



    copyButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copy Text", reasons.get(selectedReason).getText());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(Reasons.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
      }
    });



  }

}
