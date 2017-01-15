package com.rafael.bomberman;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by root on 18/12/16.
 */

public class TableroView extends View {

    private boolean fin;

    public TableroView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TableroView,
                0, 0);

        try {
            fin = a.getBoolean(R.styleable.TableroView_fin, true);
        } finally {
            a.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect r = new Rect();
        Paint rectanguloPaint = new Paint(0);
        rectanguloPaint.setStyle(Paint.Style.FILL);
        rectanguloPaint.setColor(0xff101010);
        canvas.drawRect(r,rectanguloPaint);
    }
}
