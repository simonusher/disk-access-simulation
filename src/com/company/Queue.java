package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by Igor on 20.03.2017.
 */
public class Queue {
    int numberOfRequests;
    int maxArrivalTime;
    int diskSize;
    List<Request> requests;

    public Queue() {
    }

    public Queue(Queue q){
        this.maxArrivalTime = q.getMaxArrivalTime();
        this.numberOfRequests = q.getNumberOfRequests();
        this.diskSize = q.getDiskSize();
        this.requests = new ArrayList<Request>(q.getRequests());
    }

    public Queue(int numberOfRequests, int diskSize, int maxArrivalTime){
        this.numberOfRequests = numberOfRequests;
        this.maxArrivalTime = maxArrivalTime;
        ArrayList<Integer> arrivalTimeTab = new ArrayList<>();
        Random generator = new Random();

        for (int i = 0; i < numberOfRequests; i++) {
            arrivalTimeTab.add(generator.nextInt(maxArrivalTime + 1));
        }

        arrivalTimeTab.sort(null);

        this.diskSize = diskSize;
        requests = new ArrayList();
        while (requests.size() < numberOfRequests) {

            int x = generator.nextInt(this.diskSize) + 1;

            int deadline = arrivalTimeTab.get(0) + generator.nextInt((int)(0.2 * this.diskSize)) + (int)(0.1 * this.diskSize);
            requests.add(new Request(x, arrivalTimeTab.get(0), deadline));
            arrivalTimeTab.remove(0);
            for(int j = 0; j < (int)(0.07 * numberOfRequests); j++){

                if(requests.size() >= numberOfRequests) break;
                int y =0;
                while(y <= 0 || y > diskSize){
                    y = (int)(generator.nextGaussian()*(diskSize/6) + x);
                }
                requests.add(new Request(y, arrivalTimeTab.get(0), -1));
                arrivalTimeTab.remove(0);
            }
        }

        XYSeries series = new XYSeries("");
        for (int i = 1; i <= requests.size(); i++) {
            series.add(i, requests.get(i-1).getMemAdress());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        // Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Zgłoszenia", // Title
                "Numer zgłoszenia", // x-axis Label
                "Adres zgłoszenia", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );



//        requests.sort(null);

        try(FileWriter fwr = new FileWriter("xd.csv")){
            for (int i = 0; i < requests.size() ; i++) {
                fwr.write(i +  ";");
            }
            fwr.write(System.lineSeparator());
            for (int i = 0; i < requests.size(); i++) {
                fwr.write(requests.get(i) + ";");
            }
            fwr.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public String toString(){
        StringBuilder sbr = new StringBuilder();
        for (Request r: requests) {
            sbr.append(r.toString());
        }
        return sbr.toString();
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }


    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }
}
