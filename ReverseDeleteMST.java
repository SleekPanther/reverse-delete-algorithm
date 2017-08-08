import java.util.*;

class Edge implements Comparable<Edge>{
	public int vertex1;
	public int vertex2;
	public int weight;

	public Edge(int vertex1, int vertex2, int weight){
		this.vertex1=vertex1;
		this.vertex2=vertex2;
		this.weight=weight;
	}

	@Override		//Sort by largest edge weight 1st
	public int compareTo(Edge edge) {
		return edge.weight - this.weight;
	}

	@Override
	public String toString(){
		return "[" + vertex1 + "-->" + vertex2 + " (Weight="+weight+")]";
	}
}

public class ReverseDeleteMST {
	public static void findMST(ArrayList<Edge> graph, int vertexCount){
		System.out.println(graph);
		Collections.sort(graph);		//sort by largest weight edges 1st
		System.out.println(graph);

		int mstWeight = 0;
		ArrayList<Edge> mst = new ArrayList<Edge>();

		ArrayList<ArrayList<Integer>> adjacencyGraph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<vertexCount; i++){		//Add placeholders for each vertex
			adjacencyGraph.add(new ArrayList<Integer>());
		}
		for(Edge edge : graph){
			adjacencyGraph.get(edge.vertex1).add(edge.vertex2);
			adjacencyGraph.get(edge.vertex2).add(edge.vertex1);
		}

		for(Edge edge : graph){
		int v1=edge.vertex1;
		int v2 = edge.vertex2;
			adjacencyGraph.get(edge.vertex1).removeIf(v -> v==edge.vertex2);
			adjacencyGraph.get(edge.vertex2).removeIf(v -> v==edge.vertex1);
			
			int x=9;

			if(!ReverseDeleteMST.isConnected(adjacencyGraph)){
				adjacencyGraph.get(edge.vertex1).add(edge.vertex2);
				adjacencyGraph.get(edge.vertex2).add(edge.vertex1);
				mst.add(edge);
				mstWeight += edge.weight;
			}
		}

		System.out.println("Minimum Spanning Tree (weight)=" +mstWeight);
		for(Edge edge: mst){
			System.out.println(edge);
		}
	}

	//Breadth First Search
	private static boolean isConnected(ArrayList<ArrayList<Integer>> graph){
		int startingVertex = 0;
		Queue<Integer> vertexQueue = new LinkedList<Integer>();		//queue where new un-visited vertices are stored
		vertexQueue.add(startingVertex);
		
		boolean[] visitedNodes = new boolean[graph.size()];
		ArrayList<Integer> nodesInVisitedOrder = new ArrayList<Integer>();	//Keep track of visitation order
		
		while(!vertexQueue.isEmpty()){
			int nodeRemovedFromQueue = vertexQueue.remove();		//pick a node from the head of the queue
			
			if(!visitedNodes[nodeRemovedFromQueue]){		//If NOT visited
				visitedNodes[nodeRemovedFromQueue]=true;	//set to visited
				nodesInVisitedOrder.add(nodeRemovedFromQueue);	//append to list of vertices in order they are visited
				
				ArrayList<Integer> incidentVertices = graph.get(nodeRemovedFromQueue);	//get a list of all vertices adjacent to the current Node
				for(int v: incidentVertices){			//Iterate over all vertices adjacent to the current node
					if(!visitedNodes[v]){		//if it's NOT visited, add to queue
						vertexQueue.add(v);
					}
				}
			}
		}
		// System.out.println("Breadth first search visitation order:   "+nodesInVisitedOrder);

		//Check to see if any vertex was NOT visited
		for(int i=0; i<visitedNodes.length; i++){
			if(!visitedNodes[i]){
				return false;	//break early if any vertex is NOT visited
			}
		}
		return true;
	}


	public static void main(String[] args) {
		int vertexCount = 8;
		ArrayList<Edge> graph = new ArrayList<Edge>();
		graph.add(new Edge(0, 1, 9));
		graph.add(new Edge(0, 5, 14));
		graph.add(new Edge(0, 6, 15));
		graph.add(new Edge(1, 2, 24));
		graph.add(new Edge(2, 7, 19));
		graph.add(new Edge(2, 3, 6));
		graph.add(new Edge(2, 4, 2));
		graph.add(new Edge(2, 5, 18));
		graph.add(new Edge(3, 4, 11));
		graph.add(new Edge(3, 7, 7));
		graph.add(new Edge(4, 7, 16));
		graph.add(new Edge(4, 5, 30));
		graph.add(new Edge(4, 6, 20));
		graph.add(new Edge(5, 6, 5));
		graph.add(new Edge(6, 7, 44));

		ReverseDeleteMST.findMST(graph, vertexCount);
	}
}