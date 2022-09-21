package me.tojaeung.testing;

public class Junit {
    private JunitStatus status;
    private int limit;

    public Junit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }

    public JunitStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }
}
