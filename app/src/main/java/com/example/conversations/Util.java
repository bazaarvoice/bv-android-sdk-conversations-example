package com.example.conversations;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.bazaarvoice.bvandroidsdk.Badge;
import com.bazaarvoice.bvandroidsdk.Error;
import com.bazaarvoice.bvandroidsdk.FieldError;
import com.bazaarvoice.bvandroidsdk.FormError;
import com.bazaarvoice.bvandroidsdk.SecondaryRating;

import java.util.List;
import java.util.Map;

public class Util {

  public static String badgesToString(Map<String, Badge> badgeMap) {
    if (badgeMap == null || badgeMap.isEmpty()) {
      return "no badges";
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Badge badge : badgeMap.values()) {
      stringBuilder.append("badge: ").append(badgeToString(badge)).append("\n");
    }
    return stringBuilder.toString();
  }

  private static String badgeToString(Badge badge) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("id: ").append(badge.getId()).append(", ").append("contentType: ").append(badge.getContentType());
    return stringBuilder.toString();
  }

  public static void showMessage(Context activityContext, String title, String message) {
    new AlertDialog.Builder(activityContext).setTitle(title).setMessage(message).show();
  }

  public static String bvErrorsToString(List<Error> errors) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (Error error: errors) {
      stringBuilder
          .append("code: ").append(error.getCode()).append(", ")
          .append("message: ").append(error.getMessage()).append(";");
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  public static String formErrorsToString(FormError formError) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Map.Entry<String, FieldError> entry : formError.getFieldErrorMap().entrySet()) {
      stringBuilder.append(entry.getKey()).append(fieldErrorToString(entry.getValue())).append("\n");
    }
    return stringBuilder.toString();
  }

  private static String fieldErrorToString(FieldError fieldError) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder
        .append("code: ").append(fieldError.getCode()).append(", ")
        .append("field: ").append(fieldError.getField()).append(", ")
        .append("message").append(fieldError.getMessage()).append("\n");
    return stringBuilder.toString();
  }

  public static String secondaryRatingsToString(Map<String, SecondaryRating> secondaryRatingMap) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (Map.Entry<String, SecondaryRating> entry : secondaryRatingMap.entrySet()) {
      final SecondaryRating secondaryRating = entry.getValue();
      stringBuilder
          .append("label: ").append(secondaryRating.getLabel()).append(", ")
          .append("value: ").append(secondaryRating.getValue()).append(";");
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}
