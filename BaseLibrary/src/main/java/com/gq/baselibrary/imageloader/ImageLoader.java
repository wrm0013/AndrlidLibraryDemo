package com.gq.baselibrary.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;

/**
 * Created by user on 2017/12/28.
 */

public class ImageLoader {
    /**
     * @param iv
     * @param imgUrl
     */
    public static void loadImage(Context context,ImageView iv, String imgUrl) {
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).into(iv);
        }
    }

    /**
     * @param iv
     * @param imgUrl
     * @param strategy 缓存
     */
    public static void loadImageDisk(Context context,ImageView iv, String imgUrl, DiskCacheStrategy strategy) {
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).diskCacheStrategy(strategy).into(iv);
        }
    }

    /**
     * @param iv
     * @param imgUrl
     * @param strategy 缓存
     */
    public static void loadImageDontAnimate(Context context,ImageView iv, Integer imgUrl, DiskCacheStrategy strategy) {
        if (imgUrl == 0)
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).dontAnimate().diskCacheStrategy(strategy).into(iv);
        }
    }

    /**
     * @param iv
     * @param imgUrl
     * @param placeHolder 占位图
     */
    public static void loadImagePlaceHolder(Context context,ImageView iv, String imgUrl, int placeHolder) {
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).placeholder(placeHolder).into(iv);
        }
    }

    /**
     * @param iv
     * @param imgUrl
     * @param placeHolder 占位图
     * @param errorIcon   加载失败
     */
    public static void loadImageHolderError(Context context,ImageView iv, String imgUrl, int placeHolder, int errorIcon) {
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).placeholder(placeHolder).error(errorIcon).into(iv);
        }
    }

    public static void loadImageHolderErrorDisk(Context context,ImageView iv, String imgUrl, int placeHolder, int errorIcon, DiskCacheStrategy strategy) {
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).placeholder(placeHolder).error(errorIcon).diskCacheStrategy(strategy).into(iv);
        }
    }

    public static void loadBitmapTarget(Context context, SimpleTarget target, String imgUrl){
        if (isNull(imgUrl))
            return;
        if (Util.isOnMainThread()) {
            Glide.with(context).load(imgUrl).asBitmap().into(target);
        }
    }

    /**
     * 取消图片加载请求
     * @param context
     */
    public static void cancelRequest(Context context) {
        if (Util.isOnMainThread()) {
            Glide.with(context).pauseRequests();
        }
    }

    /**
     * 检测链接地址是否为空
     *
     * @param url 链接地址
     * @return
     */
    private static boolean isNull(String url) {
        if (null == url || url.isEmpty())
            return true;
        return false;
    }

}
