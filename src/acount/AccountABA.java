package acount;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//Reference: https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-concurrency-advanced-3/src/main/java/com/baeldung/abaproblem/Account.java

public class AccountABA {

    private AtomicInteger balance;
    private AtomicInteger transactionCount;
    private ThreadLocal<Integer> currentThreadCASFailureCount;

    public AccountABA() {
        this.balance = new AtomicInteger(0);
        this.transactionCount = new AtomicInteger(0);
        this.currentThreadCASFailureCount = new ThreadLocal<>();
        this.currentThreadCASFailureCount.set(0);
    }

    public int getBalance() {
        return balance.get();
    }

    public int getTransactionCount() {
        return transactionCount.get();
    }

    public int getCurrentThreadCASFailureCount() {
        return currentThreadCASFailureCount.get();
    }

    public boolean withdraw(int amount) {
        int current = getBalance();
        maybeWait();
        boolean result = balance.compareAndSet(current, current - amount);
        if (result) {
            transactionCount.incrementAndGet();
        } else {
            int currentCASFailureCount = currentThreadCASFailureCount.get();
            currentThreadCASFailureCount.set(currentCASFailureCount + 1);
        }
        return result;
    }

    private void maybeWait() {
        if ("thread1".equals(Thread.currentThread().getName())) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean deposit(int amount) {
        int current = balance.get();
        boolean result = balance.compareAndSet(current, current + amount);
        if (result) {
            transactionCount.incrementAndGet();
        } else {
            int currentCASFailureCount = currentThreadCASFailureCount.get();
            currentThreadCASFailureCount.set(currentCASFailureCount + 1);
        }
        return result;
    }
}
