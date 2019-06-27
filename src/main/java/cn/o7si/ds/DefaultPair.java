package cn.o7si.ds;

public class DefaultPair<T, E> {

    private T first;
    private E second;

    public DefaultPair() {
    }

    public DefaultPair(T first, E second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public E getSecond() {
        return second;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "DefaultPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
