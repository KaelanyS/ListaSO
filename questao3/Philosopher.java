package org.sistemasoperacionais.questao3;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{
    private final List<Object> forks;
    private final int index;
    private final int leftFork;
    private final int rightFork;

    public Philosopher(List<Object> forks, int index) {
        this.forks = forks;
        this.index = index;
        this.leftFork = index;
        this.rightFork = (index + 1) % 5;
    }

    public void run(){
        try{
            while (true){
                // Filósofo está pensando
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));

                    synchronized (forks.get(leftFork)) {
                        synchronized (forks.get(rightFork)) {
                            // Filósofo está comendo
                            System.out.println("O filósofo " + index + " está comendo");
                            Thread.sleep(2000);
                        }
                    }

                // Filósofo terminou de comer
                System.out.println("O filósofo " + index + " terminou de comer");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
