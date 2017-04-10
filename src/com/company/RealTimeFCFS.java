package com.company;

/**
 * Created by Szymon on 09.04.2017.
 */
public class RealTimeFCFS extends RealTimeStrategy{

    public RealTimeFCFS(Queue queue) {
        super(queue);
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int index = 1;

        while(!activeRequests.isEmpty() || !queue.getRequests().isEmpty()){
            if(activeRequests.isEmpty()){
                timePassed++;
                addNewRequests();
            }
            else{
                activeRequest = activeRequests.get(0);
                if(moveCurrentAdress()){
                    if (activeRequest.getDeadline() < timePassed && activeRequest.getDeadline() != -1){
                        notServedBeforeDeadline++;
                    }
                    activeRequests.remove(activeRequest);
                }
                addToSeries(index, currentAddress);
                index++;
                timePassed++;
                addNewRequests();
            }
        }
        createChart();
        saveChart("RealTimeFCFS.jpg");
    }
}
