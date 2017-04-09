package com.company.comparators;

import com.company.Request;

import java.util.Comparator;

/**
 * Created by Szymon on 09.04.2017.
 */
public class ArrivalTimeComparator implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        int x = 0;
        if(((Request)o1).getArrivalTime() < ((Request)o2).getArrivalTime()) x = 1;
        else if(((Request)o1).getArrivalTime() > ((Request)o2).getArrivalTime()) x = -1;
        return x;
    }
}
