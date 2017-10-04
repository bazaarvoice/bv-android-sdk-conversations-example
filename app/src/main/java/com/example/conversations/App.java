package com.example.conversations;

import android.app.Application;
import android.content.Context;

import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.BVLogLevel;
import com.bazaarvoice.bvandroidsdk.BVSDK;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class App extends Application {
  private BVSDK bvsdk;
  private BVConversationsClient bvClient;

  @Override
  public void onCreate() {
    super.onCreate();

    bvsdk = BVSDK.builder(this, Constants.BAZAAR_ENVIRONMENT)
        .logLevel(BVLogLevel.VERBOSE)
        .okHttpClient(getOkHttpClient(getLoggingInterceptor()))
        .build();
    
    bvClient = new BVConversationsClient.Builder(bvsdk).build();
  }

  public static App get(Context context) {
    return (App) context.getApplicationContext();
  }

  public BVConversationsClient getBvClient() {
    return bvClient;
  }

  private static OkHttpClient getOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build();
  }

  private static HttpLoggingInterceptor getLoggingInterceptor() {
    final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }
}