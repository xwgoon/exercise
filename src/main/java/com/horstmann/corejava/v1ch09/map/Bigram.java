package com.horstmann.corejava.v1ch09.map;

import java.util.HashMap;
import java.util.Map;

public class Bigram {

    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

//    public boolean equals(Bigram b) {
//        return b.first == first && b.second == second;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bigram)) return false;
        Bigram bigram = (Bigram) o;
        return first == bigram.first &&
                second == bigram.second;
    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }

    @Override
    public String toString() {
        return "Bigram{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static void main(String[] args) {
        Map<Bigram, String> map = new HashMap<>();
        for (int i = 0; i < 2; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                map.put(new Bigram(ch, ch), i + "_" + ch);
        System.out.println(map.size());
        System.out.println(map);
    }
}
