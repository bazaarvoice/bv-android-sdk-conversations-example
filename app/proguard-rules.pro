# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/casey.kulm/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep public class com.google.android.gms.* { public *; }
-keep class org.ocpsoft.prettytime.i18n.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.**

-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn com.bazaarvoice.bvandroidsdk.**
-dontwarn afu.org.**
-dontwarn com.squareup.**
-dontwarn butterknife.**
-dontwarn okhttp3.internal.platform.ConscryptPlatform

-keepclassmembers class com.bazaarvoice.bvandroidsdk.BVConfig { <fields>;}
-keepclassmembers enum com.bazaarvoice.bvandroidsdk.** {<fields>;}

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

### OKHTTP

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote okhttp3.internal.Platform

### OKIO

# java.nio.file.* usage which cannot be used at runtime. Animal sniffer annotation.
-dontwarn okio.Okio

-dontwarn org.slf4j.impl.**

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

## Kotlin

-dontwarn kotlin.**
-dontwarn kotlin.reflect.jvm.internal.**
-keep class kotlin.reflect.jvm.internal.** { *; }


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

##---------------End: proguard configuration for Gson  ----------

## Other Settings
#-dontobfuscate
#-dontoptimize