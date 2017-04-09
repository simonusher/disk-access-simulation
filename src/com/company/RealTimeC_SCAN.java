package com.company;

/**
 * Created by Szymon on 09.04.2017.
 */
public class RealTimeC_SCAN extends RealTimeSCAN {

    public RealTimeC_SCAN(Queue queue) {
        super(queue);
    }

    public void switchEdges(int index) {
        addToSeries(index, currentAddress);
        currentAddress = 0;
        addToSeries(index, currentAddress);
    }

    @Override
    public void findCloserEdge() {
        closerEdge = queue.getDiskSize();
    }

    @Override
    public void saveChart(String filename) {
        super.saveChart("RealTimeC-SCAN.jpg");
    }
}
