package com.example.conversations.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bazaarvoice.bvandroidsdk.Action;
import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.BazaarException;
import com.bazaarvoice.bvandroidsdk.ConversationsCallback;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionRequest;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionResponse;
import com.example.conversations.App;
import com.example.conversations.Constants;
import com.example.conversations.R;
import com.example.conversations.Util;

import static com.example.conversations.Util.showMessage;

public class SubmitReviewsActivity extends AppCompatActivity implements ConversationsCallback<ReviewSubmissionResponse> {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_submit_reviews);
    BVConversationsClient bvClient = App.get(this).getBvClient();

    // Send Request
    ReviewSubmissionRequest request = new ReviewSubmissionRequest
        .Builder(Action.Submit, Constants.PRODUCT_ID)
        .reviewText("this is awesome, would recommend")
        .rating(4)
        .title("Test title")
        .userNickname(Constants.USER_NICKNAME)
        .build();
    bvClient.prepareCall(request).loadAsync(this);
  }

  @Override
  public void onSuccess(ReviewSubmissionResponse response) {
    if (response.getHasErrors()) {
      if (response.getFormErrors() != null && !response.getFormErrors().getFieldErrorMap().isEmpty()) {
        showMessage(this, "Form Errors", Util.formErrorsToString(response.getFormErrors()));
      } else {
        showMessage(this, "Errors occurred", Util.bvErrorsToString(response.getErrors()));
      }
    } else {
      showMessage(this, "Success", "Everything worked!");
    }
  }

  @Override
  public void onFailure(BazaarException exception) {
    exception.printStackTrace();
    showMessage(this, "Error occurred", exception.getMessage());
  }

  public static void transitionTo(Context fromContext) {
    fromContext.startActivity(new Intent(fromContext, SubmitReviewsActivity.class));
  }
}
