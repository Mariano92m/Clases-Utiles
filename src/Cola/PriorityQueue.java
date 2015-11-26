package Cola;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class PriorityQueue<Item> implements Iterable<Item> {

	//Variable
	private int N;
	private int[] priority;
	private int[] items;
	
	private Node first;
	private Node last;
	
	
	private class Node{
		private Object item;
		private Node next;
	}
	
	//Constructor de la cola de prioridad
	public PriorityQueue(){
		first=null;
		last=null;
		priority=new int[10];
		items = new int[10];
	}
	//Tamaño
	public int size(){
		return N;
	}
	
	//Largo
	public int length(){
		return N;
	}
	
	//Pregunta si esta vacia
	public Object peek(){
		if(isEmpty()) throw new RuntimeException("Queue underflow");
		return first.item;
	}
	
	//Esta vacia boolean
	public boolean isEmpty(){
		return (first==null);
	}
	
	//Sacar de la cola
	public Object dequeue(){
		int i=0;
		while(i<N){
			Node x=new Node();
			x.item=items[i];
			if(isEmpty()){
				first=x;
				last=x;
			}
			else{
				last.next=x;
				last=x;
			}
			i++;
		}
		System.out.println(toString());
		if(isEmpty()) throw new RuntimeException("Queue underflow");
		Object item=first.item;
		first=first.next;
		N--;
		if(isEmpty()) last=null;
		return item;
	}
	
	//Encolar con prioridad
	public void enqueueWithPriority(int value,int priority){
		
			this.priority[N]=priority;
			this.items[N]=value;
		
		if(N!=0){
			int i=N;
			while(i>0){
				if(this.priority[i-1]<this.priority[i]){
					int temp=items[i-1];
					items[i-1]=items[i];
					items[i]=temp;
					
					temp=this.priority[i-1];
					this.priority[i-1]=this.priority[i];
					this.priority[i]=temp;
				}
				i--;
			}
		}
		N++;
	}
	
	//Muestra los items del arreglo
	public void a(){
		System.out.println(Arrays.toString(items));
	}
	
	//Encolar
	public void enqueue(Item item){
		Node x=new Node();
		x.item=item;
		if(isEmpty()){
			first=x;
			last=x;
		}
		else{
			last.next=x;
			last=x;
		}
		N++;
	}
	
	//String to string
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(Item item:this){
			sb.append(item+" ");
		}
		return sb.toString();
	}
	
	//Iterator
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	//LIsta de iteradores
	 private class ListIterator implements Iterator<Item> {
	    private Node current = first;

	    public boolean hasNext(){
	    	return current != null; 
	    }
	    public void remove(){
	    	throw new UnsupportedOperationException();
	    }

	    @SuppressWarnings("unchecked")
		public Item next() {
	       if (!hasNext()) throw new NoSuchElementException();
	       Object item = current.item;
	       current = current.next; 
	       return (Item) item;
	    }
	 }
}

