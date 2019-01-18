

package com.displayfort.app.TextUtils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.displayfort.app.widgets.HashTagSuggestAdapter;


/**
 * @author husains
 */
public class TypefaceHashTagAutocompleteBrandenReg extends AutoCompleteTextView {

    public TypefaceHashTagAutocompleteBrandenReg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TypefaceHashTagAutocompleteBrandenReg(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypefaceHashTagAutocompleteBrandenReg(Context context) {
        super(context);
        init();
    }

    private void init() {
        try {

            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.ttf");
                setTypeface(tf);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void replaceText(CharSequence text) {

        clearComposingText();

        HashTagSuggestAdapter adapter = (HashTagSuggestAdapter) getAdapter();
        HashTagSuggestAdapter.HashTagFilter filter = (HashTagSuggestAdapter.HashTagFilter) adapter.getFilter();

        Editable span = getText();
        span.replace(filter.start, filter.end, text);
    }
}
