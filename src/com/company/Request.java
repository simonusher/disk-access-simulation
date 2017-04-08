package com.company;

/**
 * Created by Igor on 20.03.2017.
 */
public class Request {
    private int arrivalTime;
    private int memAdress;
    private boolean isDone = false;

    public Request (int arrivalTime, int memAdress){
        this.arrivalTime = arrivalTime;
        this.memAdress = memAdress;
    }
}
