package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.requests = new ArrayList<>(q.getRequests());
    }

    public Queue(int numberOfRequests, int diskSize, int maxArrivalTime, double percentageOfRequestsWithNormalDistribution,
                 double deadlineLowerBound, double deadlineUpperBound, int percentOfRequestsWithDeadline){
        this.numberOfRequests = numberOfRequests;
        this.maxArrivalTime = maxArrivalTime;
//        int percentOfRequests = percentOfRequestsWithDeadline
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

            int hasDeadline = generator.nextInt(percentOfRequestsWithDeadline) + 1;

            if (hasDeadline == 1) {
                int deadline = arrivalTimeTab.get(0) + generator.nextInt((int)((deadlineUpperBound - deadlineLowerBound) * this.diskSize)) + (int)(deadlineLowerBound * this.diskSize);
                requests.add(new Request(x, arrivalTimeTab.get(0), deadline));
            }
            else
            {
                requests.add(new Request(x, arrivalTimeTab.get(0), -1));
            }
            arrivalTimeTab.remove(0);
            for(int j = 0; j < (int)(percentageOfRequestsWithNormalDistribution * numberOfRequests); j++){

                if(requests.size() >= numberOfRequests) break;
                int y =0;
                while(y <= 0 || y > diskSize){
                    y = (int)(generator.nextGaussian()*(diskSize/6) + x);
                }
                hasDeadline = generator.nextInt(percentOfRequestsWithDeadline) + 1;
                if(hasDeadline == 1){
                    int deadline = arrivalTimeTab.get(0) + generator.nextInt((int)((deadlineUpperBound - deadlineLowerBound) * this.diskSize)) + (int)(deadlineLowerBound * this.diskSize);
                    requests.add(new Request(y, arrivalTimeTab.get(0), deadline));
                }
                else{
                    requests.add(new Request(y, arrivalTimeTab.get(0), -1));
                }
                arrivalTimeTab.remove(0);
            }
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
