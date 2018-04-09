package baloght.tongue.game;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import baloght.tongue.R;

public class DragListener implements View.OnDragListener {

    Context context;
    ImageView imageView0, imageView1;
    TextView word;

    public DragListener(Context context, ImageView imageView0, ImageView imageView1, TextView word) {
        this.imageView0 = imageView0;
        this.imageView1 = imageView1;
        this.word = word;
        this.context = context;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                final View view = (View) event.getLocalState();
                if(view.getId() == R.id.gameImageView0) {
                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
                } else if(view.getId() == R.id.gameImageView1){
                    Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                }
                break;
            case DragEvent.ACTION_DRAG_EXITED:

                break;
            case DragEvent.ACTION_DROP:

        }
        return true;
    }
}
