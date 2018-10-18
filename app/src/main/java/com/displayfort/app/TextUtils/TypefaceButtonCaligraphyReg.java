

package com.displayfort.app.TextUtils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * @author husains
 */
public class TypefaceButtonCaligraphyReg extends Button {

    public TypefaceButtonCaligraphyReg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TypefaceButtonCaligraphyReg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypefaceButtonCaligraphyReg(Context context) {
        super(context);
        init();
    }

    private void init() {
        try {

            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/BLKCHCRY.TTF");
                setTypeface(tf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//

}
