package baloght.tongue.game;

import android.content.ClipData;
import android.view.View;

public class LongClickListener implements  View.OnLongClickListener{

    @Override
    public boolean onLongClick(View v) {
        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        v.startDrag(clipData,
                shadowBuilder,
                v,
                0);
        return true;
    }
}
