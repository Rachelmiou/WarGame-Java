package com.company;

// f(y) = 2y^2 + 3y + 2
// Fib(x) = x : x < 2
//          Fib(x-1) + Fib(x-2) : x > 1

import java.util.concurrent.Callable;

public class Main {
    private static double f(double y) {
        return (2 * Math.pow(y, 2)) + (3 * y) + 2;
    }

    private static int fib(int x) {
        if (x < 2) {
            return x;
        }

        return fib(x - 1) + fib(x - 2);
    }

    private static int CountLetters(String input) {
        return CountLetters(input, 5);
    }

    private static int CountLetters(String input, Callable<Integer> length) {
        try {
            return CountLetters(input, length.call());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private static int CountLetters(String input, int limit) {
        String cleanedInput = input.replaceAll("[;,.<>\\[\\]{}()'\"/|\\-_=+]", ""); // \W is all non word characters eg. ;,.<>[]{}()'"/\|-_=+
        String[] words = cleanedInput.split("\s+"); // \s+ any number of spaces " " "  " "   "

        int count = 0;
        for(String word: words) {
            if(word.length() >= limit) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] Args) {
        System.out.println(f(6));
        System.out.println(f(1345));

        System.out.println(fib(5));
        System.out.println(fib(21));

        final int x = 1;

        System.out.println(CountLetters("The Quick Brown Fox Jumps Over the Lazy Dog."));
        System.out.println(CountLetters("The Quick Brown Fox Jumps Over the Lazy Dog.", 4));
    }
}