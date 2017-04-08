package com.company;

import java.util.ArrayList;

/**
 * Created by Igor on 20.03.2017.
 */
public class Disk {
    int numberOfBlocks;
    int diskHeadPosition;
    ArrayList<Request> disk;

    public Disk(int numberOfBlocks){
        this.numberOfBlocks = numberOfBlocks;
        disk = new ArrayList<>();
    }

    public Disk(int numberOfBlocks, int diskHeadPosition){
        this.numberOfBlocks = numberOfBlocks;
        this.diskHeadPosition = diskHeadPosition;

    }

}
