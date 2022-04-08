package ru.sinforge.barabashka_game;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.random;

public class GameThread extends Thread{
    private boolean running = true;
    private SurfaceHolder surfaceHolder;
    private int number_of_figure;
    //private String[] figures = {"Circle", "Rectangle", "Square", "Rhombus", "Oval" };
    private String[] figures = {"Circle", "Rectangle", "Square", "Oval"};
    private int[] colorArray=  {Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLACK, Color.BLUE, Color.MAGENTA};



    public GameThread (Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }



    public void requestStop() {
        running = false;
    }

    private String base_figure;
    private int base_color;
    private int base_digit;

    //По правилам игры нужно сделать, чтобы было посередине спавнилась фигура с числом и цветом(Либо найти полностью
    //противоположную, либо абсолютно такую же( Пусть 0- раунд на нахождение полностью отличной, 1- раунд на нахождение
    //абсолютно такой же

    public void DrawBaseFigure(Canvas canvas, Paint paint) {
        //Параметры основной фигуры
        base_figure = "Circle";//figures[(int)(random() * 4)]; //фигура
        base_color = colorArray[(int) (random()* 7)]; // цвет
        base_digit = (int) (random() * 10); // цифра
        paint.setColor(base_color);
        if (base_figure.equals("Circle")) {
            canvas.drawCircle((float)canvas.getWidth()/ 2, (float) canvas.getHeight()/2, canvas.getWidth() / 8, paint);
        }
        else if (base_figure.equals("Square")) {
            Rect rect = new Rect();
            rect.set( (canvas.getWidth()/5 *2), canvas.getHeight()/5 * 2, canvas.getWidth()/5 * 3, (canvas.getHeight()/5 * 2) + canvas.getWidth()/5);
            canvas.drawRect(rect, paint);

        }
        paint.setARGB(255, 120, 120, 120);
        paint.setTextSize(canvas.getWidth() / 8);
        canvas.drawText(String.valueOf(base_digit),canvas.getWidth()/2, canvas.getHeight()/2, paint);

    }

    //TODO : finish game logic
    private int correct_figure;
    public void mode0 (Canvas canvas,Paint paint) { //Найти полностью отличный
        correct_figure = (int) (random() * 4);
        for(int i =0; i < 4; i++) {


        }

    }
    // TODO : realize method for drawing random figures
    public void DrawRandomFigures(Canvas canvas, Paint paint) {
        //Выбор режима (0 или 1):
        int mode = (int)(random() * 2);
        DrawBaseFigure(canvas, paint);
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
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawARGB(255, 0, 255, 0);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setARGB(255, 255, 0, 0);
                    DrawRandomFigures(canvas, paint);
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
