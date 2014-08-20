package com.example.icarjs.myfirsttest;

/**
 * Created by icarjs on 20/08/14.
 */
public class Circle {
    public float centerX;
    public float centerY;
    public float radius;
    public int fillColor;

    float sqr(float i) {
        return i * i;
    }

    boolean contains(float x, float y) {
        return sqr(x - centerX) + sqr(y - centerY) <= sqr(radius);
    }

    boolean inView(int width, int height) {
        return (0 <= centerX) && (centerX < width)
                && (0 <= centerY) && (centerY < height);
    }

}
