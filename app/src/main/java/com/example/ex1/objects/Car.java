package com.example.ex1.objects;

public class Car {
    private int positionX = 2; // נתיב אמצעי חדש בחמישה נתיבים
    private int positionY = 4; // שורה תחתונה של חמישה שורות

    public Car() {}

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
