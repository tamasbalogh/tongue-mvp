package baloght.tongue.data.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import baloght.tongue.di.ApplicationContext;
import baloght.tongue.utils.LogUtil;

@Singleton
public class ImageHandlerImpl implements ImageHandler{

    Context context;

    @Inject
    public ImageHandlerImpl(@ApplicationContext Context context) {
        this.context = context;
    }


    @Override
    public Bitmap downloadBitmapImage(String url) {
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(url).openStream();   // Download Image from URL
            bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
            inputStream.close();
        } catch (Exception e) {
            LogUtil.log("Something went wrong with img download!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void saveImage(Bitmap bitmap) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput("profile_pic.png", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            LogUtil.log("Something went wrong with saving image!");
            e.printStackTrace();
        }

        File file = context.getFileStreamPath("profile_pic.png");
        LogUtil.log("Saved image path: " + file.getAbsolutePath());
    }

    @Override
    public Bitmap loadImage(String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            LogUtil.log("Something went wrong with loading image!");
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public String getImagePath(String imageName) {
        return null;
    }

    @Override
    public boolean isFileExists(String imageName) {
        return false;
    }

    @Override
    public void deleteImage(String imageName) {

    }


}
