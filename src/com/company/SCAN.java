package com.company;

import com.sun.org.apache.regexp.internal.RE;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Szymon on 08.04.2017.
 */
public class SCAN extends Strategy{
    int closerEdge;

    public SCAN() {
    }

    public SCAN(Queue queue) {
        super(queue);
        queue.requests.sort(new MemoryAdressComparator());
        findCloserEdge();
    }

    public SCAN(Queue queue, int currentAddress) {
        super(queue, currentAddress);
        queue.requests.sort(new MemoryAdressComparator());
        findCloserEdge();
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int index = 1;

        while(!queue.requests.isEmpty()) {
            Set<Request> set = findRequest();

            for (Request r: set) {
                queue.getRequests().remove(r);
                addToSeries(index, currentAddress);
                index++;
            }

            if(currentAddress < closerEdge){
                currentAddress ++;
                timePassed ++;
            }
            else if(currentAddress > closerEdge){
                currentAddress --;
                timePassed ++;
            }
            else {
                switchEdges(index);
                index++;
            }
        }

        createChart();
        saveChart("SCAN.jpg");
    }


    public Set<Request> findRequest(){
        Set<Request> set = new HashSet<Request>();
        for (Request r: queue.getRequests()) {
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
        if(currentAddress > Math.abs(queue.getRequests().size() - currentAddress)) x = queue.getDiskSize();
        closerEdge = x;
    }
}
