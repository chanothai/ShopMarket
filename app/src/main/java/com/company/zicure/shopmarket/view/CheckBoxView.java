package com.company.zicure.shopmarket.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Pakgon on 11/16/2017 AD.
 */

public class CheckBoxView extends AppCompatCheckBox {

    public CheckBoxView(Context context) {
        super(context);
        setFont();
    }

    public CheckBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CheckBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/Cloud-Light.otf");
        setTypeface(typeface);
    }
}
