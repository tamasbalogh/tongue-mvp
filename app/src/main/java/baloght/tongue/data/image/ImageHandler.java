package baloght.tongue.data.image;

import android.content.Context;
import android.graphics.Bitmap;

public interface ImageHandler {

    Bitmap downloadBitmapImage(String url);
    void saveImage(Bitmap bitmap);
    Bitmap loadImage(String imageName);
    String getImagePath(String imageName);
    boolean isFileExists(String imageName);
    void deleteImage(String imageName);

}
