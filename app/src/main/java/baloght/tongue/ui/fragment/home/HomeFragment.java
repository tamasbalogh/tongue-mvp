package baloght.tongue.ui.fragment.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import baloght.tongue.R;
import baloght.tongue.di.component.ActivityComponent;
import baloght.tongue.ui.base.BaseFragment;
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
        username.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fragmentHomeButtonLetsStart){
            Log.d("homefragment","button");
        }

        if (v.getId() == R.id.fragmentHomeCircleImageViewProfilePic){
            Log.d("homefragment","profilepic");
        }

        if (v.getId() == R.id.fragmentHomeTextViewUserName){
            Log.d("homefragment","username");
        }
    }
}