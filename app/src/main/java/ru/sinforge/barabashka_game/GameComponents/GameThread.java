package ru.sinforge.barabashka_game.GameComponents;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import ru.sinforge.barabashka_game.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.random;

public class GameThread extends Thread{
    private final Object sync = new Object();
    private final int[] Correct_answers = {1, 4};

    private boolean running = true;
    private final SurfaceHolder surfaceHolder;
    private final Context context;
    private GameField gameField;


    public GameThread (Context context, SurfaceHolder surfaceHolder, GameField gameField) {
        this.gameField = gameField;
        this.context = context;
        this.surfaceHolder = surfaceHolder;
    }



    public void requestStop() {
        running = false;
    }







    //Метод для проверки корректного ответа
    public void CheckAnswer(int x, int y) {
        int ans = gameField.CheckAnswer(x,y);
        switch (ans) {
            case 1:
                COUNT_PLAYER1++;
                synchronized (sync) {
                    gameField.loadTextures(context);
                }
                break;
            case 2:
                synchronized (sync) {
                    gameField.loadTextures(context);
                }
                COUNT_PLAYER2++;
                break;
        }
    }




    private int COUNT_PLAYER1 = 0;
    private int COUNT_PLAYER2 = 0 ;
    @Override
    public void run() {
        Paint paint = new Paint();
        paint.setTextSize(40);
        Canvas canvas = surfaceHolder.lockCanvas();
        gameField.MakeField(canvas);
        gameField.loadTextures(context);
        surfaceHolder.unlockCanvasAndPost(canvas);
        while (running) {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawARGB(255, 255, 255, 255);
                    paint.setColor(Color.RED);
                    paint.setARGB(255, 255, 0, 0);
                    paint.setARGB(255, 0, 0, 255);
                    canvas.drawText(""+COUNT_PLAYER1, 30 , 30, paint);
                    canvas.drawText(""+COUNT_PLAYER2, canvas.getWidth()- 30 , 30, paint);
                    paint.setStyle(Paint.Style.FILL);
                    synchronized (sync) {
                        gameField.DrawImg(canvas,paint);
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }

}
