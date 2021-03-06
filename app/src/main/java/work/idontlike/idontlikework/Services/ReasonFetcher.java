package work.idontlike.idontlikework.Services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import work.idontlike.idontlikework.Constants;
import work.idontlike.idontlikework.Models.Reason;

/**
 * Created by rentomojo on 4/13/18.
 */

public class ReasonFetcher extends AsyncTask<Void, Void, JSONArray> {

  OnCompleteListener onCompleteListener = null;
  OnPreExecuteListener onPreExecuteListener = null;

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    if (onPreExecuteListener != null) {
      onPreExecuteListener.onPreExecute();
    }
  }

  @Override
  protected JSONArray doInBackground(Void... voids) {
    URL url = null;
    try {
      url = new URL(Constants.SHEETS_URL);

      HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
      urlConnection.setReadTimeout(30000);
      urlConnection.setConnectTimeout(30000);

      String response = "", line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      while ((line = reader.readLine()) != null) {
        response = response.concat(line);
      }

      JSONArray result = new JSONArray(response);
      Log.i("ReasonFetcher:result", result.toString());
      return result;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


  @Override
  protected void onPostExecute(JSONArray jsonArray) {
    super.onPostExecute(jsonArray);

    if(jsonArray == null){
      if (onCompleteListener != null) {
        onCompleteListener.onComplete(null);
      }
      return;
    }

    ArrayList<Reason> reasons = new ArrayList<>();


    for(int i = 0 ; i < jsonArray.length(); i++){
      try {
        reasons.add(Reason.parseJSON(jsonArray.getJSONObject(i)));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    if (onCompleteListener != null) {
      onCompleteListener.onComplete(reasons);
    }


  }


  public interface OnCompleteListener {
    void onComplete(ArrayList<Reason> reasons);
  }

  public interface OnPreExecuteListener {
    void onPreExecute();
  }

  public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
    this.onCompleteListener = onCompleteListener;
  }

  public void setOnPreExecuteListener(OnPreExecuteListener onPreExecuteListener) {
    this.onPreExecuteListener = onPreExecuteListener;
  }
}
