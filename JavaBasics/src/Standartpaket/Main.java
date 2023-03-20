package Standartpaket;

import java.util.ArrayList;  //ArrayList, LinkedList erben von List
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

            A b = new B(42);
            System.out.println(b.getAge());

            IA a = new IB();
            IA c = new C();
            System.out.println(a.getName());
            System.out.println(c.getName());

            List<String> str = new ArrayList<String>();
            consumer(str);

            Optional<String> o = new Optional<String>("42");
            consumer1(o.getValue());

    }

    public static void consumer(List<String> str) {
        System.out.println(str.size());
    }

    public static void consumer1(String str) {
        System.out.println(str);
    }
}