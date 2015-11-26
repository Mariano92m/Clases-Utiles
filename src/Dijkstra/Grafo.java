package Dijkstra;

import java.util.List;

public class Grafo {
  private final List<Nodo> nodos;
  private final List<Arista> aristas;

  public Grafo(List<Nodo> nodos, List<Arista> aristas) {
    this.nodos = nodos;
    this.aristas = aristas;
  }

  public List<Nodo> getNodos() {
    return nodos;
  }

  public List<Arista> getAristas() {
    return aristas;
  }
    
} 
