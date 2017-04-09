package com.company;

/**
 * Created by Igor on 20.03.2017.
 */
public class Request {
    private int arrivalTime;
    private int memAdress;
    private int deadline;
    private boolean wasServedBeforeDeadline = true;


    public Request(int memAdress) {
        this.memAdress = memAdress;
    }

    public Request (int memAdress, int arrivalTime){
        this.arrivalTime = arrivalTime;
        this.memAdress = memAdress;
    }

    public Request(int memAdress, int arrivalTime, int deadline) {
        this.arrivalTime = arrivalTime;
        this.memAdress = memAdress;
        this.deadline = deadline;
    }

    public String toString(){
        return memAdress + " " + arrivalTime + " " + deadline + System.lineSeparator();
    }

    public int getMemAdress() {
        return memAdress;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDeadline() {
        return deadline;
    }
}
