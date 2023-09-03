package org.kvto;

import java.util.ArrayList;

public class Memory {
    ArrayList<Double> mem;

    public Memory(String name) {
        mem = new ArrayList<>(512);
    }

    public double read(int index) {
        return mem.get(index);
    }

    public double read(double index) {
        return mem.get((int) Math.round(index));
    }

    public void write(int index, double value) {
        mem.set(index, value);
    }

    public void write(double index, double value) {
        mem.set((int) Math.round(index), value);
    }
}
