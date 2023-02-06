import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {

    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, Stack<Integer> stack, boolean[] visited) {
        visited[node] = true;

        for (Integer neighbour : adjList.get(node)) {
            if(!visited[neighbour]) {
                dfs(neighbour, adjList, stack, visited);
            }
        }

        stack.push(node);
    }

    private int[] topologicalSort(ArrayList<ArrayList<Integer>> adjList, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];

        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                dfs(i, adjList, stack, visited);
            }
        }

        int[] result = new int[V];
        for(int i=0; i<V; i++) {
            result[i] = stack.pop();
        }

        return result;
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i=0; i<6; i++) {
            adjList.add(new ArrayList<>());
        }

        adjList.get(2).add(3);
        adjList.get(3).add(1);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(5).add(0);
        adjList.get(5).add(2);

       TopologicalSort topologicalSort = new TopologicalSort();
       int[] result = topologicalSort.topologicalSort(adjList, 6);

        for(int i = 0; i<result.length; i++) {
            System.out.print(result[i]+" ");
        }
    }
}
