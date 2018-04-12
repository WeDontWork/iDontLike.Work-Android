package work.idontlike.idontlikework.Activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import work.idontlike.idontlikework.R;

public class SplashScreen extends AppCompatActivity {

  LinearLayout loadingView;
  Button showReasonButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    loadingView = findViewById(R.id.loading_view);
    showReasonButton = findViewById(R.id.show_reason_button);


    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        showReasonButton.post(new Runnable() {
          @Override
          public void run() {
           showReasonButton.setVisibility(View.VISIBLE);
           loadingView.setVisibility(View.GONE);
          }
        });
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
  }
}
