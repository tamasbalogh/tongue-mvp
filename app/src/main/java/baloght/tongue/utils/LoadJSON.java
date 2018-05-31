package baloght.tongue.utils;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.prefs.BackingStoreException;

import baloght.tongue.R;
import baloght.tongue.utils.GameUtils.BaseGame;

/**
 * Created by baloght on 2018.04.27..
 */

public class LoadJSON {

    public static String loadJsonObjectFromFile(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.moc_games);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
