package com.anhmantk;
import com.apportable.activity.VerdeActivity;
import com.facebook.*;
import com.facebook.FacebookSdk;
import com.facebook.login.*;
import com.facebook.login.LoginResult;

import com.apportable.utils.ThreadUtils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.os.Handler;
import android.graphics.Bitmap;

public class FacebookConnect extends Activity {
    private static final String TAG = "MSG";
    private int loginStatus;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //not run here
        super.onCreate(savedInstanceState);
        Log.e(TAG,"==> onCreate");
    }
    
    public void initFB(final Activity activity){
        Log.e(TAG,"start login facebook");
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }
    
    public void loginFB(final Activity activity){
        loginStatus = 10;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends", "email"));
        LoginManager.getInstance().logInWithPublishPermissions(activity, Arrays.asList("publish_actions"));
        Log.e(TAG,"==> loginFB");
        LoginManager.getInstance().registerCallback(callbackManager,
                                                    new FacebookCallback<LoginResult>() {
                                                        @Override
                                                        public void onSuccess(LoginResult loginResult) {
                                                            loginStatus = 1;
                                                            Log.e(TAG, "Login success");
                                                            Log.e(TAG, loginResult.getAccessToken().toString());
                                                            finish();
                                                        }
                                                        
                                                        @Override
                                                        public void onCancel() {
                                                            loginStatus = 2;
                                                            Log.e(TAG, "Login cancel");
                                                            Log.e(TAG, "==> onCancel");
                                                            finish();
                                                        }
                                                        
                                                        @Override
                                                        public void onError(FacebookException exception) {
                                                            loginStatus = 3;
                                                            Log.e(TAG, "Login error");
                                                            Log.e(TAG, "Error: " + exception.getMessage().toString());
                                                        }
                                                    });
    }
    
    public int getLoginStatus() {
        return loginStatus;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"==> onDestroy");
        accessTokenTracker.stopTracking();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG,"==> onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    
}
