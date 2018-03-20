package baloght.tongue.login;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import baloght.tongue.data.db.models.User;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public interface LoginMVP {

    interface ViewOperations {
        Context getAppContext();
        Activity getActivity();
        ProgressBar getProgressBar();
        void goToTheHomePage();
        void showToast(Toast toast);
    }

    interface ProvidedPresenterOperations{
        void setView(ViewOperations view);
        void validateCredentials(EditText email, EditText password);
    }

}
