package com.company.zicure.shopmarket.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.company.zicure.shopmarket.R;


/**
 * Created by Pakgon on 7/21/2017 AD.
 */

public class ButtonView extends AppCompatButton {
    public ButtonView(Context context) {
        super(context);
        setFont();
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/supermarket.ttf");
        setTypeface(typeface);
        setBackgroundResource(R.drawable.bg_select_cancel_item);
        setTextColor(getResources().getColor(android.R.color.white));
    }
}
