package baloght.tongue.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by baloght on 2018.04.18..
 */

public class DownloadImageUtil extends AsyncTask<URL, Void, Bitmap>{

    ImageView imageView;

    public DownloadImageUtil(ImageView imageView) {
        this.imageView = imageView;
    }

    protected void onPreExecute(){
        // Display the progress dialog on async task start
    }

    @Override
    protected Bitmap doInBackground(URL... urls) {
        String urldisplay = urls[0].getPath();
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            LogUtil.log(e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result){

    }
}
