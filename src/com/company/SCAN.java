package com.company;

/**
 * Created by Szymon on 08.04.2017.
 */
public class SCAN extends Strategy{
    int closerEdge;

    public SCAN(Queue queue) {
        super(queue);
        queue.requests.sort(null);
        findCloserEdge();
    }

    public SCAN(Queue queue, int currentAddress) {
        super(queue, currentAddress);
        queue.requests.sort(null);
        findCloserEdge();
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int index = 1;

        while(!queue.requests.isEmpty()){
            while (currentAddress != closerEdge){
                Request r = findClosest();
                if(
            }
        }

        for (Integer i: queue.requests) {
            timePassed += Math.abs(i - currentAddress);
            currentAddress = i;
            series.add(index, currentAddress);
            index ++;
        }
        createChart();
        saveChart("SCAN");
    }


    public Request findClosest(){
        Request r = queue.requests.get(0);
        if(closerEdge == 0){
            for (int i = queue.requests.size() - 1; i >= 0; i--) {
                r = queue.requests.get(i);
                if(r.getMemAdress() <= currentAddress) break;
            }
        }
        else {
            for (int i = 0 ; i < queue.requests.size(); i++) {
                r = queue.requests.get(i);
                if(r.getMemAdress() >= currentAddress) break;
            }
        }
        return r;
    }

    public void findCloserEdge(){
        int x = 0;
        if(currentAddress > Math.abs(queue.requests.size() - currentAddress)) x = queue.requests.size() - 1;
        closerEdge = x;
    }
}
