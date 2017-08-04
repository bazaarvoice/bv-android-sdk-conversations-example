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
    private TextView title, reviewBody, rating, badges, authorNick;

    public ViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.title);
      reviewBody = (TextView) itemView.findViewById(R.id.reviewBody);
      rating = (TextView) itemView.findViewById(R.id.rating);
      badges = (TextView) itemView.findViewById(R.id.badges);
      authorNick = (TextView) itemView.findViewById(R.id.authorNick);
    }

    public void bind(Review review) {
      title.setText(String.format("title: %s", review.getTitle()));
      reviewBody.setText(String.format("review: %s", review.getReviewText()));
      rating.setText(String.format(Locale.US, "rating: %d", review.getRating()));
      badges.setText(String.format("badges: %s", Util.badgesToString(review.getBadges())));
      authorNick.setText(String.format("authorId: %s", review.getAuthorId()));
    }
  }
}
