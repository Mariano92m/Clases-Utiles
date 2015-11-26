package Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

  private final List<Nodo> nodos;
  private final List<Arista> aristas;
  private Set<Nodo> settledNodes;
  private Set<Nodo> unSettledNodes;
  private Map<Nodo, Nodo> predecessors;
  private Map<Nodo, Integer> distance;

  public DijkstraAlgorithm(Grafo grafo) {
    // create a copy of the array so that we can operate on this array
    this.nodos = new ArrayList<Nodo>(grafo.getNodos());
    this.aristas = new ArrayList<Arista>(grafo.getAristas());
  }

  public void execute(Nodo source) {
    settledNodes = new HashSet<Nodo>();
    unSettledNodes = new HashSet<Nodo>();
    distance = new HashMap<Nodo, Integer>();
    predecessors = new HashMap<Nodo, Nodo>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
    	Nodo nodo = getMinimum(unSettledNodes);
      settledNodes.add(nodo);
      unSettledNodes.remove(nodo);
      findMinimalDistances(nodo);
    }
  }

  private void findMinimalDistances(Nodo nodo) {
    List<Nodo> adjacentNodes = getNeighbors(nodo);
    for (Nodo target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(nodo)
          + getDistance(nodo, target)) {
        distance.put(target, getShortestDistance(nodo)
            + getDistance(nodo, target));
        predecessors.put(target, nodo);
        unSettledNodes.add(target);
      }
    }

  }

  private int getDistance(Nodo nodo, Nodo target) {
    for (Arista arista : aristas) {
      if (arista.getSource().equals(nodo)
          && arista.getDestination().equals(target)) {
        return arista.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  private List<Nodo> getNeighbors(Nodo nodo) {
    List<Nodo> neighbors = new ArrayList<Nodo>();
    for (Arista arista : aristas) {
      if (arista.getSource().equals(nodo)
          && !isSettled(arista.getDestination())) {
        neighbors.add(arista.getDestination());
      }
    }
    return neighbors;
  }

  private Nodo getMinimum(Set<Nodo> nodos) {
	  Nodo minimum = null;
    for (Nodo nodo : nodos) {
      if (minimum == null) {
        minimum = nodo;
      } else {
        if (getShortestDistance(nodo) < getShortestDistance(minimum)) {
          minimum = nodo;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Nodo nodo) {
    return settledNodes.contains(nodo);
  }

  private int getShortestDistance(Nodo destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /*
   * This method returns the path from the source to the selected target and
   * NULL if no path exists
   */
  public LinkedList<Nodo> getPath(Nodo target) {
    LinkedList<Nodo> path = new LinkedList<Nodo>();
    Nodo step = target;
    // check if a path exists
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    // Put it into the correct order
    Collections.reverse(path);
    return path;
  }

} 

