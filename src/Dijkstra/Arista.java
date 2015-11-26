package Dijkstra;

public class Arista {
	private final String id;
	private final Nodo source;
	private final Nodo destination;
	private final int weight;

	public Arista(String id, Nodo source, Nodo destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public Nodo getDestination() {
		return destination;
	}

	public Nodo getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

} 