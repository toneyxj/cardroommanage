package com.xj.mainframe.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xj.mainframe.R;
import com.xj.mainframe.utils.glide.CropCircleTransformation;
import com.xj.mainframe.utils.glide.GrayscaleTransformation;
import com.xj.mainframe.utils.glide.RoundedCornersTransformation;
import com.xj.mainframe.utils.okGlideProgress.okInterceptor.ProgressInterceptor;


/**
 * Created by Administrator on 2016/9/22.
 */
public class GlideUtils {

    private static GlideUtils mInstance;
    private GlideUtils(){}
    public static GlideUtils getInstance() {
        if (mInstance == null) {
            synchronized (GlideUtils.class) {
                if (mInstance == null) {
                    mInstance = new GlideUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 常量
     */
    static class Contants {
        public static final int BLUR_VALUE = 20; //模糊
        public static final int CORNER_RADIUS = 20; //圆角
        public static final float THUMB_SIZE = 0.5f; //0-1之间  10%原图的大小
    }
    /**
     * 加载本地图片
     * @param context
     * @param imageView
     * @param locationPath
     */
    public void locatonPic(Context context, ImageView imageView,
                           String locationPath){
        Glide.with(context)
                .load(locationPath)
                .error(R.drawable.pic_fail)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
    /**
     * 常规加载图片
     * @param context
     * @param imageView  图片容器
     * @param imgUrl  图片地址
     */
    public void loadImage(Context context, ImageView imageView,
                          String imgUrl) {
        loadImage(context,imageView,imgUrl,true);
    }
    /**
     * 常规加载图片
     * @param context
     * @param imageView  图片容器
     * @param imgUrl  图片地址
     * @param isFade  是否需要动画
     */
    public void loadImage(@NonNull Context context, @NonNull ImageView imageView,
                          @NonNull final String imgUrl, boolean isFade) {
        if (isFade) {
            Glide.with(context)
                    .load(imgUrl)
                    .error(R.drawable.pic_fail)
                    .crossFade()
                    .priority(Priority.NORMAL) //下载的优先级
                    //all:缓存源资源和转换后的资源 none:不作任何磁盘缓存
                    //source:缓存源资源   result：缓存转换后的资源
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                    .into(new GlideDrawableImageViewTarget(imageView){
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            ProgressInterceptor.removeListener(imgUrl);
                        }
                    });
        } else {
            Glide.with(context)
                    .load(imgUrl)
                    .error(R.drawable.pic_fail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                    .into(new GlideDrawableImageViewTarget(imageView){
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            ProgressInterceptor.removeListener(imgUrl);
                        }
                    });
        }
    }

    /**
     * 加载缩略图
     * @param context
     * @param imageView  图片容器
     * @param imgUrl  图片地址
     */
    public void loadThumbnailImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.pic_fail)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .thumbnail(Contants.THUMB_SIZE)
                .into(imageView);
    }

    /**
     * 加载图片并设置为指定大小
     * @param context
     * @param imageView
     * @param imgUrl
     * @param withSize
     * @param heightSize
     */
    public void loadOverrideImage(Context context, ImageView imageView,
                                  String imgUrl, int withSize, int heightSize) {
        Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.pic_fail)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .override(withSize, heightSize)
                .into(imageView);
    }

    /**
     * 加载图片并对其进行模糊处理
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadGreyImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.pic_fail)
                .error(R.drawable.pic_non)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .dontAnimate()//去除淡出淡入效果
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .bitmapTransform(new GrayscaleTransformation(context))
                .into(imageView);

    }
    /**
     * 加载图片并对其进行模糊处理，没有背景
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadGreyImageNoBack(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.color.transparency)
                .error(R.color.transparency)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .dontAnimate()//去除淡出淡入效果
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .bitmapTransform(new GrayscaleTransformation(context))
                .into(imageView);

    }

    public void loadGreyImage(Context context, ImageView imageView,int holder,int error, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .placeholder(holder)
                .error(error)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .dontAnimate()//去除淡出淡入效果
                .diskCacheStrategy(DiskCacheStrategy.NONE) //缓存策略
                .bitmapTransform(new GrayscaleTransformation(context))
                .into(imageView);

    }

    /**
     * 加载圆图
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadCircleImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.pic_fail)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .bitmapTransform(new CropCircleTransformation(context))
                .into(imageView);
    }

    /**
     * 加载模糊的圆角的图片
     * @param context
     * @param imageView
     * @param imgUrl

    public void loadBlurCircleImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.pic_fail)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .bitmapTransform(
                        new BlurTransformation(context, Contants.BLUR_VALUE),
                        new CropCircleTransformation(context))
                .into(imageView);
    }*/

    /**
     * 加载圆角图片
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadCornerImage(Context context, ImageView imageView, String imgUrl) {
        loadCornerImage(context,imageView,imgUrl, Contants.CORNER_RADIUS, Contants.CORNER_RADIUS);
    }
    public void loadCornerImage(Context context, ImageView imageView, String imgUrl,int radius, int margin) {
        Glide.with(context)
                .load(imgUrl)
                .error(R.drawable.pic_fail)
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .bitmapTransform(
                        new RoundedCornersTransformation(
                                context, radius, margin))
                .into(imageView);
    }

    /**
     * 同步加载图片
     * @param context
     * @param imgUrl
     * @param target
     */
    public void loadBitmapSync(Context context, String imgUrl, SimpleTarget<Bitmap> target) {
        Glide.with(context)
                .load(imgUrl)
                .asBitmap()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .into(target);
    }

    /**
     * 加载gif
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadGifImage(Context context, ImageView imageView, String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .asGif()
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .error(R.drawable.pic_fail)
                .into(imageView);
    }

    /**
     * 加载gif的缩略图
     * @param context
     * @param imageView
     * @param imgUrl
     */
    public void loadGifThumbnailImage(Context context, ImageView imageView,String imgUrl) {
        Glide.with(context)
                .load(imgUrl)
                .asGif()
                .crossFade()
                .priority(Priority.NORMAL) //下载的优先级
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓存策略
                .error(R.drawable.pic_fail)
                .thumbnail(Contants.THUMB_SIZE)
                .into(imageView);
    }

    /**
     * 恢复请求，一般在停止滚动的时候
     * @param context
     */
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 暂停请求 正在滚动的时候
     * @param context
     */
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 清除磁盘缓存
     * @param context
     */
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
            }
        }).start();
    }

    /**
     * 清除内存缓存
     * @param context
     */
    public void clearMemory(Context context) {
        Glide.get(context).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }



}




