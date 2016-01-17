package com.w22ee.lixi.numberlimitedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;



/**
 * Created by lixi on 16/1/12.
 */
public class NumberLimitEditText extends EditText {

    private double maxDouble;

    public NumberLimitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NumberLimitEditText(Context context) {
        super(context);
        init();
    }

    public NumberLimitEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setMaxDouble(double maxDouble) {
        this.maxDouble = maxDouble;
    }


    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new LimitInputConnectionWrapper(super.onCreateInputConnection(outAttrs), false);
    }

    private void  init(){
        this.setCustomSelectionActionModeCallback(new ActionModeCallBack());
        this.setLongClickable(false);
    }

    private class ActionModeCallBack implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }

    private boolean overload(CharSequence text, int newCursorPosition) {

        if (getText().toString().isEmpty()) {
            return false;
        }

        if (text.equals(".")) {
            //防止输入多个小数点奔溃,拦截
            if (getText().toString().contains(".")) {
                return true;
            } else {
                //防止无法输入小数点，放过
                return false;
            }
        }

        //小数点超过两位也不能输入
        if (getText().toString().contains(".")){
            int index = getText().toString().indexOf(".");
            int length = getText().toString().length();
            if ((length-index)>2){
                return true;
            }
        }


        StringBuffer currentStringBuffer = new StringBuffer(getText().toString());
        currentStringBuffer.insert(getSelectionStart(), text);

        double newNumber = Double.parseDouble(currentStringBuffer.toString());



        if (newNumber > maxDouble) {
            return true;
        } else {
            return false;
        }

    }

    private class LimitInputConnectionWrapper extends InputConnectionWrapper {
        public LimitInputConnectionWrapper(InputConnection target, boolean mutable) {
            super(target, mutable);
        }




        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            if (overload(text, newCursorPosition)) {
                return false;
            }
            return super.commitText(text, newCursorPosition);
        }


    }


}
