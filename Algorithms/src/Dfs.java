import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dfs {

    private static void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> result) {
        //marking node as visited
        visited[node] = true;
        result.add(node);

        //get adjacent nodes from adjacency list and do a recursive call on each of neighbouring node if node is not visited
        for(Integer i : adj.get(node)) {
            if(!visited[i]) {
                dfs(i, visited, adj, result);
            }
        }
    }

    public ArrayList<Integer> dfsTraversal(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        visited[0] = true;
        dfs(0, visited, adj, result);
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

        Dfs dfs = new Dfs();
        ArrayList<Integer> result = dfs.dfsTraversal(5, adjList);

        for(int i = 0; i<result.size(); i++) {
            System.out.print(result.get(i)+" ");
        }
    }
}
