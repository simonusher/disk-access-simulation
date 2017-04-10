package com.company;

import com.company.comparators.DeadlineComparator;

import java.util.Set;

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
                if(isThereDeadline()){
                    sortByDeadline();
                }
                activeRequest = activeRequests.get(0);
                if(moveCurrentAdress()){
                    if (activeRequest.getDeadline() < timePassed && activeRequest.getDeadline() != -1){
                        notServedBeforeDeadline++;
                    }
                    activeRequests.remove(activeRequest);
                    addNewRequests();
                }
                addToSeries(index, currentAddress);
                index++;
                timePassed++;
                if(activeRequest.getDeadline() == -1) addNewRequests();
            }
        }
        createChart();
        saveChart("RealTimeEDF.jpg");
    }

    public void sortByDeadline(){
        activeRequests.sort(new DeadlineComparator(timePassed));
    }

    public boolean isThereDeadline(){
        boolean isThereDeadline = false;
        for (Request r: activeRequests) {
            if(r.getDeadline() != -1){
                isThereDeadline = true;
                break;
            }
        }
        return isThereDeadline;
    }


}
