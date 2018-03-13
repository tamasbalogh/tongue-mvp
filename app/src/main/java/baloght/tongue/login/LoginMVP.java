package baloght.tongue.login;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import baloght.tongue.repository.models.User;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public interface LoginMVP {
    interface ModelOperations{
        User getUser(int id);
    }

    interface ViewOperations {
        Context getAppContext();
        void showToast(Toast toast);
        void clearEditText();
    }

    interface ProvidedPresenterOperations{
        void goToRegisterPage(Activity activity);
        void setView(ViewOperations view);
    }

    interface RequiredPresenterOperations {
        Context getAppContext();
    }
}
