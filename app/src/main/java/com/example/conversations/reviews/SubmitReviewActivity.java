package com.example.conversations.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bazaarvoice.bvandroidsdk.Action;
import com.bazaarvoice.bvandroidsdk.AuthenticationProvider;
import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.BVHostedAuthenticationProvider;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionCallback;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionException;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionRequest;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionResponse;
import com.example.conversations.App;
import com.example.conversations.Constants;
import com.example.conversations.R;

import static com.example.conversations.Util.submissionExceptionToString;

public class SubmitReviewActivity extends AppCompatActivity {
  private TextView submitResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_with_text);
    submitResult = findViewById(R.id.displayText);
    final BVConversationsClient bvClient = App.get(this).getBvClient();

    final AuthenticationProvider authProvider = new BVHostedAuthenticationProvider(Constants.UAS); // if BV Hosted Authentication
    // OR // = new SiteAuthenticationProvider(Constants.UAS); // if Site Authentication

    // Send Request
    final ReviewSubmissionRequest request = new ReviewSubmissionRequest
        .Builder(Action.Preview, Constants.PRODUCT_ID)
        .authenticationProvider(authProvider)
        .rating(5)
        .title(Constants.REVIEW_TITLE)
        .reviewText(Constants.REVIEW_TEXT)
        .userNickname(Constants.USER_NICKNAME)
        .build();
    bvClient.prepareCall(request).loadAsync(viewFormCb);
  }

  private final ConversationsSubmissionCallback<ReviewSubmissionResponse> viewFormCb = new ConversationsSubmissionCallback<ReviewSubmissionResponse>() {
    @Override
    public void onSuccess(@NonNull ReviewSubmissionResponse response) {
      submitResult.setText("Success!\n\nNow submit with Action.Submit to actually submit");
    }

    @Override
    public void onFailure(@NonNull ConversationsSubmissionException exception) {
      submitResult.setText("Error!\n\n" + submissionExceptionToString(exception));
    }
  };

  public static void transitionTo(Context fromContext) {
    fromContext.startActivity(new Intent(fromContext, SubmitReviewActivity.class));
  }
}
