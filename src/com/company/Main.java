package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /**
         * Wartości początkowe
         * @param numberOfRequests - ilość generowanych zgłoszeń
         * @param diskSize - wielkość dysku
         * @param maxArrivalTime - maksymalny czas do którego będą generowane zgłoszenia
         * @param percentOfRequestsWithNormalDistribution - ilość zgłoszeń wygenerowanych według rozkładu normalnego
         *                                                po wygenerowaniu jednego zgłoszenia z rozkładu równomiernego
         *                                                wyrażona jako procent wszystkich zgłoszeń
         * @param deadlineLowerBound - dolna granica liczby dodawanej do arrivalTime, w celu utworzenia deadlinu
         *                           wyrażona jako procent wielkości dysku
         * @param deadlineUpperBound - jak wyżej, górna granica
         * @param percentOfRequestsWithDeadline - 1/percentOfRequestsWithDeadline zgłoszeń z deadlinem
         */
        int numberOfRequests = 2000;
        int diskSize = 100;
        int maxArrivalTime = 8000;
        double percentOfRequestsWithNormalDistribution = 0.07;
        double deadlineLowerBound = 0.4;
        double deadlineUpperBound = 0.8;
        int percentOfRequestsWithDeadline = 10;


        Queue fc = new Queue(numberOfRequests, diskSize, maxArrivalTime, percentOfRequestsWithNormalDistribution,
                deadlineLowerBound, deadlineUpperBound, percentOfRequestsWithDeadline);
//        System.out.println(fc);

//        System.out.println(fc);
        ArrayList<Strategy> strategies = new ArrayList<>();
        strategies.add(new FCFS(fc));
        strategies.add(new SSTF(fc));
        strategies.add(new SCAN(fc));
        strategies.add(new C_SCAN(fc));
        strategies.add(new RealTimeFCFS(fc));
        strategies.add(new RealTimeSSTF(fc));
        strategies.add(new RealTimeSCAN(fc));
        strategies.add(new RealTimeC_SCAN(fc));
        strategies.add(new EDF(fc));
        strategies.add(new FD_SCAN(fc));

        for (Strategy s: strategies) {
            s.run();
            if(s instanceof RealTimeStrategy){
                System.out.println(s.getClass().getSimpleName());
                System.out.println("Disk head moves: " + ((RealTimeStrategy)s).getDiskHeadMoves());
                System.out.println("Number of requests not served before deadline: " + ((RealTimeStrategy)s).notServedBeforeDeadline);
            }
            else {
                System.out.println(s.getClass().getSimpleName());
                System.out.println("Disk head moves: " + (s.getTimePassed()));
            }
        }


    }
}
