import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CycleDetectionDG {
    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int pathVis[]) {
        vis[node] = 1;
        pathVis[node] = 1;

        // traverse for adjacent nodes
        for(int it : adj.get(node)) {
            // when the node is not visited
            if(vis[it] == 0) {
                if(dfsCheck(it, adj, vis, pathVis) == true)
                    return true;
            }
            // if the node has been previously visited
            // but it has to be visited on the same path
            else if(pathVis[it] == 1) {
                return true;
            }
        }

        pathVis[node] = 0;
        return false;
    }

    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V];
        int pathVis[] = new int[V];

        for(int i = 0;i<V;i++) {
            if(vis[i] == 0) {
                if(dfsCheck(i, adj, vis, pathVis) == true) return true;
            }
        }
        return false;
    }

    public boolean hasCycleKahns(ArrayList<ArrayList<Integer>> graph) {
        int V = graph.size();
        int[] indegrees = new int[V];

        //populate in degrees of each vertex
        for (int i=0; i<V; i++) {
            for(int neighbour : graph.get(i)) {
                indegrees[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        //start with nodes with no dependencies (no incoming edges these can be at start of list)
        for (int i=0; i<V; i++) {
            if(indegrees[i] == 0) {
                queue.add(i);
            }
        }

        int index = 0;

        while (!queue.isEmpty()) {
            Integer node = queue.remove();
            index++;
            //remove node from graph and decrease degree of all affected nodes
            for (int neighbour : graph.get(node)) {
                indegrees[neighbour]--;
                if(indegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        if(index != V) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        CycleDetectionDG obj = new CycleDetectionDG();
//        boolean ans = obj.isCyclic(V, adj);
        boolean ans = obj.hasCycleKahns(adj);
        if (ans)
            System.out.println("True");
        else
            System.out.println("False");

    }

}
