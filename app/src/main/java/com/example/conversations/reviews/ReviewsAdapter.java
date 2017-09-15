package com.example.conversations.reviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bazaarvoice.bvandroidsdk.Review;
import com.example.conversations.R;
import com.example.conversations.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReviewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private List<Review> reviews = new ArrayList<>();

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View reviewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row, parent, false);
    return new ViewHolder(reviewRow);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    Review review = reviews.get(position);
    ViewHolder viewHolder = (ViewHolder) holder;
    viewHolder.bind(review);
  }

  @Override
  public int getItemCount() {
    return reviews.size();
  }

  public void updateData(List<Review> results) {
    if (results != null) {
      this.reviews.clear();
      this.reviews.addAll(results);
      notifyDataSetChanged();
    }
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView title, reviewBody, rating, badges, authorNick,
    submissionDate, isRecommended, isSyndicated, helpfulCount, notHelpfulCount,
    secondaryRatings, photoCount, videoLinkCount, commentCount;

    public ViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
      reviewBody = itemView.findViewById(R.id.reviewBody);
      rating = itemView.findViewById(R.id.rating);
      badges = itemView.findViewById(R.id.badges);
      authorNick = itemView.findViewById(R.id.authorNick);
      submissionDate = itemView.findViewById(R.id.submissionDate);
      isRecommended = itemView.findViewById(R.id.isRecommended);
      isSyndicated = itemView.findViewById(R.id.isSyndicated);
      helpfulCount = itemView.findViewById(R.id.helpfulCount);
      notHelpfulCount = itemView.findViewById(R.id.notHelpfulCount);
      secondaryRatings = itemView.findViewById(R.id.secondaryRatings);
      photoCount = itemView.findViewById(R.id.photoCount);
      videoLinkCount = itemView.findViewById(R.id.videoLinkCount);
      commentCount = itemView.findViewById(R.id.numComments);
    }

    public void bind(Review review) {
      title.setText(String.format("Title: %s", review.getTitle()));
      reviewBody.setText(String.format("Review Text: %s", review.getReviewText()));
      rating.setText(String.format(Locale.US, "Rating: %d", review.getRating()));
      badges.setText(String.format("Badges: %s", Util.badgesToString(review.getBadges())));
      authorNick.setText(String.format("AuthorId: %s", review.getAuthorId()));
      submissionDate.setText(String.format("Submission Date: %s", review.getSubmissionDate()));
      isRecommended.setText(String.format("IsRecommended: %s", review.getRecommended()));
      isSyndicated.setText(String.format("IsSyndicated: %s", review.getSyndicated()));
      helpfulCount.setText(String.format("HelpfulCount: %s", review.getTotalPositiveFeedbackCount()));
      notHelpfulCount.setText(String.format("Not HelpfulCount: %s", review.getTotalNegativeFeedbackCount()));
      secondaryRatings.setText(String.format("SecondaryRatings: %s", Util.secondaryRatingsToString(review.getSecondaryRatings())));
      photoCount.setText(String.format("PhotoCount: %s", review.getPhotos().size()));
      videoLinkCount.setText(String.format("VideoLinkCount: %s", review.getVideos().size()));
      commentCount.setText(String.format("CommentCount: %s", review.getComments().size()));
    }
  }
}
