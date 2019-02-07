package com.example.conversations.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bazaarvoice.bvandroidsdk.Badge
import com.bazaarvoice.bvandroidsdk.Review
import com.example.conversations.R
import com.example.conversations.Util
import java.util.*

class ReviewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val reviews = ArrayList<Review>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val reviewRow = LayoutInflater.from(parent.context).inflate(R.layout.review_row, parent, false)
        return ViewHolder(reviewRow)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val review = reviews[position]
        val viewHolder = holder as ViewHolder
        viewHolder.bind(review)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    fun updateData(results: List<Review>?) {
        if (results != null) {
            this.reviews.clear()
            this.reviews.addAll(results)
            notifyDataSetChanged()
        }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val reviewBody: TextView = itemView.findViewById(R.id.reviewBody)
        private val rating: TextView = itemView.findViewById(R.id.rating)
        private val badges: TextView = itemView.findViewById(R.id.badges)
        private val authorNick: TextView = itemView.findViewById(R.id.authorNick)
        private val submissionDate: TextView = itemView.findViewById(R.id.submissionDate)
        private val isRecommended: TextView = itemView.findViewById(R.id.isRecommended)
        private val isSyndicated: TextView = itemView.findViewById(R.id.isSyndicated)
        private val helpfulCount: TextView = itemView.findViewById(R.id.helpfulCount)
        private val notHelpfulCount: TextView = itemView.findViewById(R.id.notHelpfulCount)
        private val secondaryRatings: TextView = itemView.findViewById(R.id.secondaryRatings)
        private val photoCount: TextView = itemView.findViewById(R.id.photoCount)
        private val videoLinkCount: TextView = itemView.findViewById(R.id.videoLinkCount)
        private val commentCount: TextView = itemView.findViewById(R.id.numComments)

        fun bind(review: Review) {
            review.badgeList
            title.text = String.format("Title: %s", review.title)
            reviewBody.text = String.format("Review Text: %s", review.reviewText)
            rating.text = String.format(Locale.US, "Rating: %d", review.rating)
            badges.text = String.format("Badges: %s", Util.badgesToString(review.badges as Map<String, Badge>?))
            authorNick.text = String.format("AuthorId: %s", review.authorId)
            submissionDate.text = String.format("Submission Date: %s", review.submissionDate)
            isRecommended.text = String.format("IsRecommended: %s", review.recommended)
            isSyndicated.text = String.format("IsSyndicated: %s", review.syndicated)
            helpfulCount.text = String.format("HelpfulCount: %s", review.totalPositiveFeedbackCount)
            notHelpfulCount.text = String.format("Not HelpfulCount: %s", review.totalNegativeFeedbackCount)
            secondaryRatings.text = String.format("SecondaryRatings: %s", Util.secondaryRatingsToString(review.secondaryRatings!!))
            photoCount.text = String.format("PhotoCount: %s", review.photos!!.size)
            videoLinkCount.text = String.format("VideoLinkCount: %s", review.videos.size)
            commentCount.text = String.format("CommentCount: %s", review.comments.size)
        }
    }
}
