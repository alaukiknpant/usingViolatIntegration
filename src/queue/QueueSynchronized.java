package queue;

import java.util.Arrays;


public class QueueSynchronized  {
    private int indGet;
    private int indPut;
    private int countElements;
    private int[] items;
    private int inc(int i) {
        return (++i == items.length ? 0 : i);
    }

    public QueueSynchronized() {
        items = new int[1000];
        indPut = 0;
        indGet = 0;
        countElements = 0;

    }

    public synchronized void put(Integer i) throws Exception  {
        if (countElements == items.length) {
            throw new Exception("Queue is full");
        } else {
            items[indPut] = i;
            indPut = inc(indPut);
            countElements++;
        }
    }

    public synchronized int get()  throws Exception{
        if (countElements == 0) {
            throw new Exception("Queue is empty");
        } else {
            int ret = items[indGet];
            indGet = inc(indGet);
            countElements--;
            return ret;
        }

    }
}

