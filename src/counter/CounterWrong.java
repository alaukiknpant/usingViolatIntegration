package counter;

public class CounterWrong {
    private int c;

    public CounterWrong() {
        c = 0;
    }

    
    public int incrementAndGet() {
        c++;
        return c;
    }
}
