package work.idontlike.idontlikework;

/**
 * Created by rentomojo on 4/13/18.
 */

public class Constants {
  private static final String API_HOST = "https://s3.ap-south-1.amazonaws.com/idontlikework/wfh-reasons.json";
  public static final String SHEETS_URL = API_HOST;

  public static class BundleKeys {
    public static final String REASON_TEXT = "reasonText";
    public static final String REASON_ARRAY_LIST = "reasonArrayList";
  }
}
