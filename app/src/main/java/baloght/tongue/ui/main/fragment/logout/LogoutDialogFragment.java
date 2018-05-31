package baloght.tongue.ui.fragment.logout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import baloght.tongue.R;

/**
 * Created by baloght on 2018.04.12..
 */

public class LogoutDialogFragment extends DialogFragment{

    public static final String TAG = "LogoutFragment";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static LogoutDialogFragment newInstance() {
        LogoutDialogFragment fragment = new LogoutDialogFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", someInt);
        fragment.setArguments(args);
        return fragment;
    }

}
