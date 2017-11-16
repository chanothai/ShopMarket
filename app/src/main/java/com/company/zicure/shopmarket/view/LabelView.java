package com.company.zicure.shopmarket.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Pakgon on 7/21/2017 AD.
 */

public class LabelView extends AppCompatTextView {
    public LabelView(Context context) {
        super(context);
        setTextView();
    }

    public LabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTextView();
    }

    public LabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTextView();
    }

    private void setTextView() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fonts/Cloud-Light.otf");
        setTypeface(typeface);
    }
}
