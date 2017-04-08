package com.company;

/**
 * Created by Igor on 02.04.2017.
 */
public class FCFS extends Strategy {

    public FCFS(Queue queue) {
        super(queue);
    }
    public FCFS(Queue queue, int currentAddress) {
        super(queue, currentAddress);
    }

    public void run(){
        addToSeries(0, currentAddress);
        int k = 1;
        for (Request r: queue.requests) {
            int address = r.getMemAdress();
            timePassed += Math.abs(address - currentAddress);
            currentAddress = address;
            addToSeries(k, currentAddress);
            k++;
        }
        createChart();
        saveChart("FCFS.jpg");
    }

    public int getTimePassed() {
        return timePassed;
    }
}
