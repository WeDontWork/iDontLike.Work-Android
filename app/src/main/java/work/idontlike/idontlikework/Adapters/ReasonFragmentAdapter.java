package work.idontlike.idontlikework.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import work.idontlike.idontlikework.Constants;
import work.idontlike.idontlikework.Fragments.ReasonFragment;
import work.idontlike.idontlikework.Models.Reason;
import work.idontlike.idontlikework.R;

/**
 * Created by rentomojo on 4/13/18.
 */
public class ReasonFragmentAdapter extends PagerAdapter{

  Context context;
  LayoutInflater layoutInflater;
  private ArrayList<Reason> reasons = new ArrayList<>();


  public ReasonFragmentAdapter( /* FragmentManager fm, */Context context, ArrayList<Reason> reasons) {
//    super(fm);
    this.context = context;
    this.reasons = reasons;

    layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }


  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    ConstraintLayout layout = (ConstraintLayout) layoutInflater.inflate(R.layout.fragment_reason, container, false);
    TextView reasonTextView = layout.findViewById(R.id.reason_text);
    container.addView(layout);
    reasonTextView.setText(reasons.get(position).getText().concat(".\nWill be working from home"));

    return layout;
  }

  @Override
  public int getCount() {
    return reasons.size();
  }
//
//  @Override
//  public Fragment getItem(int position) {
//    String reason = reasons.get(position);
//
//    Fragment fragment = new ReasonFragment();
//    Bundle bundle = new Bundle();
//    bundle.putString(Constants.BundleKeys.REASON_TEXT, reason);
//    fragment.setArguments(bundle);
//    return fragment;
//  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view.getClass() == object.getClass();
  }
}