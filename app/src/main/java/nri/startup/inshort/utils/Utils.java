package nri.startup.inshort.utils;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nri.startup.inshort.R;



public class Utils {

    private static final String NO_CONNECTION_ERROR = "Connection failed. Please check your internet connection.";
    private static final String NO_RESPONSE_TIMEOUT = "No response received within reply timeout.";
    private Context mContext;
    private String[] mDateformat;
    private String mDate, mTime;


    public Utils(Context context) {

        this.mContext = context;
    }





    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void showAlertDialog(Context mActivity, String title, String message, String positiveButtonText, String negativeButtonText, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener, boolean cancelable) {
        Dialog dialog;
        if (!isEmpty(negativeButtonText)) {
            dialog = new AlertDialog.Builder(mActivity)
                    .setIcon(ContextCompat.getDrawable(mActivity, R.mipmap.ic_launcher))
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText, okListener)
                    .setNegativeButton(negativeButtonText, cancelListener)
                    .setCancelable(cancelable)
                    .create();
        } else {
            dialog = new AlertDialog.Builder(mActivity)
                    .setIcon(ContextCompat.getDrawable(mActivity, R.mipmap.ic_launcher))
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText, okListener)
                    .setCancelable(cancelable)
                    .create();
        }
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.show();
    }


    public String getDeviceId() {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str) || str.toString().equals("null");
    }


    public boolean isMarshmallow() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }


    public Date convertStringToDate(String strDate, String currentFormat) {
        try {
            return new SimpleDateFormat(currentFormat, Locale.getDefault()).parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            //If It can not be parsed, return today's date instead of null. So return value of this method does not create null pointer exception.
            return new Date();
        }
    }

    public String convertDateStringToString(String strDate, String currentFormat,
                                            String parseFormat) {
        if (isDateStringProperlyFormatted(strDate, currentFormat)) {
            try {
                return convertDateToString(convertStringToDate(strDate, currentFormat),
                        parseFormat);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return strDate;
        }
    }

    public String convertDateToString(Date objDate, String parseFormat) {
        try {
            return new SimpleDateFormat(parseFormat, Locale.getDefault()).format(objDate);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean isDateStringProperlyFormatted(String dateString, String dateFormat) {
        boolean isProperlyFormatted = false;
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        //SetLenient meanse dateString will be checked strictly with dateFormat. Otherwise it will format TaskDetailActivity per wish.
        format.setLenient(false);
        try {
            Date date = format.parse(dateString);
            isProperlyFormatted = true;
        } catch (ParseException e) {
            //exception means dateString is not parsable by dateFormat. Thus dateIsProperlyFormatted.
        }
        return isProperlyFormatted;
    }


    public boolean isArrayListEmpty(ArrayList arraylist) {
        return arraylist == null || arraylist.isEmpty();
    }

    public boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public Drawable getTintedDrawable(Context context,
                                      @DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableResId);
        int color = ContextCompat.getColor(context, colorResId);
        drawable.mutate();
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }


    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        return animator;
    }

}
