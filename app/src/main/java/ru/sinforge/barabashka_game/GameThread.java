package ru.sinforge.barabashka_game;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
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
    private int[] Correct_answers = {4};
    private final ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
    private boolean running = true;
    private SurfaceHolder surfaceHolder;
    private final Context context;
    private int number_of_figure;
    //private String[] figures = {"Circle", "Rectangle", "Square", "Rhombus", "Oval" };
    private String[] figures = {"Circle", "Rectangle", "Square", "Oval"};
    private int[] colorArray=  {Color.RED, Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLACK, Color.BLUE, Color.MAGENTA};



    public GameThread (Context context, SurfaceHolder surfaceHolder) {
        this.context = context;
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
    //Заготовим готовые уровни

    public String SelectRandomLevel() {
        int level_number = (int) (Math.random() * 5);
        return ("level" +level_number + "/");
    }



    private Rect MainShape;
    private ArrayList<Rect> ShapesRects ;
    private ArrayList<Bitmap> ShapesTextures;



    public void MakeField(Canvas canvas) {
        ShapesRects = new ArrayList<Rect>();
        MainShape = new Rect(canvas.getWidth()/ 5 * 2,30, canvas.getWidth()/5 * 3, canvas.getHeight()/3);
        for(int i = 1 ; i < 5; i++) {
            ShapesRects.add(new Rect(50, (canvas.getHeight()/5)* i, canvas.getHeight()/5, (canvas.getHeight()/5)* (i+1) ));
        }
    }

    public void loadTextures() {
        ShapesTextures = new ArrayList<Bitmap>();
        for(int i = 1; i <=5; i++) {
            InputStream ims;
            try {
                ims = context.getAssets().open("level2/" + "shape"+i+".png");
            }
            catch(IOException ex) {
                return;
            }
            ShapesTextures.add(BitmapFactory.decodeStream(ims));
        }
    }
    public void DrawImg(Canvas canvas, Paint paint) {
        for(int i =1 ; i < 5; i++) {
            canvas.drawBitmap(ShapesTextures.get(i - 1), ShapesRects.get(i- 1).left, ShapesRects.get(i - 1).top, paint);
        }
        canvas.drawBitmap(ShapesTextures.get(4), MainShape.left, MainShape.top,paint);
    }



    public void CheckAnswer(int x, int y) {
        for(int i =0; i < 4;i++) {
            if(ShapesRects.get(i).contains(x, y) && i == 3) {
                COUNT_PLAYER++;
                break;
            }
        }
    }
    private int COUNT_PLAYER = 0;
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
                    paint.setColor(Color.RED);
                    paint.setARGB(255, 255, 0, 0);
                    paint.setARGB(255, 0, 0, 255);
                    canvas.drawText(""+COUNT_PLAYER, 30 , 30, paint);
                    paint.setStyle(Paint.Style.FILL);
                    DrawImg(canvas, paint);
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
