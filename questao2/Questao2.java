package org.sistemasoperacionais.questao2;

public class Questao2 {

    public static void main(String[] args) {
        int numEquipes = 2;
        int corredoresPorEquipe = 3;

        Equipe[] equipes = new Equipe[numEquipes];

        // Cria as equipes e corredores
        // Preenchendo de acordo com a quantidade informada
        for (int i = 0; i < numEquipes; i++) {
            equipes[i] = new Equipe(i + 1, corredoresPorEquipe);
        }

        Equipe equipeVencedora = null;
        long menorTempo = Long.MAX_VALUE;

        // Iniciar a corrida para cada equipe
        for (Equipe equipe : equipes) {
            long tempo = equipe.iniciarCorrida();

            // Calcula qual equipe foi a mais rápida
            if (tempo < menorTempo) {
                menorTempo = tempo;
                equipeVencedora = equipe;
            }
        }

        System.out.println("A equipe vencedora é a Equipe " + equipeVencedora.idEquipe);
    }
}