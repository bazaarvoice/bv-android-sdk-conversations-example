package com.example.conversations.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bazaarvoice.bvandroidsdk.*
import com.example.conversations.App
import com.example.conversations.Constants
import com.example.conversations.R
import com.example.conversations.Util.submissionExceptionToString

class SubmitReviewActivity : AppCompatActivity() {
    private var submitResult: TextView? = null

    private val viewFormCb = object : ConversationsSubmissionCallback<ReviewSubmissionResponse> {
        override fun onSuccess(response: ReviewSubmissionResponse) {
            submitResult?.text = "Success!\n\nNow submit with Action.Submit to actually submit"
        }

        override fun onFailure(exception: ConversationsSubmissionException) {
            submitResult?.text = "Error!\n\n" + submissionExceptionToString(exception)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_text)
        submitResult = findViewById(R.id.displayText)
        val bvClient = App.get(this).bvClient

        val authProvider = BVHostedAuthenticationProvider(Constants.UAS) // if BV Hosted Authentication
        // OR // = new SiteAuthenticationProvider(Constants.UAS); // if Site Authentication

        // Send Request
        val request = ReviewSubmissionRequest.Builder(Action.Preview, Constants.PRODUCT_ID)
                .authenticationProvider(authProvider)
                .rating(5)
                .title(Constants.REVIEW_TITLE)
                .reviewText(Constants.REVIEW_TEXT)
                .userNickname(Constants.USER_NICKNAME)
                .build()
        bvClient!!.prepareCall(request).loadAsync(viewFormCb)
    }

    companion object {

        fun transitionTo(fromContext: Context) {
            fromContext.startActivity(Intent(fromContext, SubmitReviewActivity::class.java))
        }
    }
}
