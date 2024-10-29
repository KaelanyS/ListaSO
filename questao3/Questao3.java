package org.sistemasoperacionais.questao3;

import java.util.ArrayList;
import java.util.List;

public class Questao3 {
    public static void main(String[] args) {
        List<Object> forks = new ArrayList<>();
        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Object fork = new Object();
            forks.add(fork);
        }

        for (int i = 0; i < 5; i++){
            Philosopher philosopher = new Philosopher(forks, i);
            philosophers.add(philosopher);
            philosopher.start();
        }
    }
}