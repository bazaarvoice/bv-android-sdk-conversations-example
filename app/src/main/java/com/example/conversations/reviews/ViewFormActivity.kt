package com.example.conversations.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

import com.bazaarvoice.bvandroidsdk.Action
import com.bazaarvoice.bvandroidsdk.BVConversationsClient
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionCallback
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionException
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionRequest
import com.bazaarvoice.bvandroidsdk.ReviewSubmissionResponse
import com.example.conversations.App
import com.example.conversations.Constants
import com.example.conversations.R

import com.example.conversations.Util.formResponseToString
import com.example.conversations.Util.showMessage

class ViewFormActivity : AppCompatActivity() {
    private var formFieldsTextView: TextView? = null

    private val viewFormCb = object : ConversationsSubmissionCallback<ReviewSubmissionResponse> {
        override fun onSuccess(response: ReviewSubmissionResponse) {
            formFieldsTextView?.text = formResponseToString(response)
        }

        override fun onFailure(exception: ConversationsSubmissionException) {
            showMessage(this@ViewFormActivity, "Unexpected Error Occurred", exception.message ?: "An unexpected error occurred")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_text)
        formFieldsTextView = findViewById(R.id.displayText)
        val bvClient = App[this].bvClient

        // Send Request
        val request = ReviewSubmissionRequest.Builder(Action.Form, Constants.PRODUCT_ID)
                .userNickname(Constants.USER_NICKNAME)
                .build()
        bvClient!!.prepareCall(request).loadAsync(viewFormCb)
    }

    companion object {

        fun transitionTo(fromContext: Context) {
            fromContext.startActivity(Intent(fromContext, ViewFormActivity::class.java))
        }
    }
}
