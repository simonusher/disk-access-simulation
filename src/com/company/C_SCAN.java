package com.company;

/**
 * Created by Szymon on 09.04.2017.
 */
public class C_SCAN extends SCAN {

    public C_SCAN(Queue queue) {
        super(queue);
    }

    public C_SCAN(Queue queue, int currentAddress) {
        super(queue, currentAddress);
    }

    @Override
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
        super.saveChart("C-SCAN.jpg");
    }
}
