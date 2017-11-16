package com.company.zicure.shopmarket.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.company.zicure.shopmarket.R;


/**
 * Created by Pakgon on 7/21/2017 AD.
 */

public class ButtonView extends AppCompatImageButton {
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

    }
}
