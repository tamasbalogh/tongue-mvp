package baloght.tongue.login;

import android.content.Context;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import baloght.tongue.R;
import baloght.tongue.data.prefs.MyPreferences;
import baloght.tongue.data.prefs.PreferencesHelper;
import baloght.tongue.utils.KeyboardUtils;
import baloght.tongue.utils.ProgressBarUtils;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Balogh Tamas on 2018. 03. 12..
 */

public class LoginPresenter implements LoginMVP.ProvidedPresenterOperations {

    private LoginMVP.ViewOperations view;
    private MyPreferences model;
    private AsyncHttpClient client;

    public LoginPresenter( LoginMVP.ViewOperations view) {
        this.view = view;
        this.model = new MyPreferences(view.getAppContext(),MyPreferences.PREF_NAME);
        client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(3,1000);
    }

    @Override
    public void setView(LoginMVP.ViewOperations view) {

    }

    @Override
    public void validateCredentials(final EditText email, EditText password) {

        if(email.getText().toString().isEmpty()){
            warnUser(email);
            return;
        }

        if(password.getText().toString().isEmpty()){
            warnUser(password);
            return;
        }

        RequestParams params = new RequestParams();
        params.put("email", email.getText());
        params.put("password", password.getText());

        client.post("http://localhost:3000/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                ProgressBarUtils.showProgressBar(view.getProgressBar());
                KeyboardUtils.HideKeyboard(view.getActivity());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                view.showToast(makeToast("succes"));
                ProgressBarUtils.hideProgressBar(view.getProgressBar());
                view.goToTheHomePage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //view.showToast(makeToast("An error occurd, please check the network."));
                view.showToast(makeToast("logged in mode: " + model.getCurrentUserLoggedInMode() + "\n" +
                                "email: " + model.getCurrentEmail() + "\n" +
                                "token: " + model.getAccessToken()));
                ProgressBarUtils.hideProgressBar(view.getProgressBar());
            }

            @Override
            public void onRetry(int retryNo) {
                view.showToast(makeToast("Request retry no. " +  retryNo + "/3"));
                if(retryNo == 1) {
                    setRandomPreferences(email);
                }
            }
        }).setTag("login");
    }

    private void warnUser(TextView emptyTextView){
        Animation shake = AnimationUtils.loadAnimation(view.getAppContext(), R.anim.shake);
        emptyTextView.startAnimation(shake);
        Vibrator vibe = (Vibrator) view.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        emptyTextView.requestFocus();
        KeyboardUtils.showKeyboard(emptyTextView, view.getAppContext());
    }

    private Toast makeToast(String message){
        return Toast.makeText(view.getAppContext(),message,Toast.LENGTH_SHORT);
    }

    private void setRandomPreferences(EditText editText){
        model.setCurrentEmail(editText.getText().toString());
        model.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
        model.setCurrentUserLoggedInMode(PreferencesHelper.LoggedInMode.LOGGED_IN_MODE_SERVER);
    }
}
