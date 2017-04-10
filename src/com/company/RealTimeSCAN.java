package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Szymon on 09.04.2017.
 */
public class RealTimeSCAN extends RealTimeStrategy {
    protected int closerEdge;

    public RealTimeSCAN() {
    }

    public RealTimeSCAN(Queue queue) {
        super(queue);
        findCloserEdge();
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int index = 1;

        while(!activeRequests.isEmpty() || !queue.getRequests().isEmpty()){
            Set<Request> set = findRequest();

            for (Request r: set) {
                activeRequests.remove(r);
                if (r.getDeadline() < timePassed && r.getDeadline() != -1){
                    notServedBeforeDeadline++;
                }
                addToSeries(index, currentAddress);
                index++;
            }

            if(currentAddress < closerEdge){
                currentAddress ++;
                diskHeadMoves ++;
                timePassed ++;
                addNewRequests();
            }
            else if(currentAddress > closerEdge){
                currentAddress --;
                diskHeadMoves ++;
                timePassed ++;
                addNewRequests();
            }
            else {
                switchEdges(index);
                index++;
            }
        }
        createChart();
        saveChart("RealTimeSCAN.jpg");
    }


    public Set<Request> findRequest(){
        Set<Request> set = new HashSet<Request>();
        for (Request r: activeRequests) {
            if(r.getMemAdress() == currentAddress) set.add(r);
        }
        return set;
    }

    public void switchEdges(int index){
        addToSeries(index, currentAddress);
        if(closerEdge == 0) closerEdge = queue.getDiskSize();
        else closerEdge = 0;
    }

    public void findCloserEdge(){
        int x = 0;
        if(currentAddress > Math.abs(currentAddress - queue.getDiskSize())) x = queue.getDiskSize();
        closerEdge = x;
    }
}
