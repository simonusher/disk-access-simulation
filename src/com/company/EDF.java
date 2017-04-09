package com.company;

import java.util.List;

/**
 * Created by Szymon on 09.04.2017.
 */
public class EDF extends RealTimeStrategy {

    public EDF(Queue queue) {
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
                sortByDeadline();
                activeRequest = activeRequests.get(0);
                if(moveCurrentAdress()){

                    activeRequests.remove(activeRequest);
                }
                addToSeries(index, currentAddress);
                index++;
                timePassed++;
                addNewRequests();
            }
        }
        createChart();
        saveChart("EDF.jpg");
    }

    public void sortByDeadline(){
        activeRequests.sort(new DeadlineComparator(timePassed));
    }



}
