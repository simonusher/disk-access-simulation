package com.company;

import com.company.Queue;
import com.company.RealTimeStrategy;
import com.company.Request;
import com.company.comparators.DeadlineComparator;

import java.util.Set;

/**
 * Created by Szymon on 10.04.2017.
 */
public class FD_SCAN extends RealTimeStrategy {

    public FD_SCAN(Queue queue) {
        super(queue);
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int index = 1;

        while(!activeRequests.isEmpty() || !queue.getRequests().isEmpty()){
            if(!activeRequests.isEmpty()) {
                activeRequest = findClosestFeasibleDeadline();
            }
            Set<Request> set = findRequest();

            for (Request r: set) {
                activeRequests.remove(r);
                addToSeries(index, currentAddress);
                index++;
            }

            if(activeRequest == null){
                timePassed++;
                addNewRequests();
            }

            else if(currentAddress < activeRequest.getMemAdress()){
                currentAddress ++;
                diskHeadMoves ++;
                timePassed ++;
                addNewRequests();
            }
            else if(currentAddress > activeRequest.getMemAdress()){
                currentAddress --;
                diskHeadMoves ++;
                timePassed ++;
                addNewRequests();
            }
            else {
                timePassed++;
                addNewRequests();
            }
        }
        createChart();
        saveChart("RealTimeFD-SCAN.jpg");
    }

    public Request findClosestFeasibleDeadline() {
        for (Request r : activeRequests) {
            if (r.getDeadline() != -1 && (r.getDeadline() - timePassed < Math.abs(currentAddress - r.getMemAdress()))) {
//                System.out.println("Current address:" + currentAddress + " Address: " + r.getMemAdress() + " Time passed:" + timePassed + " Deadline:" + r.getDeadline());
                r.setDeadline(-1);
                notServedBeforeDeadline++;
            }
        }
        activeRequests.sort(new DeadlineComparator(currentAddress));
        return activeRequests.get(0);
    }
}
