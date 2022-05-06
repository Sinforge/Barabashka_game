package ru.sinforge.barabashka_game.GameComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import ru.sinforge.barabashka_game.GameComponents.GameThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getHolder().addCallback(this);
    }

    private GameThread gameThread;
    private GameField gameField;
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameField = new GameField(getContext());
        gameThread = new GameThread(getContext(), getHolder(), gameField);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        gameThread.requestStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameThread.CheckAnswer((int)event.getX(),(int)event.getY());
        return false;
    }
}
