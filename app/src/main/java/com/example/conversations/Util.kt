package com.example.conversations

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.bazaarvoice.bvandroidsdk.*

object Util {

    fun badgesToString(badgeMap: Map<String, Badge>?): String {
        if (badgeMap == null || badgeMap.isEmpty()) {
            return "no badges"
        }
        val stringBuilder = StringBuilder()
        for (badge in badgeMap.values) {
            stringBuilder.append("badge: ").append(badgeToString(badge)).append("\n")
        }
        return stringBuilder.toString()
    }

    private fun badgeToString(badge: Badge): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("id: ").append(badge.id).append(", ").append("contentType: ").append(badge.contentType)
        return stringBuilder.toString()
    }

    fun showMessage(activityContext: Context, title: String, message: String) {
        AlertDialog.Builder(activityContext).setTitle(title).setMessage(message).show()
    }

    fun bvErrorsToString(errors: List<Error>): String {
        val stringBuilder = StringBuilder()
        for (error in errors) {
            stringBuilder
                    .append("code: ").append(error.code).append("\n")
                    .append("message: ").append(error.message).append("\n")
                    .append("\n")
        }
        return stringBuilder.toString()
    }

    fun bvFieldErrorsToString(fieldErrors: List<FieldError>): String {
        val stringBuilder = StringBuilder()
        for (fieldError in fieldErrors) {
            stringBuilder
                    .append("code: ").append(fieldError.code).append("\n")
                    .append("field: ").append(fieldError.field).append("\n")
                    .append("message: ").append(fieldError.message).append("\n")
                    .append("\n")
        }
        return stringBuilder.toString()
    }

    fun submissionExceptionToString(e: ConversationsSubmissionException): String {
        val stringBuilder = StringBuilder()

        if (!e.errors.isEmpty()) {
            stringBuilder.append("BV Errors").append("\n\n")
            stringBuilder.append(bvErrorsToString(e.errors))
            stringBuilder.append("\n\n")
        }

        if (!e.fieldErrors.isEmpty()) {
            stringBuilder.append("Form Field Errors").append("\n\n")
            stringBuilder.append(bvFieldErrorsToString(e.fieldErrors))
            stringBuilder.append("\n\n")
        }

        return stringBuilder.toString()
    }

    fun formErrorsToString(formError: FormError): String {
        val stringBuilder = StringBuilder()
        for ((key, value) in formError.fieldErrorMap) {
            stringBuilder.append(key).append(fieldErrorToString(value)).append("\n")
        }
        return stringBuilder.toString()
    }

    private fun fieldErrorToString(fieldError: FieldError): String {
        val stringBuilder = StringBuilder()
        stringBuilder
                .append("code: ").append(fieldError.code).append(", ")
                .append("field: ").append(fieldError.field).append(", ")
                .append("message").append(fieldError.message).append("\n")
        return stringBuilder.toString()
    }

    fun secondaryRatingsToString(secondaryRatingMap: Map<String, SecondaryRating>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("[")
        for ((_, secondaryRating) in secondaryRatingMap) {
            stringBuilder
                    .append("label: ").append(secondaryRating.label).append(", ")
                    .append("value: ").append(secondaryRating.value).append(";")
        }
        stringBuilder.append("]")
        return stringBuilder.toString()
    }

    fun formResponseToString(response: ConversationsSubmissionResponse): String {
        val stringBuilder = StringBuilder()
        for (formField in response.formFields) {
            stringBuilder.append("id: ").append(formField.id).append("\n")
                    .append("required: ").append(formField.isRequired).append("\n")
                    .append("label: ").append(formField.label).append("\n")
                    .append("value: ").append(formField.value).append("\n")
                    .append("type: ").append(formField.formInputType.value).append("\n")

            if (formField.formInputType == FormInputType.TEXT_AREA) {
                stringBuilder
                        .append("minlength: ").append(formField.minLength).append("\n")
                        .append("minlength: ").append(formField.minLength).append("\n")
            }

            if (formField.formInputType == FormInputType.SELECT) {
                stringBuilder.append("options: ").append("\n")
                for (option in formField.formFieldOptions) {
                    stringBuilder
                            .append("\t--").append("\n")
                            .append("\tlabel: ").append(option.label).append("\n")
                            .append("\tvalue: ").append(option.value).append("\n")
                }
            }

            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

}
