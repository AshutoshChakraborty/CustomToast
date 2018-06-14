package ap.com.example.admin.customtoast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * <p>A Simple Custom Toast Class which show three type of toast <ul><li>"info(color primary)"</li><li>"success(green)"</li><li>"error(red)"</li></ul></p>
 * </marque><h2><u><font color="green">how to use</font></u></h2>
 * {@code CustomToast.show(this,"Hello world",CustomToast.TYPE_INFO)}
 * <p>for show() method reference ..</p>
 * <p>{@link #show(Context, String, int, int)}</p>
 * {@link #show(Context, String, int)}
 *
 * @author Ashutosh Chakraborty
 */
public class CustomToast extends Toast {


    @IntDef({TYPE_INFO, TYPE_SUCCESS, TYPE_ERROR})
    @Retention(RetentionPolicy.SOURCE)

    public @interface ToastType {
    }


    public static final int TYPE_INFO = 0;
    public static final int TYPE_SUCCESS = 1;
    public static final int TYPE_ERROR = 2;

    public static int LENGTH_LONG = Toast.LENGTH_LONG;
    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;

    private final Context mContext;
    private View mView;
    private int mType;


    public CustomToast(Context context) {
        super(context);
        mContext = context;
    }

    public static CustomToast makeText(Context context, String message) {
        return makeText(context, message, LENGTH_SHORT, TYPE_INFO);
    }

    public static CustomToast makeText(Context context, String message, int duration) {
        return makeText(context, message, duration, TYPE_INFO);
    }

    public static CustomToast makeText(Context context, String message, int duration, @ToastType int type) {
        CustomToast CustomToast = new CustomToast(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast_container, null);

        ImageView icon = view.findViewById(R.id.icon);
        TextView text = view.findViewById(R.id.text);

        switch (type) {
            case TYPE_SUCCESS:
//                icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_circle_white_24dp));
                icon.setImageResource(R.drawable.ic_check_circle_white_24dp);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_toast_success_background));
                } else {
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSuccess));
                }
                break;

            case TYPE_ERROR:
//                icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_error_white_24dp));
                icon.setImageResource(R.drawable.ic_error_white_24dp);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_toast_error_background));
                } else {
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorError));
                }

                break;
            default:
//                icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_info_white_24dp));
                icon.setImageResource(R.drawable.ic_info_white_24dp);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_toast_info_background));
                } else {
                    view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorInfo));
                }
                break;
        }

        text.setText(message);
        CustomToast.setDuration(duration);
        CustomToast.setView(view);

        CustomToast.mView = view;
        CustomToast.mType = type;
        return CustomToast;
    }

    @Override
    public void setText(@StringRes int resId) {
        setText(mContext.getString(resId));
    }

    @Override
    public void setText(CharSequence s) {
        if (mView == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        TextView tv = mView.findViewById(R.id.text);
        if (tv == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        tv.setText(s);
    }

    public void setIcon(@DrawableRes int iconId) {
        setIcon(ContextCompat.getDrawable(mContext, iconId));
    }


    public void setIcon(Drawable icon) {
        if (mView == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        ImageView iv = mView.findViewById(R.id.icon);
        if (iv == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        iv.setImageDrawable(icon);
    }


    public void setType(@ToastType int type) {
        mType = type;
    }


    @ToastType
    public int getType() {
        return mType;
    }

    //for displaying short duration toast

    /**
     * @param context The context to use.Usually {@link android.app.Activity} object.
     * @param message The desired message to show
     * @param mType   The pre defined toast type ({@link #TYPE_INFO}  ,  {@link #TYPE_SUCCESS}  ,  {@link #TYPE_ERROR})
     *                <p>here the duration is by default {@link Toast#LENGTH_SHORT}</p>
     * @see #TYPE_INFO
     * @see #TYPE_SUCCESS
     * @see #TYPE_ERROR
     */
    public static void show(Context context, String message, @ToastType int mType) {
        CustomToast.makeText(context, message, Toast.LENGTH_SHORT, mType).show();
    }

    /**
     * @param context The context to use.Usually {@link android.app.Activity} object.
     * @param message The desired message to show
     * @param mType   The pre defined toast type ({@link #TYPE_INFO}  ,  {@link #TYPE_SUCCESS}  ,  {@link #TYPE_ERROR})
     * @param duration The duration of toast {@link Toast#LENGTH_SHORT}  or  {@link Toast#LENGTH_LONG}
     * @see #TYPE_INFO
     * @see #TYPE_SUCCESS
     * @see #TYPE_ERROR
     */
    public static void show(Context context, String message, int duration, @ToastType int mType) {
        CustomToast.makeText(context, message, duration, mType).show();
    }


}
