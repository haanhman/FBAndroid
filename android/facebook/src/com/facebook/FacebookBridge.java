package com.facebook;
import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.net.Uri;
import com.apportable.utils.ThreadUtils;
import com.facebook.*;
import com.facebook.share.*;
import com.facebook.share.widget.*;
import com.facebook.share.model.*;

public class FacebookBridge extends Activity {

    private static final String TAG = "MSG";

    private static Activity act = null;
    private static String facebook_id = null;
    private static String facebook_app = null;
    private static String facebook_link = null;
    private static String facebook_msg = null;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager,
            new FacebookCallback<Sharer.Result>() {
                @Override public void onSuccess(Sharer.Result result)
                {
                    if (getParent() == null) {
                      setResult(Activity.RESULT_OK, null);
                    } else {
                      getParent().setResult(Activity.RESULT_OK, null);
                    }
                    finish();
                }
                @Override public void onCancel()
                {
                    if (getParent() == null) {
                      setResult(Activity.RESULT_CANCELED, null);
                    } else {
                      getParent().setResult(Activity.RESULT_CANCELED, null);
                    }
                    finish();
                }
                @Override public void onError(FacebookException error)
                {
                    Log.e(TAG, "facebook exception: " + error.getMessage());
                    Bundle bundle = new Bundle();
                    bundle.putString("message", error.getMessage()+"");
                    Intent newIntent=new Intent();
                    newIntent.putExtras(bundle);
                    if (getParent() == null) {
                     setResult(Activity.RESULT_CANCELED, newIntent);
                    } else {
                     getParent().setResult(Activity.RESULT_CANCELED, newIntent);
                    }
                    finish();
                }
             });
             started = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (started == false) { // first time
         if (shareDialog.canShow(ShareLinkContent.class)) {
          ShareLinkContent content = new ShareLinkContent.Builder()
            .setContentTitle(facebook_app)
            .setContentUrl(Uri.parse(facebook_link))
            .setContentDescription(facebook_msg)
            .build();
          shareDialog.show(content);
          Log.i(TAG, "sharing: " + facebook_link + " " + facebook_msg);
          started = true;
         } else {
          Bundle bundle = new Bundle();
          bundle.putString("message", "not support share link");
          Intent newIntent=new Intent();
          newIntent.putExtras(bundle);
          if (getParent() == null) {
           setResult(Activity.RESULT_CANCELED, newIntent);
          } else {
           getParent().setResult(Activity.RESULT_CANCELED, newIntent);
          }
          finish();
          Log.e(TAG, "can't sharing: " + facebook_link + " " + facebook_msg);
         }
        }
    }

    @Override
    protected void onActivityResult(final int requestCode,
                        final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static void init(Activity activity, final String id) {
        Log.i(TAG, "init FacebookBridge: " + id);
        act = activity;
        facebook_id = id;
    }

    public static void log(final String msg)
    {
        Log.i(TAG, msg);
    }

    public static void share(final String name, final String url, final String msg)
    {
        facebook_app = name;
        facebook_link = url;
        facebook_msg = msg;
    }
}

