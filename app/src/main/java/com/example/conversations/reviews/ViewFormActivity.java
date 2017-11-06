package com.example.conversations.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bazaarvoice.bvandroidsdk.Action;
import com.bazaarvoice.bvandroidsdk.BVConversationsClient;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionCallback;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionException;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionRequest;
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionResponse;
import com.example.conversations.App;
import com.example.conversations.Constants;
import com.example.conversations.R;

import static com.example.conversations.Util.formResponseToString;
import static com.example.conversations.Util.showMessage;

public class ViewFormActivity extends AppCompatActivity {
  private TextView formFieldsTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_with_text);
    formFieldsTextView = findViewById(R.id.displayText);
    final BVConversationsClient bvClient = App.get(this).getBvClient();

    // Send Request
    final ReviewSubmissionRequest request = new ReviewSubmissionRequest
        .Builder(Action.Form, Constants.PRODUCT_ID)
        .userNickname(Constants.USER_NICKNAME)
        .build();
    bvClient.prepareCall(request).loadAsync(viewFormCb);
  }

  private final ConversationsSubmissionCallback<ReviewSubmissionResponse> viewFormCb = new ConversationsSubmissionCallback<ReviewSubmissionResponse>() {
    @Override
    public void onSuccess(@NonNull ReviewSubmissionResponse response) {
      formFieldsTextView.setText(formResponseToString(response));
    }

    @Override
    public void onFailure(@NonNull ConversationsSubmissionException exception) {
      showMessage(ViewFormActivity.this, "Unexpected Error Occurred", exception.getMessage());
    }
  };

  public static void transitionTo(Context fromContext) {
    fromContext.startActivity(new Intent(fromContext, ViewFormActivity.class));
  }
}
