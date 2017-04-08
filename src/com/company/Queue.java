package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Igor on 20.03.2017.
 */
public class Queue {
    int numberOfRequests;
    int maxArrivalTime;
    Disk disk;


    public Queue(int numberOfRequests, int maxArrivalTime){
        Random generator = new Random();
        List<Integer> memAddresses = new ArrayList();
        for (int i = 0; i < numberOfRequests; i++) {
            int x = generator.nextInt(100) + 1;
            memAddresses.add(x);
            i++;
            System.out.println("0");
            for(int j = 0; j < 5/*(int)(0.05 * numberOfRequests)*/; j++){
                int y =0;
                while(y <= 0 || y > 100){
                    y = (int)(generator.nextGaussian()*(50/3) + x);
                }
                memAddresses.add(y);
                i++;
                System.out.println("1");
            }
        }

//        memAddresses.sort(null);

        try(FileWriter fwr = new FileWriter("xd.csv")){
            for (int i = 0; i < memAddresses.size() ; i++) {
                if(i%2==0&&i%3==0) {
                    fwr.write("0;");
                }
                else fwr.write("1;");
            }
            fwr.write(System.lineSeparator());
            for (int i = 0; i < memAddresses.size(); i++) {
                fwr.write(memAddresses.get(i) + ";");
            }
            fwr.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
