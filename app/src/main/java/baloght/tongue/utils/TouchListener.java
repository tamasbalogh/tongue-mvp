package baloght.tongue.utils;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by baloght on 2018.04.10..
 */

public class TouchListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        ClipData clipData = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.log("action down");
                view.setVisibility(View.INVISIBLE);
                view.startDrag(clipData,shadowBuilder,view,0);
                return true;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;

        }
        return true;
    }
}
