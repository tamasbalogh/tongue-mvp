package baloght.tongue.register;

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
import baloght.tongue.data.db.models.User;
import baloght.tongue.utils.KeyboardUtils;
import baloght.tongue.utils.ProgressBarUtils;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Balogh Tamas on 2018. 03. 16..
 */

public class RegisterPresenter implements RegisterMVP.ProvidedPresenterOperations {

    AsyncHttpClient client;

    RegisterMVP.ViewOperations view;

    public RegisterPresenter(RegisterMVP.ViewOperations view){
        this.view = view;
        client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(3,1000);
    }

    @Override
    public void register(EditText email, EditText user, EditText password) {
        if(email.getText().toString().isEmpty()){
            warnUser(email);
            return;
        }

        if(user.getText().toString().isEmpty()){
            warnUser(user);
            return;
        }

        if(password.getText().toString().isEmpty()){
            warnUser(password);
            return;
        }

        RequestParams params = new RequestParams();
        params.put("email",email.getText().toString());
        params.put("user", user.getText().toString());
        params.put("password", password.getText().toString());

        client.post("http://localhost:3000/login", params, new AsyncHttpResponseHandler() {
            public void onStart() {
                ProgressBarUtils.showProgressBar(view.getProgressBar());
                KeyboardUtils.HideKeyboard(view.getActivity());
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                view.showToast(makeToast("succes"));
                ProgressBarUtils.hideProgressBar(view.getProgressBar());
                view.goToTheLoginPage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                view.showToast(makeToast("An error occurd, please check the network."));
                ProgressBarUtils.hideProgressBar(view.getProgressBar());
            }
            @Override
            public void onRetry(int retryNo) {
                view.showToast(makeToast("Request retry no. " +  retryNo + "/3"));
            }
        });

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
}
