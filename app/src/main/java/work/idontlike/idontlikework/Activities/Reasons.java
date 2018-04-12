package work.idontlike.idontlikework.Activities;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import work.idontlike.idontlikework.R;

public class Reasons extends AppCompatActivity {

  ViewPager viewPager;

  String[] dummyReasons = new String[]{"Reason 1", "Reason 2", "Reason 3", "Reason 4", "Reason 5", "Reason 6", "Reason 7", "Reason 8", "Reason 9"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reasons);

    viewPager = findViewById(R.id.viewpager);

    Bundle bundle = getIntent().getExtras();


  }

  class ReasonAdapter extends PagerAdapter{

    @Override
    public int getCount() {
      return dummyReasons.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
      return false;
    }
  }
}
