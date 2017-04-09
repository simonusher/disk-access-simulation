package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Queue fc = new Queue(100, 100, 150);
//        System.out.println(fc);
//        FCFS f = new FCFS(fc);
//        f.run();
//        System.out.println(f.getTimePassed());
//
//        SSTF s = new SSTF(fc);
//        s.run();
//        System.out.println(s.getTimePassed());
//
//        SCAN sc = new SCAN(fc);
//        sc.run();
//        System.out.println(sc.getTimePassed());
//
//        C_SCAN csc = new C_SCAN(fc);
//        csc.run();
//        System.out.println(csc.getTimePassed());
//
//        EDF edf = new EDF(fc);
//        edf.run();
//        System.out.println(edf.getDiskHeadMoves());

        RealTimeSSTF rsstf = new RealTimeSSTF(fc);
        rsstf.run();
        System.out.println(rsstf.getDiskHeadMoves());
    }
}
