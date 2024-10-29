package org.sistemasoperacionais.questao1;

import java.util.List;
import java.util.Map;

public class LineProcessor extends Thread {
    private List<String> text;
    private Map<String, Integer> wordCountHashMap;
    String lowerCaseWord = "";

    public LineProcessor(List<String> text, Map<String, Integer> wordCountHashMap) {
        this.text = text;
        this.wordCountHashMap = wordCountHashMap;
    }

    public void run() {
        while (true) {
            String line;
            synchronized (text) {
                if (text.isEmpty()) {
                    break;
                }
                line = text.removeFirst();
            }
            if (line != null) {
                String[] wordArray = line.split("[^a-zA-Zá-úÁ-ÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜãÃõÕçÇ\\-]+");
                for (String s : wordArray) {
                    lowerCaseWord = s.toLowerCase();
                    if (!lowerCaseWord.isEmpty()) {
                        synchronized (wordCountHashMap){
                            wordCount(lowerCaseWord);
                        }
                    }
                }
            }
        }
    }

    private synchronized void wordCount(String word) {
        wordCountHashMap.merge(word, 1, Integer::sum);
    }
}