import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {

    class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    private boolean isCycle(int source, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(source, -1));
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.peek().node;
            int parent = queue.peek().parent;

            queue.poll();

            for(Integer i : adjList.get(node)) {
                if(!visited[i]) {
                    queue.add(new Pair(i, node));
                    visited[i] = true;
                }
                //if some other node is marked as true and it is not parent node then there is a cycle
                else if(i != parent) {
                    return true;
                }
            }
        }

        return false;
    } 

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[V];

        for (int i=0; i<V; i++) {
            if(!visited[i]) {
                if(isCycle(i, adjList, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        int V = 8;
        ArrayList<ArrayList<Integer>>adj = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<Integer>());
        }

        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(2).add(3);
        adj.get(1).add(0);
        adj.get(2).add(4);

        adj.get(5).add(6);
        adj.get(5).add(7);
        adj.get(6).add(5);
        adj.get(6).add(7);
        adj.get(7).add(5);
        adj.get(7).add(6);

        CycleDetection obj = new CycleDetection();

        boolean result = obj.isCycle(V, adj);
        if(result)
            System.out.println("Yes, a cycle exists");
        else
            System.out.println("No cycle exits");

    }
}
