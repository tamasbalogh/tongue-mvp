package baloght.tongue.data.image;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.inject.Inject;
import javax.inject.Singleton;

import baloght.tongue.R;
import baloght.tongue.di.ApplicationContext;
import baloght.tongue.utils.LogUtil;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;

import static com.facebook.FacebookSdk.getApplicationContext;

@Singleton
public class ImageHandlerImpl implements ImageHandler{

    Context context;
    Picasso.Builder picassoBuilder;
    Picasso picasso;


    @Inject
    public ImageHandlerImpl(@ApplicationContext Context context) {
        this.context = context;
        picassoBuilder = new Picasso.Builder(context);
        picasso = picassoBuilder.build();
    }

    @Override
    public Bitmap downloadBitmap(String url) {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            LogUtil.log(e.toString());
            return null;
        }
    }

    @Override
    public String saveProfilePic(Bitmap bitmap){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("profilePicDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,"profile.png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            LogUtil.log(e.toString());
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                LogUtil.log(e.toString());
                e.printStackTrace();
            }
        }
        LogUtil.log("directory path: " + directory.getAbsolutePath());
        LogUtil.log("profilepic path: " + mypath.getAbsolutePath());
        return directory.getAbsolutePath();
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
