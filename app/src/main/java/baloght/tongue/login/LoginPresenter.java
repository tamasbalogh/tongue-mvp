package baloght.tongue.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import baloght.tongue.register.RegisterView;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class LoginPresenter implements LoginMVP.ProvidedPresenterOperations, LoginMVP.RequiredPresenterOperations {

    LoginMVP.ModelOperations model;
    LoginMVP.ViewOperations view;

    public LoginPresenter( LoginMVP.ViewOperations view) {
        this.view = view;
    }

    @Override
    public void goToRegisterPage(Activity activity) {
        activity.startActivity(new Intent(getAppContext(), RegisterView.class));
    }

    @Override
    public void setView(LoginMVP.ViewOperations view) {

    }

    @Override
    public Context getAppContext() {
        return view.getAppContext();
    }
}
