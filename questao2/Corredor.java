package org.sistemasoperacionais.questao2;

public class Corredor extends Thread {
  // Identificador do corredor
  private final int idCorredor;
  // Referência à equipe do corredor
  private final Equipe equipe;

  // Ponto de sincronização B
  private final Object pontoB;
  // Ponto de sincronização C
  private final Object pontoC;

  // Tempo máximo de corrida entre os pontos
  private static final int TEMPO_CORRIDA = 1000;

  public Corredor(int idCorredor, Equipe equipe, Object pontoB, Object pontoC) {
    this.idCorredor = idCorredor;
    this.equipe = equipe;
    this.pontoB = pontoB;
    this.pontoC = pontoC;
  }

  @Override
  public void run() {
    try {
      System.out.println("Equipe " + equipe.idEquipe + " Corredor " + idCorredor + " começou no Ponto A");

      // Corrida do Ponto A ao Ponto B
      Thread.sleep((long) (Math.random() * TEMPO_CORRIDA));
      System.out.println("Equipe " + equipe.idEquipe + " Corredor " + idCorredor + " chegou ao Ponto B");
      equipe.esperarNoPontoB();

      // Corrida do Ponto B ao Ponto C
      Thread.sleep((long) (Math.random() * TEMPO_CORRIDA));
      System.out.println("Equipe " + equipe.idEquipe + " Corredor " + idCorredor + " chegou ao Ponto C");
      equipe.esperarNoPontoC();

      // Corrida do Ponto C ao Ponto D
      Thread.sleep((long) (Math.random() * TEMPO_CORRIDA));
      System.out.println("Equipe " + equipe.idEquipe + " Corredor " + idCorredor + " chegou ao Ponto D");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}