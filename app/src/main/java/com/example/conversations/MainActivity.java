package com.example.conversations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.conversations.reviews.DisplayReviewsActivity;
import com.example.conversations.reviews.SubmitReviewsActivity;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.displayReviewsButton)
        .setOnClickListener(v -> DisplayReviewsActivity.transitionTo(MainActivity.this));
    findViewById(R.id.submitReviewButton)
        .setOnClickListener(v -> SubmitReviewsActivity.transitionTo(MainActivity.this));
  }
}
