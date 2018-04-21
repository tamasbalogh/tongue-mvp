package baloght.tongue.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.facebook.appevents.internal.InAppPurchaseEventManager;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import baloght.tongue.di.ApplicationContext;

/**
 * Created by baloght on 2018.04.21..
 */

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

    Context context;
    String imageName;

    @Inject
    public DownloadImage(@ApplicationContext Context context) {
        this.context = context;
    }

    @Inject
    public DownloadImage(@ApplicationContext Context context,
                         String imageName) {
        this.context = context;
        this.imageName = imageName;
    }


    private Bitmap downloadImageBitmap(String sUrl) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
            bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
            inputStream.close();
        } catch (Exception e) {
            LogUtil.log("Something went wrong with img download!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadImageBitmap(params[0]);
    }

    protected void onPostExecute(Bitmap result, String imageName) {
        saveImage(result, imageName + ".png");
    }

    public void saveImage(Bitmap b, String imageName) {

        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            LogUtil.log("Something went wrong with saving image!");
            e.printStackTrace();
        }
    }

    public static void loadProfilePic(){


    }


}
