package com.example.conversations.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.ConversationsDisplayCallback;
import com.bazaarvoice.bvandroidsdk.ConversationsException;
import com.bazaarvoice.bvandroidsdk.ReviewResponse;
import com.bazaarvoice.bvandroidsdk.ReviewsRequest;
import com.example.conversations.App;
import com.example.conversations.Constants;
import com.example.conversations.R;

import static com.example.conversations.Util.bvErrorsToString;
import static com.example.conversations.Util.showMessage;

public class DisplayReviewsActivity extends AppCompatActivity {
  private ReviewsAdapter reviewsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_reviews);
    setupRecyclerView();

    // Send Request
    final BVConversationsClient bvClient = App.get(this).getBvClient();
    final ReviewsRequest request = new ReviewsRequest.Builder(Constants.PRODUCT_ID, 20, 0).build();
    bvClient.prepareCall(request).loadAsync(reviewsCb);
  }

  private final ConversationsDisplayCallback<ReviewResponse> reviewsCb = new ConversationsDisplayCallback<ReviewResponse>() {
    @Override
    public void onSuccess(@NonNull ReviewResponse response) {
      if (response.getResults().isEmpty()) {
        showMessage(DisplayReviewsActivity.this, "Empty results", "No reviews found for this product");
      } else {
        reviewsAdapter.updateData(response.getResults());
      }
    }

    @Override
    public void onFailure(@NonNull ConversationsException exception) {
      showMessage(DisplayReviewsActivity.this, "Error occurred", bvErrorsToString(exception.getErrors()));
    }
  };

  private void setupRecyclerView() {
    RecyclerView recyclerView = findViewById(R.id.reviewsRecyclerView);
    reviewsAdapter = new ReviewsAdapter();
    recyclerView.setAdapter(reviewsAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  public static void transitionTo(Context fromContext) {
    fromContext.startActivity(new Intent(fromContext, DisplayReviewsActivity.class));
  }
}
