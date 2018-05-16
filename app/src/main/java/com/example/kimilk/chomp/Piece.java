package com.example.kimilk.chomp;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by kimilk on 19.10.2017.
 */

public class Piece extends AppCompatButton {


    public static int columns = 5; //Zusatzsaufgabe

    public Piece(Context context) {
        super(context);
    }

    public Piece(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Piece(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int size = MeasureSpec.getSize(widthMeasureSpec) / Piece.columns;
        setMeasuredDimension(size, size);
    }
}
