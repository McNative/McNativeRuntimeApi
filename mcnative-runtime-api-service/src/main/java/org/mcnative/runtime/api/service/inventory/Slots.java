package org.mcnative.runtime.api.service.inventory;

import java.util.Arrays;

public class Slots {

    public static int[] slot(int index){
        return new int[]{index};
    }

    public static int[] slot(int x,int y){
        return slot((y)*9+x);
    }

    public static int[] range(int start,int end){
        int[] slots = new int[((end-start)+1)*9];
        int slot = start*9;
        for (int i = 0; i < slots.length; i++) {
            slots[i] = slot;
            slot++;
        }
        return slots;
    }

    public static int[] lines(int... lines){
        int[] slots = new int[lines.length*9];
        int index = 0;
        for (int line : lines) {
            for (int slot : line(line)) {
                slots[index] = slot;
                index++;
            }
        }
        return slots;
    }

    public static int[] line(int line){
        int[] slots = new int[9];
        int slot = line*9;
        for (int i = 0; i < slots.length; i++) {
            slots[i] = slot;
            slot++;
        }
        return slots;
    }

    public static int[] of(int... slots) {
        return slots;
    }
}
