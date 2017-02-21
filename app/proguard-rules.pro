# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in R:\Software\AndroidSDK\AndroidSDK23/tools/proguard/proguard-android.txt
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
# Support library start
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
# Support library end

# rx library start
-keep class rx.internal.util.unsafe.** { *; }
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
   long producerNode;
   long consumerNode;
}


# RetroFit 2 library
#retrofit start
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-dontwarn rx.**

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
#retrofit end

#OKHttp
-dontwarn okio.**
-keep class okio.** { *; }
-keep class okhttp3.** { *; }
-dontwarn com.squareup.okhttp.*
-dontwarn okhttp3.**
-dontwarn okhttp3.logging.**

# Application classes that will be serialized/deserialized over Gson
-keep class nri.startup.inshort.models.** { *; }

##---------------End: proguard configuration for Gson  ----------

# Glide rules
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# butterknife start
# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
-keep class **$$ViewInjector { *; }
-keep class **$$ViewBinder { *; }
# butterknife end

# Realm rules-
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }
-dontwarn javax.**
-dontwarn io.realm.**






