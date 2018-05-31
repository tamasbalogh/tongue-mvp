package baloght.tongue.utils.GameUtils;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import baloght.tongue.R;
import baloght.tongue.utils.LogUtil;

public class DragListener implements View.OnDragListener {

    Context context;
    ImageView imageView0, imageView1;
    TextView word;
    public Response delegate;

    public DragListener(Context context, ImageView imageView0, ImageView imageView1, TextView word, Response delegate) {
        this.context = context;
        this.imageView0 = imageView0;
        this.imageView1 = imageView1;
        this.word = word;
        this.delegate = delegate;
    }

    @Override
    public boolean onDrag(final View v, DragEvent event) {
        final View view = (View) event.getLocalState();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED:
                //LogUtil.log("draglistener entered");
                break;
            case DragEvent.ACTION_DROP:
                //LogUtil.log("draglistener drop");
                if (view.getId() == R.id.gameImageView0) {
                    delegate.selectionFinish(0);
                }
                if (view.getId() == R.id.gameImageView1) {
                    delegate.selectionFinish(1);
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setVisibility(View.VISIBLE);
                    }
                });
                break;
        }
        return true;
    }

    public interface Response{
        void selectionFinish(int selected);
    }
}
