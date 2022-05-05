package ru.sinforge.barabashka_game;

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
    private final int[] Correct_answers = {1, 4};
    private final ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
    private boolean running = true;
    private final SurfaceHolder surfaceHolder;
    private final Context context;


    public GameThread (Context context, SurfaceHolder surfaceHolder) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
    }



    public void requestStop() {
        running = false;
    }




    //По правилам игры нужно сделать, чтобы было посередине спавнилась фигура с числом и цветом(Либо найти полностью
    //противоположную, либо абсолютно такую же( Пусть 0- раунд на нахождение полностью отличной, 1- раунд на нахождение
    //абсолютно такой же
    //Заготовим готовые уровни
    private int level_number;


    public String SelectRandomLevel() {
        this.level_number = 2;
        return ("level" +level_number);
    }



    private Rect MainShape;
    private ArrayList<Rect> ShapesRects_PLAYER1;
    private ArrayList<Rect> ShapesRects_PLAYER2;
    private ArrayList<Bitmap> ShapesTextures;



    //Создаем поле, куда будут помещаться наши фигуры
    public void MakeField(Canvas canvas) {
        ShapesRects_PLAYER1 = new ArrayList<Rect>();
        ShapesRects_PLAYER2 = new ArrayList<Rect>();
        MainShape = new Rect(canvas.getWidth()/ 5 * 2,30, canvas.getWidth()/5 * 3, canvas.getHeight()/3);
        for(int i = 1 ; i < 5; i++) {
            ShapesRects_PLAYER1.add(new Rect(50, (canvas.getHeight()/5)* i, canvas.getHeight()/5, (canvas.getHeight()/5)* (i+1) ));
            ShapesRects_PLAYER2.add(new Rect(canvas.getWidth()-200, (canvas.getHeight()/5)* i, canvas.getWidth()-200 + canvas.getHeight()/5 , (canvas.getHeight()/5)* (i+1) ));
        }
    }

    //TODO: fix bug with level1 (array with textures have only 3 elements)
    //Загружаем текстуры (рандомно из assets)
    public void loadTextures(){
        ShapesTextures = new ArrayList<Bitmap>();
        for(int i = 1; i <=5; i++) {
            InputStream ims;
            try {
                String level = SelectRandomLevel();
                ims = context.getAssets().open(level+"/" + "shape"+i+".png");
                ShapesTextures.add(BitmapFactory.decodeStream(ims));
                ims.close();
            }
            catch(IOException ex) {
                return;
            }

        }
    }

    //Рисуем фигурки
    public void DrawImg(Canvas canvas, Paint paint) {
        for(int i =1 ; i < 5; i++) {
            canvas.drawBitmap(ShapesTextures.get(i - 1), ShapesRects_PLAYER1.get(i- 1).left, ShapesRects_PLAYER1.get(i - 1).top, paint);
            canvas.drawBitmap(ShapesTextures.get(i - 1), ShapesRects_PLAYER2.get(i- 1).left, ShapesRects_PLAYER2.get(i - 1).top, paint);

        }
        canvas.drawBitmap(ShapesTextures.get(4), MainShape.left, MainShape.top,paint);
    }



    //Метод для проверки корректного ответа
    public void CheckAnswer(int x, int y) {
        for(int i =0; i < 4;i++) {
            if(ShapesRects_PLAYER1.get(i).contains(x, y) &&  i == Correct_answers[level_number-1] - 1) {
                COUNT_PLAYER1++;
                loadTextures();
                break;
            }
            Log.d("CHECK_ANSWER", "Координаты нажатия: " + x + " " + y);
            Log.d("CHECK_ANSWER", "Координаты фигурки правой: " + ShapesRects_PLAYER2.get(i).left + " "+ ShapesRects_PLAYER2.get(i).right + " "+ ShapesRects_PLAYER2.get(i).top + " "+ ShapesRects_PLAYER2.get(i).bottom);
            if(ShapesRects_PLAYER2.get(i).contains(x, y) && i== Correct_answers[level_number-1]) {
                COUNT_PLAYER2++;
                loadTextures();
                break;
            }
        }
    }
    private int COUNT_PLAYER1 = 0;
    private int COUNT_PLAYER2 = 0 ;
    @Override
    public void run() {
        Paint paint = new Paint();
        paint.setTextSize(40);
        Canvas canvas = surfaceHolder.lockCanvas();
        MakeField(canvas);
        loadTextures();
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
                    DrawImg(canvas, paint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }

    }

}
