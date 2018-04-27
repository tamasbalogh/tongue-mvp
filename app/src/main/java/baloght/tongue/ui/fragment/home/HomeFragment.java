package baloght.tongue.ui.fragment.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import javax.inject.Inject;
import baloght.tongue.R;
import baloght.tongue.data.DataManager;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.game.activity.GameActivity;
import baloght.tongue.utils.Constants;
import baloght.tongue.utils.ImageHandler;
import baloght.tongue.utils.LogUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by baloght on 2018.04.11..
 */

public class HomeFragment extends BaseFragment implements HomeMvpView{

    @Inject
    HomeMvpPresenter<HomeMvpView> presenter;

    Button start;
    CircleImageView profilePic;
    TextView username;
    ProgressBar progressBar;

    public static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            presenter.onAttach(this);
        }

        setUp(view);
        return view;
    }

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUp(View view) {
        start = view.findViewById(R.id.fragmentHomeButtonLetsStart);
        profilePic = view.findViewById(R.id.fragmentHomeCircleImageViewProfilePic);
        username = view.findViewById(R.id.fragmentHomeTextViewUserName);
        progressBar= view.findViewById(R.id.fragmentHomeProgressBar);

        start.setOnClickListener(this);
        profilePic.setOnClickListener(this);

        presenter.onViewInitialized();
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragmentHomeButtonLetsStart){
            presenter.onLetStartClicked();
        }

        if (v.getId() == R.id.fragmentHomeCircleImageViewProfilePic){
            presenter.onProfilePicClicked();
        }
    }

    @Override
    public void updateUserName(String userName) {
        username.setText("Welcome " + userName);
    }

    @Override
    public void updateUserProfilePic(DataManager.LoggedInMode mode, String profilePicUrl, String storedPicPath) {

        if(storedPicPath != null){
            profilePic.setImageURI(Uri.parse(storedPicPath));
        }

        if(mode == DataManager.LoggedInMode.LOGGED_IN_MODE_FB && storedPicPath == null){
            new ImageHandler(Constants.PROFILE_PIC_NAME,
                    progressBar,
                    profilePic,
                    this).execute(profilePicUrl);
        }

        if(mode == DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER && storedPicPath == null) {
            profilePic.setImageDrawable(getResources().getDrawable(R.drawable.avatar));
        }

    }

    @Override
    public void OpenGameActivity() {
        startActivity(new Intent(getActivity(),  GameActivity.class));
    }

    @Override
    public void processFinish(String output) {
        LogUtil.log("path of the downloaded image: " + output);
        presenter.saveProfilePicPath(output);
    }
}