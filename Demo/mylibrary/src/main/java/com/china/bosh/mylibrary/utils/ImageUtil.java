package com.china.bosh.mylibrary.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.china.bosh.mylibrary.provider.ContextProvider;

/**
 * @author lzq
 * @date 2019/5/5
 */
public class ImageUtil {

    /**
     * 从ImageView中获取bitmap
     * @param iv 传入ImageView
     * @return 返回bitmap
     */
    public static Bitmap getBmFromIv(ImageView iv) {
         Drawable drawable = iv.getDrawable();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap getBmFromRs(@DrawableRes int id) {
        Bitmap bitmap = null;
        Resources res = ContextProvider.get().getContext().getResources();
        bitmap = BitmapFactory.decodeResource(res, id);
        return bitmap;
    }
}
