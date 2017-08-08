import java.util.*;

class Edge implements Comparable<Edge>{
	public int from;
	public int to;
	public int weight;

	public Edge(int from, int to, int weight){
		this.from=from;
		this.to=to;
		this.weight=weight;
	}

	@Override		//Sort by largest edge weight 1st
	public int compareTo(Edge edge) {
		return edge.weight - this.weight;
	}

	@Override
	public String toString(){
		return "[" + from + "-->" + to + " (weight="+weight+")]";
	}
}

public class ReverseDeleteMST {
	public static void findMST(ArrayList<Edge> graph){
		System.out.println(graph);
		Collections.sort(graph);
		System.out.println(graph);
	}

	public static void main(String[] args) {
		ArrayList<Edge> graph = new ArrayList<Edge>();
		graph.add(new Edge(0, 1, 10));
		graph.add(new Edge(0, 2, 50));
		graph.add(new Edge(1, 2, 20));

		ReverseDeleteMST.findMST(graph);
	}
}