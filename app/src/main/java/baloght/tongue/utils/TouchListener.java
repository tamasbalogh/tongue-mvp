package baloght.tongue.utils;

import android.content.ClipData;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by baloght on 2018.04.10..
 */

public class TouchListener implements View.OnTouchListener {

    ConstraintLayout constraintLayout;

    public TouchListener(ConstraintLayout constraintLayout) {
        this.constraintLayout = constraintLayout;
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int lWidth = constraintLayout.getMeasuredWidth();
        int lHeight = constraintLayout.getMeasuredHeight();

        float baseXofView = 0;
        float baseYofView = 0;

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                baseXofView = lWidth - view.getPivotX();
                baseYofView = lWidth - view.getPivotY();

                return true;
            case MotionEvent.ACTION_UP:
                view.setPivotX(baseXofView);
                view.setPivotY(baseYofView);

                break;
            case MotionEvent.ACTION_MOVE:


                break;
        }

        //ClipData clipData = ClipData.newPlainText("","");
        //View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        //view.startDrag(clipData, shadowBuilder, view, 0);
        return true;
    }
}
