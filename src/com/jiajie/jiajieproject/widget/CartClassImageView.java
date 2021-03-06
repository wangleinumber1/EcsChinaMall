package com.jiajie.jiajieproject.widget;

import com.jiajie.jiajieproject.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.ImageView;

public class CartClassImageView extends ImageView implements Checkable{
	private static final String TAG = "CartClassImageView";
	private boolean isChecked;
	
	public CartClassImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setChecked(boolean check){
		isChecked = check;
		refreshDrawableState();
	}
	
	public boolean isChecked(){
		return isChecked;
	}
	
	public void toggle() {
        setChecked(!isChecked);
    }
	
	@Override
	public boolean performClick() {
		Log.d(TAG,"performClick()");
		toggle();
		return super.performClick();
	}
	
	public void refreshDrawableState(){
		int resId = isChecked() ? R.drawable.check:R.drawable.caryuan;
		this.setImageResource(resId);
	}
}
