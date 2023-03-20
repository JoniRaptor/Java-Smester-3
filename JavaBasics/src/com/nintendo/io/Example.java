package com.nintendo.io;

public class Example {

    public final static String WINDOWTITEL = "Excel"; //final --> Konstante, ohne final nur static Variable

    private int age;

    public Example(int age) {
        super();            //ruft parent Konstruktor auf (hier standart Konstruktor)
        this.age = age;
    }

    public static void test() {
        System.out.println(Example.WINDOWTITEL);
    }
}
