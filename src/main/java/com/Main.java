package com;

import lombok.val;
import org.jetbrains.annotations.Contract;

public class Main {

    @Contract(pure = true)
    public static boolean isBigger(final int a, final int b) {
        return a > b;
    }

    @Contract(pure = true)
    public int divide(final int a, final int b) {
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println(Main.isBigger(10, 6));
        System.out.println(Main.isBigger(11, 4));

        val main = new Main();
        System.out.println(main.divide(10, 2));
        System.out.println(main.divide(5, 4));
//        main = new Main(); // Cannot assign a value to final variable 'main'
    }
}
