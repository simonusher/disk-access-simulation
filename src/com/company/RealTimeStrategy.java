package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Szymon on 09.04.2017.
 */
public abstract class RealTimeStrategy extends Strategy{
    List<Request> activeRequests;
    Request activeRequest;
    int diskHeadMoves = 0;

    public RealTimeStrategy() {
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public RealTimeStrategy(Queue queue) {
        super(queue);
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public RealTimeStrategy(Queue queue, int currentAddress) {
        super(queue, currentAddress);
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public void addNewRequests(){
        for (int i = 0; i < queue.getRequests().size(); i++) {
            Request r = queue.getRequests().get(i);
            if(timePassed >= r.getArrivalTime()){
                activeRequests.add(r);
            }
            else break;
        }
        for (Request r: activeRequests) {
            queue.getRequests().remove(r);
        }
    }

    public boolean moveCurrentAdress(){
        boolean isAddressCorrect = true;
        if(activeRequest.getMemAdress() < currentAddress){
            currentAddress--;
            diskHeadMoves++;
            isAddressCorrect = false;
        }
        else if(activeRequest.getMemAdress() > currentAddress){
            currentAddress++;
            diskHeadMoves++;
            isAddressCorrect = false;
        }
        return isAddressCorrect;
    }

    public int getDiskHeadMoves() {
        return diskHeadMoves;
    }

    public void sort(Comparator c){
        activeRequests.sort(c);
    }
}