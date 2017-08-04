package com.example.conversations;

import android.app.Application;
import android.content.Context;

import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.BVLogLevel;
import com.bazaarvoice.bvandroidsdk.BVSDK;
import com.bazaarvoice.bvandroidsdk.BazaarEnvironment;

public class App extends Application {
  private BVSDK bvsdk;
  private BVConversationsClient bvClient;

  @Override
  public void onCreate() {
    super.onCreate();
    bvsdk = BVSDK.builder(this, BazaarEnvironment.STAGING)
        .logLevel(BVLogLevel.VERBOSE)
        .build();
    bvClient = new BVConversationsClient.Builder(bvsdk).build();
  }

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }

  public BVConversationsClient getBvClient() {
    return bvClient;
  }
}