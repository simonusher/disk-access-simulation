package com.company;

import java.util.Comparator;

/**
 * Created by Szymon on 09.04.2017.
 */
public class MemoryAdressComparator implements Comparator{

    public int compare(Object o1, Object o2){
        int x = 0;
        if(((Request)o1).getMemAdress() > ((Request)o2).getMemAdress()) x = 1;
        else if (((Request)o1).getMemAdress() > ((Request)o2).getMemAdress()) x = -1;
        return x;
    }
}
