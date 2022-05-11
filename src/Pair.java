public class Pair <T,V>{
    T First;
    V Second;

    public Pair(T first, V second) {
        First = first;
        Second = second;
    }

    public Pair() {
    }

    public T getFirst() {
        return First;
    }

    public void setFirst(T first) {
        First = first;
    }

    public V getSecond() {
        return Second;
    }

    public void setSecond(V second) {
        Second = second;
    }
}
