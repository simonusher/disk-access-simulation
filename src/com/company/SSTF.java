package com.company;

/**
 * Created by Szymon on 08.04.2017.
 */
public class SSTF extends Strategy {

    public SSTF(Queue queue) {
        super(queue);
    }
    public SSTF(Queue queue, int currentAddress) {
        super(queue, currentAddress);
    }

    @Override
    public void run() {
        addToSeries(0, currentAddress);
        int k = 1;
        while(!queue.requests.isEmpty()){
            Request closest = findClosest();
            int address = closest.getMemAdress();
            timePassed += Math.abs(currentAddress - address);
            currentAddress = address;
            queue.requests.remove(closest);
            addToSeries(k, currentAddress);
            k++;
        }
        createChart();
        saveChart("SSTF.jpg");
    }

    public Request findClosest(){
        Request q = queue.requests.get(0);
        for (Request r : queue.getRequests()) {
            if(Math.abs(r.getMemAdress() - currentAddress) < Math.abs(q.getMemAdress() - currentAddress)) q = r;
        }
        return q;
    }


    public int getTimePassed() {
        return timePassed;
    }
}
