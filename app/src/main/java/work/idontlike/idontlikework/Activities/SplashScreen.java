package work.idontlike.idontlikework.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;

import work.idontlike.idontlikework.R;
import work.idontlike.idontlikework.Services.ReasonFetcher;

public class SplashScreen extends AppCompatActivity {

  LinearLayout loadingView;
  Button showReasonButton;
  boolean isReasonButtonVisible = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    loadingView = findViewById(R.id.loading_view);
    showReasonButton = findViewById(R.id.show_reason_button);

    ReasonFetcher reasonFetcher = new ReasonFetcher();
    reasonFetcher.setOnCompleteListener(new ReasonFetcher.OnCompleteListener() {
      @Override
      public void onComplete(JSONArray jsonArray) {
        showReasonButton.post(new Runnable() {
          @Override
          public void run() {
            isReasonButtonVisible = true;
            showReasonButton.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
          }
        });
      }
    });

    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(15000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(!isReasonButtonVisible){
          showReasonButton.post(new Runnable() {
            @Override
            public void run() {
              showReasonButton.setVisibility(View.VISIBLE);
              loadingView.setVisibility(View.GONE);
            }
          });
        }

      }
    };

    Thread thread = new Thread(runnable);
    thread.start();

    reasonFetcher.execute();

    showReasonButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(SplashScreen.this, Reasons.class));
        finish();
        overridePendingTransition(R.anim.reason_activity_enter, R.anim.splash_screen_exit);
      }
    });
  }
}
