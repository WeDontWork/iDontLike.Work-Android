package work.idontlike.idontlikework.Models;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rentomojo on 4/13/18.
 */

public class Reason implements Serializable{
  public String text;

  public Reason() {
    super();
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public static Reason parseJSON (JSONObject jsonObject){
    if(jsonObject == null){
      return null;
    }

    Reason reason = new Reason();
    reason.setText(jsonObject.optString("text"));

    return reason;
  }
}
