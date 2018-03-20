package baloght.tongue.register;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import baloght.tongue.data.db.models.User;

/**
 * Created by Balogh Tamas on 2018. 03. 16..
 */

public interface RegisterMVP {

    interface ViewOperations {
        Context getAppContext();
        Activity getActivity();
        ProgressBar getProgressBar();
        void showToast(Toast toast);
        void goToTheLoginPage();
    }

    interface ProvidedPresenterOperations{
        void register(EditText email, EditText user, EditText password);
    }
}
