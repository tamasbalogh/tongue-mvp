package baloght.tongue.utils;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import baloght.tongue.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

public class ImageHandler extends AsyncTask<String, Void, Bitmap> {

    ProgressBar progressBar;
    ImageView imageView;

    public ImageHandler(ProgressBar progressBar, ImageView imageView) {
        this.progressBar = progressBar;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        URL url;
        Bitmap bitmap = null;
        HttpURLConnection connection = null;

        try{
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bitmap = BitmapFactory.decodeStream(bufferedInputStream);

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        progressBar.setVisibility(View.INVISIBLE);

        if(bitmap!=null){
            ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
            File file = wrapper.getDir("Images",MODE_PRIVATE);
            file = new File(file, "UniqueFileName"+".jpg");
            try{
                OutputStream stream = null;
                stream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                stream.flush();
                stream.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            imageView.setImageURI(Uri.parse(file.getAbsolutePath()));
            LogUtil.log("postexecute - " + file.getAbsolutePath());

        }else {
            imageView.setImageResource(R.drawable.avatar);
            LogUtil.log("An error occurd while downloading image ");
        }
    }

}
