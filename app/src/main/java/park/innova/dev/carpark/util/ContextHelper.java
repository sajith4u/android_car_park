package park.innova.dev.carpark.util;

import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by sajith on 11/27/16.
 */

public class ContextHelper {
    /**
     * Universal Image Loader
     *
     * @param appImgUrl
     * @param imgView
     */
    public static void loadImage(String appImgUrl, ImageView imgView) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        File cacheDir = StorageUtils.getCacheDirectory(imgView.getContext());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(1000, true, false, false))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(imgView.getContext())
                .memoryCacheExtraOptions(1000, 1000)
                .threadPoolSize(3)
                .memoryCache(new UsingFreqLimitedMemoryCache(10 * 1024 * 1024))
                .defaultDisplayImageOptions(options)
                .build();

        imageLoader.init(config);

        imageLoader.displayImage(appImgUrl, imgView, options);
    }

}
