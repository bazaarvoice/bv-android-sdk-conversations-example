# Conversations Example App

Follow the [Installation Instructions](https://bazaarvoice.github.io/bv-android-sdk/installation.html#configure-bvsdk)
to generate your bvsdk_config_staging.json and bvsdk_config_prod.json files and add them to the 
```app/src/main/assets/``` directory.

You will need to add in a valid product id, and author id in the 
```app/src/main/com/example/conversations/Constants.java``` file. 

If you launch ```MainActivity.java``` then you should see 2 buttons. One to Display Reviews, and one 
to Submit a Review.

<image src="./art/home.png" width="100">

## Display Reviews

In the ```DisplayReviewsActivity.java``` a call is being made to display reviews.

<image src="./art/display_reviews.png" width="100">

## Submit Review

In the ```SubmitReviewsActivity.java``` a call is being made to submit a review.

You may see some form errors if your request does not include all of the field 
required per your configuration...

<image src="./art/some_form_errors.png" width="100">

Once you go back and edit the Request with the required params, you should now be 
able to submit successfully...

<image src="./art/submit_success.png" width="100">
