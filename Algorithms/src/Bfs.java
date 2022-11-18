import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

    public ArrayList<Integer> bfsTraversal(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            result.add(node);

            // Get all adjacent vertices of the dequeued vertex 'node' from adjacency list
            // If adjacent vertex has not been visited mark it visited and enqueue it
            for(Integer i : adj.get(node)) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i<5; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(0).add(1);
        adjList.get(0).add(4);
        adjList.get(1).add(0);
        adjList.get(1).add(4);
        adjList.get(1).add(2);
        adjList.get(2).add(1);
        adjList.get(2).add(4);
        adjList.get(2).add(3);
        adjList.get(3).add(2);
        adjList.get(3).add(4);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(4).add(2);
        adjList.get(4).add(3);

        Bfs bfs = new Bfs();
        ArrayList<Integer> result = bfs.bfsTraversal(5, adjList);

        for(int i = 0; i<result.size(); i++) {
            System.out.print(result.get(i)+" ");
        }
    }
}
