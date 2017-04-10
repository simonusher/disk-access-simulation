package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.*;

/**
 * Created by Szymon on 09.04.2017.
 */
public abstract class RealTimeStrategy extends Strategy{
    int notServedBeforeDeadline = 0;
    List<Request> activeRequests;
    Request activeRequest;
    int diskHeadMoves = 0;

    public RealTimeStrategy() {
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public RealTimeStrategy(Queue queue) {
        super(queue);
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public RealTimeStrategy(Queue queue, int currentAddress) {
        super(queue, currentAddress);
        this.activeRequests = new ArrayList<>();
        addNewRequests();
    }

    public void addNewRequests(){
        for (int i = 0; i < queue.getRequests().size(); i++) {
            Request r = queue.getRequests().get(i);
            if(timePassed >= r.getArrivalTime()){
                activeRequests.add(r);
            }
            else break;
        }
        for (Request r: activeRequests) {
            queue.getRequests().remove(r);
        }
    }

    public boolean moveCurrentAdress(){
        boolean isAddressCorrect = true;
        if(activeRequest.getMemAdress() < currentAddress){
            currentAddress--;
            diskHeadMoves++;
            isAddressCorrect = false;
        }
        else if(activeRequest.getMemAdress() > currentAddress){
            currentAddress++;
            diskHeadMoves++;
            isAddressCorrect = false;
        }
        return isAddressCorrect;
    }

    public int getDiskHeadMoves() {
        return diskHeadMoves;
    }

    public void sort(Comparator c){
        activeRequests.sort(c);
    }

    public int getNotServedBeforeDeadline() {
        return notServedBeforeDeadline;
    }

    public Set<Request> findRequest(){
        Set<Request> set = new HashSet<Request>();
        for (Request r: activeRequests) {
            if(r.getMemAdress() == currentAddress) set.add(r);
        }
        return set;
    }

    public void createChart(){
        dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        String className = this.getClass().getName();
        className = className.substring(12);
        chart = ChartFactory.createXYLineChart(
                className, // Title
                "Czas", // x-axis Label
                "Adres zgłoszenia", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
    }
}
