package com.company.comparators;

import com.company.Request;

import java.util.Comparator;

/**
 * Created by Szymon on 09.04.2017.
 */
public class DeadlineComparator implements Comparator{
    int currentAddress;

    public DeadlineComparator(int currentAdress) {
        this.currentAddress = currentAdress;
    }

    @Override
    public int compare(Object o1, Object o2) {
        int x = 0;
        Request r1 = (Request)o1;
        Request r2 = (Request)o2;

        if(r1.getDeadline() == -1){
            if(r2.getDeadline() == -1){
                if(Math.abs(r1.getMemAdress() - currentAddress) > Math.abs(r2.getMemAdress() - currentAddress)){
                    x = -1;
                }
                else if(Math.abs(r1.getMemAdress() - currentAddress) < Math.abs(r2.getMemAdress() - currentAddress)){
                    x = 1;
                }
            }
            else{
                x = -1;
            }
        }
        else if(r2.getDeadline() == -1){
            x = 1;
        }
        else{
            if(r1.getDeadline()> r2.getDeadline()) x = -1;
            else if(r1.getDeadline() < r2.getDeadline()) x = 1;
        }
        return x;
    }
}
