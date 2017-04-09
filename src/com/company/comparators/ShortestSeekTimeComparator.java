package com.company.comparators;

import com.company.Request;

import java.util.Comparator;

/**
 * Created by Szymon on 09.04.2017.
 */
public class ShortestSeekTimeComparator implements Comparator {
    int currentAddress;

    public ShortestSeekTimeComparator(int currentAddress) {
        this.currentAddress = currentAddress;
    }

    @Override
    public int compare(Object o1, Object o2) {
        int x = 0;
        if(Math.abs(((Request)o1).getMemAdress() - currentAddress) < Math.abs(((Request)o2).getMemAdress() - currentAddress)) x = -1;
        else if(Math.abs(((Request)o1).getMemAdress() - currentAddress) > Math.abs(((Request)o2).getMemAdress() - currentAddress)) x = 1;
        return x;
    }
}
