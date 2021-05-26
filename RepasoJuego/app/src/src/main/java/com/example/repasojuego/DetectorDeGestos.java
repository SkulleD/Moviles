package com.example.repasojuego;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectorDeGestos extends GestureDetector.SimpleOnGestureListener {
    private static final String LOG_TAG = "GESTO: ";

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(LOG_TAG, "OnDown: " + e.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(LOG_TAG, "OnLongPress: " + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (velocityX < -10f) {
            Log.i(LOG_TAG, "Fling <-");
        } else {
            if (velocityX > 10f) {
                Log.i(LOG_TAG, "Fling ->");
            }
        }
        return true;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(LOG_TAG, "Double tap");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(LOG_TAG, "onShowPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(LOG_TAG, "Single tap");
        return true;
    }
}
