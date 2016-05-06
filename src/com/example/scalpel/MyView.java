package com.example.scalpel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

	Paint mPaint = new Paint();
	ArrayList<PointF> array = new ArrayList<PointF>();
	float length=0;
	float lastlength=0;
	public MyView(Context context) {
		super(context);
		init();
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyView(Context context, AttributeSet attrs, int theme) {
		super(context, attrs, theme);
		init();
	}
	private void init(){
		mPaint.setColor(0xFFFFFFFF);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xFFFFAA00);
		synchronized (array) {
			for (int i = 1; i < array.size(); ++i){
				canvas.drawLine(array.get(i - 1).x, array.get(i - 1).y,
						array.get(i).x, array.get(i).y, mPaint);
			}
		}
	}

	long lastTouchTime = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		lastTouchTime = System.currentTimeMillis();
		((FreshAction)getContext()).freshAction(length);
		synchronized (array) {
			int n=array.size();
			if(n!=0){
				length+=Math.sqrt((x-array.get(n-1).x)*(x-array.get(n-1).x)+(y-array.get(n-1).y)*(y-array.get(n-1).y));
			}
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				array.add(new PointF(x, y));
				break;
			}
			case MotionEvent.ACTION_MOVE: {
				array.add(new PointF(x, y));
				break;
			}
			case MotionEvent.ACTION_CANCEL: {
				array.add(new PointF(x, y));
				cancel(lastTouchTime);
				break;
			}
			case MotionEvent.ACTION_UP: {
				array.add(new PointF(x, y));
				cancel(lastTouchTime);
				break;
			}
			default:
				break;
			}
		}
		postInvalidate();
		return true;
	}

	private void cancel(final long time) {
		postDelayed(new Runnable() {
			@Override
			public void run() {
				if (time == lastTouchTime) {
					lastlength=length;
					length=0;
					array.clear();
					postInvalidate();
				}
			}
		}, 2000);
	}
	public interface FreshAction{
		void freshAction(float length);
		void getLastlength(float lastlength);
	}

}
