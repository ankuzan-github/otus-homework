package ru.otus.hw03.oom;

import java.util.ArrayList;
import java.util.List;

public class OOM implements OOMMBean {
    private final int loopCounter;
    private volatile int size = 0;
    private List<Object[]> oomList = new ArrayList<>();

    OOM(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            Object[] array = new Object[local];
            for (int i = 0; i < local; i++) {
                array[i] = new String(new char[0]);
            }
            if (idx % 2 == 0)
                oomList.add(array);
        Thread.sleep(3);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }

}
