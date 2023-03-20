package Standartpaket;

public abstract class A {

    private int age;

    public abstract String getName();

    public A(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
