package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Queue fc = new Queue(100, 100, 1000);
//        System.out.println(fc);
        FCFS f = new FCFS(fc);
        f.run();
        System.out.println(f.getTimePassed());

        SSTF s = new SSTF(fc);
        s.run();
        System.out.println(s.getTimePassed());

        SCAN sc = new SCAN(fc);
        sc.run();
        System.out.println(sc.getTimePassed());

        C_SCAN csc = new C_SCAN(fc);
        csc.run();
        System.out.println(csc.getTimePassed());
//
        EDF edf = new EDF(fc);
        edf.run();
        System.out.println(edf.getDiskHeadMoves());
        System.out.println(edf.notServedBeforeDeadline);

        RealTimeFCFS rfcfs = new RealTimeFCFS(fc);
        rfcfs.run();
        System.out.println(rfcfs.getDiskHeadMoves());
        System.out.println(rfcfs.notServedBeforeDeadline);


        RealTimeSSTF rsstf = new RealTimeSSTF(fc);
        rsstf.run();
        System.out.println(rsstf.getDiskHeadMoves());
        System.out.println(rsstf.notServedBeforeDeadline);

        RealTimeSCAN rtsc = new RealTimeSCAN(fc);
        rtsc.run();
        System.out.println(rtsc.getDiskHeadMoves());
        System.out.println(rtsc.notServedBeforeDeadline);

        RealTimeC_SCAN rtcsc = new RealTimeC_SCAN(fc);
        rtcsc.run();
        System.out.println(rtcsc.getDiskHeadMoves());
        System.out.println(rtcsc.notServedBeforeDeadline);
    }
}
