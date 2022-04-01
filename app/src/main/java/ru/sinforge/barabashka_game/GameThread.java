package ru.sinforge.barabashka_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static java.lang.Math.max;

public class GameThread extends Thread{
    private boolean running = true;
    private SurfaceHolder surfaceHolder;
    public GameThread (Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }
    public void requestStop() {
        running = false;
    }

    // TODO : realize method for drawing random figures
    public void DrawRandomFigure() {

    }
    @Override
    public void run() {
        Paint paint  = new Paint();
        while(running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawARGB(255, 0, 255, 0);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setARGB(255, 255, 0, 0);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
