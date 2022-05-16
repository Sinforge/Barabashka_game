package ru.sinforge.barabashka_game.GameComponents;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.util.Log;
import android.view.SurfaceHolder;
import ru.sinforge.barabashka_game.Activities.ResultActivity;
import ru.sinforge.barabashka_game.Services.MusicService;

import static java.lang.Math.max;

public class GameThread extends Thread{
    private final Object sync = new Object();
    private final int[] Correct_answers = {1, 4};

    private int point_to_end = 20;
    private String player1_name;
    private String player2_name;


    private int COUNT_PLAYER1 = 0;
    private int COUNT_PLAYER2 = 0 ;
    private boolean running = true;
    private final SurfaceHolder surfaceHolder;
    private final Context context;
    private GameField gameField;


    public GameThread (Context context, SurfaceHolder surfaceHolder, GameField gameField) {
        this.gameField = gameField;
        this.context = context;
        this.surfaceHolder = surfaceHolder;
    }

    public void SetPlayersNames(String player1_name, String player2_name) {
        this.player1_name = player1_name;
        this.player2_name = player2_name;
    }

    public void SetPointToEnd(int point_to_end) {
        this.point_to_end = point_to_end;
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
                if(COUNT_PLAYER1 == point_to_end) {
                    Log.d("GAME_THREAD", "game over");
                    requestStop();
                    Intent intent = new Intent(context, ResultActivity.class);
                    intent.putExtra("Winner", player1_name);
                    intent.putExtra("Score1", COUNT_PLAYER1);
                    intent.putExtra("Score2", COUNT_PLAYER2);
                    context.stopService(new Intent(context, MusicService.class));
                    context.startActivity(intent);
                    break;
                }
                synchronized (sync) {
                    gameField.loadTextures(context);
                }
                break;
            case 2:
                COUNT_PLAYER2++;
                Log.d("GAME_THREAD", "game over");
                if(COUNT_PLAYER2 == point_to_end) {
                    Log.d("GAME_THREAD", "game over");
                    requestStop();
                    Intent intent = new Intent(context, ResultActivity.class);
                    intent.putExtra("Winner", player2_name);
                    intent.putExtra("Score1", COUNT_PLAYER1);
                    intent.putExtra("Score2", COUNT_PLAYER2);
                    context.stopService(new Intent(context, MusicService.class));
                    context.startActivity(intent);
                    break;
                }
                synchronized (sync) {
                    gameField.loadTextures(context);
                }

                break;
        }
    }




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
                    canvas.drawText(player1_name, 30, 80, paint);
                    canvas.drawText(""+COUNT_PLAYER2, canvas.getWidth()- 60 , 30, paint);
                    canvas.drawText(player2_name,canvas.getWidth()-80, 80, paint );
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
