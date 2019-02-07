package com.example.conversations.reviews

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bazaarvoice.bvandroidsdk.ConversationsDisplayCallback
import com.bazaarvoice.bvandroidsdk.ConversationsException
import com.bazaarvoice.bvandroidsdk.ReviewResponse
import com.bazaarvoice.bvandroidsdk.ReviewsRequest
import com.example.conversations.App
import com.example.conversations.Constants
import com.example.conversations.R
import com.example.conversations.Util.bvErrorsToString
import com.example.conversations.Util.showMessage

class DisplayReviewsActivity : AppCompatActivity() {
    private var reviewsAdapter: ReviewsAdapter? = null

    private val reviewsCb = object : ConversationsDisplayCallback<ReviewResponse> {
        override fun onSuccess(response: ReviewResponse) {
            if (response.results.isEmpty()) {
                showMessage(this@DisplayReviewsActivity, "Empty results", "No reviews found for this product")
            } else {
                reviewsAdapter!!.updateData(response.results)
            }
        }

        override fun onFailure(exception: ConversationsException) {
            showMessage(this@DisplayReviewsActivity, "Error occurred", bvErrorsToString(exception.errors))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_reviews)
        setupRecyclerView()

        // Send Request
        val bvClient = App.get(this).bvClient
        val request = ReviewsRequest.Builder(Constants.PRODUCT_ID, 20, 0).build()
        bvClient!!.prepareCall(request).loadAsync(reviewsCb)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.reviewsRecyclerView)
        reviewsAdapter = ReviewsAdapter()
        recyclerView.adapter = reviewsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {

        fun transitionTo(fromContext: Context) {
            fromContext.startActivity(Intent(fromContext, DisplayReviewsActivity::class.java))
        }
    }
}
