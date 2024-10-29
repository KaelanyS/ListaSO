package org.sistemasoperacionais.questao2;

public class Equipe {

  final int idEquipe;

  private final int numCorredores;

  // Array para armazenar os corredores
  private final Corredor[] corredores;

  // Ponto de sincronização B
  private final Object pontoB = new Object();
  // Ponto de sincronização C
  private final Object pontoC = new Object();

  // Contador de corredores esperando no ponto B
  private int esperandoB = 0;
  // Contador de corredores esperando no ponto C
  private int esperandoC = 0;

  // Inicializa a equipe e os corredores dessa equipe
  public Equipe(int idEquipe, int numCorredores) {
    this.idEquipe = idEquipe;
    this.numCorredores = numCorredores;

    // Cria um array para armazenar os corredores
    this.corredores = new Corredor[numCorredores];

    // Cria e insere os corredores no array
    for (int i = 0; i < numCorredores; i++) {
      corredores[i] = new Corredor(i + 1, this, pontoB, pontoC);
    }
  }

  public long iniciarCorrida() {
    // Tempo de início da corrida
    long inicio = System.currentTimeMillis();

    for (Corredor corredor : corredores) {
      // Iniciar a thread do corredor
      corredor.start();
    }

    // Esperar todos os corredores terminarem
    for (Corredor corredor : corredores) {
      try {
        // Esperar a thread do corredor terminar
        corredor.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // Tempo de fim da corrida
    long fim = System.currentTimeMillis();
    // Retorna o tempo total da corrida para a equipe
    return fim - inicio;
  }

  public void esperarNoPontoB() throws InterruptedException {
    synchronized (pontoB) {
      esperandoB++;
      if (esperandoB < numCorredores) {
        // Espera até que todos os corredores cheguem ao ponto B
        pontoB.wait();
      } else {
        // Notifica todos os corredores que chegaram ao ponto B
        pontoB.notifyAll();
      }
    }
  }

  public void esperarNoPontoC() throws InterruptedException {
    synchronized (pontoC) {
      esperandoC++;
      if (esperandoC < numCorredores) {
        // Espera até que todos os corredores cheguem ao ponto C
        pontoC.wait();
      } else {
        // Notifica todos os corredores que chegaram ao ponto C
        pontoC.notifyAll();
      }
    }
  }
}