package com.example.conversations;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conversations.reviews.DisplayReviewsActivity;
import com.example.conversations.reviews.SubmitReviewActivity;
import com.example.conversations.reviews.ViewFormActivity;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.displayReviewsButton)
        .setOnClickListener(v -> DisplayReviewsActivity.transitionTo(MainActivity.this));
    findViewById(R.id.viewFormButton)
        .setOnClickListener(v -> ViewFormActivity.transitionTo(MainActivity.this));
    findViewById(R.id.submitReviewButton)
        .setOnClickListener(v -> SubmitReviewActivity.transitionTo(MainActivity.this));
  }
}
