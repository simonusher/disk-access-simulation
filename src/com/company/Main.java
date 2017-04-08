package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Queue fc = new Queue(100, 100);
        FCFS f = new FCFS(fc);
        f.run();
        System.out.println(f.getTimePassed());

        SSTF s = new SSTF(fc);
        s.run();
        System.out.println(s.getTimePassed());



    }
}
