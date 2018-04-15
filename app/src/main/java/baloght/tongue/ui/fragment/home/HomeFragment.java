package baloght.tongue.ui.fragment.home;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
import baloght.tongue.ui.game.GameActivity;
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
    public void updateUserProfilePic(String userProfilePicPath) {
        File file = new File(userProfilePicPath);
        if (file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            profilePic.setImageBitmap(bitmap);
        } else {
            LogUtil.log("default pic lodaded");
        }
    }

    @Override
    public void OpenGameActivity() {
        startActivity(new Intent(getActivity(),  GameActivity.class));
    }
}