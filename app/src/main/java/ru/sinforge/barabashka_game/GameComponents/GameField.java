package ru.sinforge.barabashka_game.GameComponents;

import android.content.Context;
import android.graphics.*;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import ru.sinforge.barabashka_game.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameField {
    MediaPlayer player;
    private Shape MainShape = new Shape();
    private ArrayList<Shape> Shapes_PLAYER1;
    private ArrayList<Shape> Shapes_PLAYER2;
    private int[] Correct_answers = {1, 4};
    private int level_number;
    private Context context;

    public GameField(Context context) {
        this.context = context;
    }
    public String SelectRandomLevel() {
        this.level_number = (int)(Math.random()*2+ 1);
        return ("level" +level_number);
    }

    public void MakeField(Canvas canvas) {
        Shapes_PLAYER1 = new ArrayList<Shape>();
        Shapes_PLAYER2 = new ArrayList<Shape>();
        MainShape.setCoordinates(new Rect(canvas.getWidth()/ 5 * 2,30, canvas.getWidth()/5 * 3, canvas.getHeight()/3));
        for(int i = 1 ; i < 5; i++) {
            Shape shape1 = new Shape();
            Shape shape2 =  new Shape();
            shape1.setCoordinates(new Rect(50, (canvas.getHeight()/5)* i, canvas.getHeight()/5, (canvas.getHeight()/5)* (i+1) ));
            shape2.setCoordinates(new Rect(canvas.getWidth()-200, (canvas.getHeight()/5)* i, canvas.getWidth()-200 + canvas.getHeight()/5 , (canvas.getHeight()/5)* (i+1) ));
            Shapes_PLAYER1.add(shape1);
            Shapes_PLAYER2.add(shape2);
        }
    }
    public void loadTextures(Context context) {
            String level = SelectRandomLevel();
            for (int i = 1; i <= 5; i++) {
                InputStream ims;
                try {
                    ims = context.getAssets().open(level + "/" + "shape" + i + ".png");
                    if (i != 5) {
                        Bitmap bitmap = BitmapFactory.decodeStream(ims);
                        Shapes_PLAYER1.get(i - 1).setTexture(bitmap);
                        Shapes_PLAYER2.get(i - 1).setTexture(bitmap);
                    }
                    else {
                        MainShape.setTexture(BitmapFactory.decodeStream(ims));
                    }
                    ims.close();
                } catch (IOException ex) {
                    Log.d("СЧИТЫВАНИЕ ФАЙЛОВ", "ошибочка");
                    return;
                }

            }

    }
    //Рисуем фигурки
    public void DrawImg(Canvas canvas, Paint paint) {
        for(int i =0 ; i < 4; i++) {
            Shapes_PLAYER1.get(i).drawShape(canvas, paint);
            Shapes_PLAYER2.get(i).drawShape(canvas, paint);
        }
        MainShape.drawShape(canvas, paint);
    }

    //Метод для проверки корректного ответа
    //0 - возвращает, когда нет правильного ответа
    //1 - когда ответил правильно игрок 1
    //2 - когда ответил правильно игрок 2
    public int CheckAnswer(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (Shapes_PLAYER1.get(i).getCoordinates().contains(x, y) && i == Correct_answers[level_number - 1] - 1) {
                return 1;
            }
            if (Shapes_PLAYER2.get(i).getCoordinates().contains(x, y) && i == Correct_answers[level_number - 1] - 1) {
                return 2;
            }
        }
        return 0;
    }


}
