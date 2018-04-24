package baloght.tongue.data.image;

import android.content.Context;
import android.graphics.Bitmap;

import de.hdodenhof.circleimageview.CircleImageView;

public interface ImageHandler {

    String saveProfilePic(Bitmap bitmap);
    Bitmap downloadBitmap(String url);
    Bitmap loadImage(String imageName);
    String getImagePath(String imageName);
    boolean isFileExists(String imageName);
    void deleteImage(String imageName);

}
