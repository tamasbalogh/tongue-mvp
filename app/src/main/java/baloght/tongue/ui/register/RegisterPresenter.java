package baloght.tongue.ui.register;
import android.widget.EditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import javax.inject.Inject;
import baloght.tongue.data.DataManager;
import baloght.tongue.ui.base.BasePresenter;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Balogh Tamas on 2018. 03. 16..
 */

public class RegisterPresenter <V extends RegisterMvpView> extends BasePresenter<V>
        implements RegisterMvpPresenter<V> {


    @Inject
    public RegisterPresenter(DataManager dataManager){
        super(dataManager);
    }

    @Override
    public void register(EditText email, final EditText user, EditText password) {
        if(email.getText().toString().isEmpty()){
            getMvpView().warnUser(email);
            return;
        }

        if(user.getText().toString().isEmpty()){
            getMvpView().warnUser(user);
            return;
        }

        if(password.getText().toString().isEmpty()){
            getMvpView().warnUser(password);
            return;
        }

        RequestParams params = new RequestParams();
        params.put("email",email.getText().toString());
        params.put("user", user.getText().toString());
        params.put("password", password.getText().toString());

        getDataManager().doRegister(params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                getMvpView().showLoading();
                getMvpView().hideKeyboard();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                getMvpView().hideLoading();
                getMvpView().openLoginPage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    getMvpView().hideLoading();
                    getMvpView().openLoginPage();
                    getMvpView().showMessage(user.getText().toString() + " created successfully.");
            }

            @Override
            public void onRetry(int retryNo) {
                getMvpView().showMessage("Request retry no. " +  retryNo + "/3");
            }
        });
    }
}
