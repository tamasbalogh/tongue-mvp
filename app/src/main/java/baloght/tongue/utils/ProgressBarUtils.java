package baloght.tongue.utils;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Balogh Tamas on 2018. 03. 16..
 */

public class ProgressBarUtils {
    public static void showProgressBar(ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
    }

    public static void hideProgressBar(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }
}
