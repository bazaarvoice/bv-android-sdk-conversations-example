package com.example.conversations;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.bazaarvoice.bvandroidsdk.Badge;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionException;
import com.bazaarvoice.bvandroidsdk.ConversationsSubmissionResponse;
import com.bazaarvoice.bvandroidsdk.Error;
import com.bazaarvoice.bvandroidsdk.FieldError;
import com.bazaarvoice.bvandroidsdk.FormError;
import com.bazaarvoice.bvandroidsdk.FormField;
import com.bazaarvoice.bvandroidsdk.FormFieldOption;
import com.bazaarvoice.bvandroidsdk.FormInputType;
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
    for (Error error: errors) {
      stringBuilder
          .append("code: ").append(error.getCode()).append("\n")
          .append("message: ").append(error.getMessage()).append("\n")
          .append("\n");
    }
    return stringBuilder.toString();
  }

  public static String bvFieldErrorsToString(List<FieldError> fieldErrors) {
    StringBuilder stringBuilder = new StringBuilder();
    for (FieldError fieldError: fieldErrors) {
      stringBuilder
          .append("code: ").append(fieldError.getCode()).append("\n")
          .append("field: ").append(fieldError.getField()).append("\n")
          .append("message: ").append(fieldError.getMessage()).append("\n")
          .append("\n");
    }
    return stringBuilder.toString();
  }

  public static String submissionExceptionToString(ConversationsSubmissionException e) {
    StringBuilder stringBuilder = new StringBuilder();

    if (!e.getErrors().isEmpty()) {
      stringBuilder.append("BV Errors").append("\n\n");
      stringBuilder.append(bvErrorsToString(e.getErrors()));
      stringBuilder.append("\n\n");
    }

    if (!e.getFieldErrors().isEmpty()) {
      stringBuilder.append("Form Field Errors").append("\n\n");
      stringBuilder.append(bvFieldErrorsToString(e.getFieldErrors()));
      stringBuilder.append("\n\n");
    }

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

  public static String formResponseToString(ConversationsSubmissionResponse response) {
    StringBuilder stringBuilder = new StringBuilder();
    for (FormField formField : response.getFormFields()) {
      stringBuilder.append("id: ").append(formField.getId()).append("\n")
          .append("required: ").append(formField.isRequired()).append("\n")
          .append("label: ").append(formField.getLabel()).append("\n")
          .append("value: ").append(formField.getValue()).append("\n")
          .append("type: ").append(formField.getFormInputType().getValue()).append("\n");

      if (formField.getFormInputType() == FormInputType.TEXT_AREA) {
        stringBuilder
            .append("minlength: ").append(formField.getMinLength()).append("\n")
            .append("minlength: ").append(formField.getMinLength()).append("\n");
      }

      if (formField.getFormInputType() == FormInputType.SELECT) {
        stringBuilder.append("options: ").append("\n");
        for (FormFieldOption option : formField.getFormFieldOptions()) {
          stringBuilder
              .append("\t--").append("\n")
              .append("\tlabel: ").append(option.getLabel()).append("\n")
              .append("\tvalue: ").append(option.getValue()).append("\n");
        }
      }

      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }

}
