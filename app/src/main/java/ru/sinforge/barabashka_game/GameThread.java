package ru.sinforge.barabashka_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static java.lang.Math.max;
import static java.lang.Math.random;

public class GameThread extends Thread{
    private boolean running = true;
    private SurfaceHolder surfaceHolder;


    public GameThread (Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }



    public void requestStop() {
        running = false;
    }



    private int number_of_figure;


    // TODO : realize method for drawing random figures
    public void DrawRandomFigure(Canvas canvas, Paint paint) {
        int x = canvas.getWidth() / 8;
        for (int i =0 ; i < 4; i++) {
             number_of_figure =(int) (random() * 4);
             switch (number_of_figure) {
                 case 0:
                     paint.setColor(Color.RED);
                     canvas.drawCircle(x + i * (canvas.getWidth() / 4), canvas.getHeight() - canvas.getWidth() / 8, canvas.getWidth() / 8, paint);
                     break;
                 case 1:
                     paint.setColor(Color.BLACK);
                     Rect rect = new Rect();
                     rect.set((int) (i * canvas.getWidth()/4), (int) (canvas.getHeight() - canvas.getWidth() / 4), (int) (canvas.getWidth() /4 + i * canvas.getWidth()/4), (int)canvas.getHeight());
                     canvas.drawRect(rect, paint);
                     break;
                 case 2:
                     paint.setColor(Color.WHITE);
                     canvas.drawCircle(x + i * (canvas.getWidth() / 4), canvas.getHeight() - canvas.getWidth() / 8, canvas.getWidth() / 8, paint);
                     break;
                 case 3:
                     paint.setColor(Color.GRAY);
                     canvas.drawCircle(x + i * (canvas.getWidth() / 4), canvas.getHeight() - canvas.getWidth() / 8, canvas.getWidth() / 8, paint);
                     break;
             }
        }
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
                    DrawRandomFigure(canvas, paint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
