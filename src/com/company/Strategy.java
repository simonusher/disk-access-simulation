package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

/**
 * Created by Igor on 02.04.2017.
 */
public abstract class Strategy {
    protected Queue queue;
    protected int timePassed = 0;
    protected int currentAddress = 50;
    protected XYSeries series;
    protected XYSeriesCollection dataset;
    JFreeChart chart;


    public Strategy() {

    }

    public Strategy(Queue queue) {
        this.queue = new Queue(queue);
        timePassed = 0;
        currentAddress = 51;
        this.series = new XYSeries("");
        this.dataset = new XYSeriesCollection();
    }

    public Strategy(Queue queue, int currentAddress) {
        this.queue = new Queue(queue);
        this.currentAddress = currentAddress;
        this.series = new XYSeries("");
        this.dataset = new XYSeriesCollection();
    }

    public abstract void run();

    public void addToSeries(int x, int y){
        series.add(x, y);
    }

    public void createChart(){
        dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        String className = this.getClass().getName();
        className = className.substring(12);
        chart = ChartFactory.createXYLineChart(
                className, // Title
                "Obsłużone zgłoszenie", // x-axis Label
                "Adres zgłoszenia", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
        );
    }

    public void saveChart(String filename){
        try {
            ChartUtilities.saveChartAsJPEG(new File(filename), chart, 1000, 600);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }
    }

    public int getTimePassed() {
        return timePassed;
    }
}
