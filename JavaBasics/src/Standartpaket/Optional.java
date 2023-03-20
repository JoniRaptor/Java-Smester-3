package Standartpaket;

public class Optional<T> {
    private T value;

    public Optional(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
