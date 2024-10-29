package org.sistemasoperacionais.questao1;

import java.util.*;

public class Questao1 {
    // Palavras que serão exibidas ao final:
    private static final int N = 3;

    // Quantidade de Threads em execução:
    private static final int T = 4;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<String> text = new ArrayList<>();
        Map<String, Integer> wordCount = new HashMap<>();

        new TextReader().readText(text, "src/main/java/org/sistemasoperacionais/questao1/OJardimSecreto.txt");

        List<Thread> threadList = new ArrayList<>();

        // Cria uma lista de Threads para poder inicializá-las
        for (int i = 0; i < T; i++){
            LineProcessor lineProcessor = new LineProcessor(text, wordCount);
            threadList.add(lineProcessor);
            lineProcessor.start();
        }

        // Interrompe a execução de cada Thread
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // Ordena o HashMap de modo que as palavras mais repetidas ficarão nas primeiras posições
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for (int i = 0; i < N; i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double totalTimeInSeconds = (double) totalTime / 1000000000;
        System.out.println("Tempo de execução em segundos: " + totalTimeInSeconds);
    }
}
