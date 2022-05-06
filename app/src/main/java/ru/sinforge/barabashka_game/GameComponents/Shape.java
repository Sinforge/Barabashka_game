package ru.sinforge.barabashka_game.GameComponents;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Shape {
    private Rect coordinates;
    private Bitmap texture;

    public Shape(){}

    public Shape(Rect coordinates, Bitmap texture) {
        this.coordinates = coordinates;
        this.texture = texture;
    }

    public Rect getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Rect coordinates) {
        this.coordinates = coordinates;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public void setTexture(Bitmap texture) {
        this.texture = texture;
    }




    public void drawShape(Canvas canvas, Paint paint) {
        canvas.drawBitmap(texture, coordinates.left, coordinates.top, paint);
    }
}
