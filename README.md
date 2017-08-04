# Conversations Example App

Follow the [Installation Instructions](https://bazaarvoice.github.io/bv-android-sdk/installation.html#configure-bvsdk)
to generate your bvsdk_config_staging.json and bvsdk_config_prod.json files and add them to the 
```app/src/main/assets/``` directory.

You will need to add in a valid product id, and author id in the 
```app/src/main/com/example/conversations/Constants.java``` file. 

If you launch ```MainActivity.java``` then you should see 2 buttons. One to Display Reviews, and one 
to Submit a Review.

<image src="./art/home.png" width="300" align="middle">

## Display Reviews

In the ```DisplayReviewsActivity.java``` a call is being made to display reviews.

<image src="./art/display_reviews.png" width="300" align="middle">

## Submit Review

In the ```SubmitReviewsActivity.java``` a call is being made to submit a review.

You may see some form errors if your request does not include all of the field 
required per your configuration...

<image src="./art/some_form_errors.png" width="300" align="middle">

Once you go back and edit the Request with the required params, you should now be 
able to submit successfully...

<image src="./art/submit_success.png" width="300" align="middle">

## Common Errors

### Submitting with ```Action.Preview``` instead of ```Action.Submit```

When you intend for submission requests to actually exist on your backend, you must ensure 
 that you are submitting with ```Action.Submit```.

Submitting with ```Action.Preview``` is useful when you do not want to be spamming your 
environment with fake data, or if you simply want to see a submission form from the 
```response.getFormData()``` call.

### Not catching the errors in the ```BVCallback#onSuccess(response)``` method. 

A review will only be considered successful when ```response.getHasErrors()``` returns false. 

All ```FormError```, and BV API ```Error``` objects are routed to the 
```BVCallback#onSuccess(ResponseType response)``` method for your callback. 

You should check 
```response.getHasErrors()``` to see if it has either type of error, which you can get 
from calling ```response.getFormErrors()``` and ```response.getErrors()```. 

The ```BVCallback#onFailure(BazaarException exception)``` method is only called when an a 
non-API related error happens, such as no-network, etc.

## FAQ

### Badges

```java
if (response.getHasErrors()) { 
  // handle it
  return;
}
final List<Review> reviews = response.getResults();
final Review review = reviews.get(position);
final Map<String, Badge> badgeMap = review.getBadges();
for (Map.Entry<String, Badge> badgeEntry : badgeMap.entrySet()) {
  final Badge badge = badgeEntry.getValue();
  final String badgeId = badge.getId();
  final String contentType = badge.getContentType();
}
```

## More info

* [Github repo](https://github.com/bazaarvoice/bv-android-sdk)
* [Docs](https://bazaarvoice.github.io/bv-android-sdk/index.html)
* [Generate config files](https://bazaarvoice.github.io/bv-android-sdk/installation.html#configure-bvsdk)
* [More comprehensive demos](https://bazaarvoice.github.io/bv-android-sdk/example_projects.html)