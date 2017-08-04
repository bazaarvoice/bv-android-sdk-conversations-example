package com.example.conversations.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.BazaarException;
import com.bazaarvoice.bvandroidsdk.ConversationsCallback;
import com.bazaarvoice.bvandroidsdk.ReviewResponse;
import com.bazaarvoice.bvandroidsdk.ReviewsRequest;
import com.example.conversations.App;
import com.example.conversations.Constants;
import com.example.conversations.R;
import com.example.conversations.Util;

import static com.example.conversations.Util.showMessage;

public class DisplayReviewsActivity extends AppCompatActivity implements ConversationsCallback<ReviewResponse> {
  private ReviewsAdapter reviewsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_reviews);
    setupRecyclerView();

    // Send Request
    BVConversationsClient bvClient = App.get(this).getBvClient();
    ReviewsRequest request = new ReviewsRequest.Builder(Constants.PRODUCT_ID, 20, 0).build();
    bvClient.prepareCall(request).loadAsync(this);
  }

  @Override
  public void onSuccess(ReviewResponse response) {
    if (response.getHasErrors()) {
      showMessage(this, "Error occurred", Util.bvErrorsToString(response.getErrors()));
    } else if (response.getResults().isEmpty()) {
      showMessage(this, "Empty results", "No reviews found for this product");
    } else {
      reviewsAdapter.updateData(response.getResults());
    }
  }

  @Override
  public void onFailure(BazaarException exception) {
    exception.printStackTrace();
    showMessage(this, "Error occurred", exception.getMessage());
  }

  private void setupRecyclerView() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reviewsRecyclerView);
    reviewsAdapter = new ReviewsAdapter();
    recyclerView.setAdapter(reviewsAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  public static void transitionTo(Context fromContext) {
    fromContext.startActivity(new Intent(fromContext, DisplayReviewsActivity.class));
  }
}
