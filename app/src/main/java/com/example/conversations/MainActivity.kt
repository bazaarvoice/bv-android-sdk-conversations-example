package com.example.conversations

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.example.conversations.reviews.DisplayReviewsActivity
import com.example.conversations.reviews.SubmitReviewActivity
import com.example.conversations.reviews.ViewFormActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.displayReviewsButton)
                .setOnClickListener { DisplayReviewsActivity.transitionTo(this@MainActivity) }
        findViewById<View>(R.id.viewFormButton)
                .setOnClickListener { ViewFormActivity.transitionTo(this@MainActivity) }
        findViewById<View>(R.id.submitReviewButton)
                .setOnClickListener { SubmitReviewActivity.transitionTo(this@MainActivity) }
    }
}
