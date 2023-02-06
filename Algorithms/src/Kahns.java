import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static utils.graphutils.Utils.addDirectedEdge;
import static utils.graphutils.Utils.createEmptyAdjacencyList;

public class Kahns {
    public int[] kahns(List<List<Integer>> graph) {
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

        int[] topologicalSort = new int[V];
        int index = 0;

        while (!queue.isEmpty()) {
            Integer node = queue.remove();
            topologicalSort[index++] = node;
            //remove node from graph and decrease degree of all affected nodes
            for (int neighbour : graph.get(node)) {
                indegrees[neighbour]--;
                if(indegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }

        if(index != V) {
            return null;
        }

        return topologicalSort;
    }

    public static void main(String[] args) {
        exampleFromSlides();
        // test1();
        // test2();
        // cycleTest();
    }

    private static void exampleFromSlides() {
        List<List<Integer>> g = createEmptyAdjacencyList(13);
        addDirectedEdge(g, 0, 2);
        addDirectedEdge(g, 0, 3);
        addDirectedEdge(g, 0, 6);
        addDirectedEdge(g, 1, 4);
        addDirectedEdge(g, 2, 6);
        addDirectedEdge(g, 3, 1);
        addDirectedEdge(g, 3, 4);
        addDirectedEdge(g, 4, 5);
        addDirectedEdge(g, 4, 8);
        addDirectedEdge(g, 6, 7);
        addDirectedEdge(g, 6, 11);
        addDirectedEdge(g, 7, 4);
        addDirectedEdge(g, 7, 12);
        addDirectedEdge(g, 9, 2);
        addDirectedEdge(g, 9, 10);
        addDirectedEdge(g, 10, 6);
        addDirectedEdge(g, 11, 12);
        addDirectedEdge(g, 12, 8);

        Kahns solver = new Kahns();
        int[] ordering = solver.kahns(g);

        // Prints: [0, 9, 13, 3, 2, 10, 1, 6, 7, 11, 4, 12, 5, 8]
        System.out.println(java.util.Arrays.toString(ordering));
    }

    private static void test1() {
        List<List<Integer>> g = createEmptyAdjacencyList(6);
        addDirectedEdge(g, 0, 1);
        addDirectedEdge(g, 0, 2);
        addDirectedEdge(g, 1, 2);
        addDirectedEdge(g, 3, 1);
        addDirectedEdge(g, 3, 2);
        addDirectedEdge(g, 2, 4);
        addDirectedEdge(g, 4, 5);
        Kahns solver = new Kahns();
        System.out.println(java.util.Arrays.toString(solver.kahns(g)));
    }

    private static void test2() {
        List<List<Integer>> g = createEmptyAdjacencyList(6);
        addDirectedEdge(g, 0, 1);
        addDirectedEdge(g, 0, 2);
        addDirectedEdge(g, 0, 5);
        addDirectedEdge(g, 1, 2);
        addDirectedEdge(g, 1, 3);
        addDirectedEdge(g, 2, 3);
        addDirectedEdge(g, 2, 4);
        addDirectedEdge(g, 3, 4);
        addDirectedEdge(g, 5, 4);
        Kahns solver = new Kahns();
        System.out.println(java.util.Arrays.toString(solver.kahns(g)));
    }

    private static void cycleTest() {
        List<List<Integer>> g = createEmptyAdjacencyList(4);
        addDirectedEdge(g, 0, 1);
        addDirectedEdge(g, 1, 2);
        addDirectedEdge(g, 2, 3);
        addDirectedEdge(g, 3, 0);
        Kahns solver = new Kahns();
        System.out.println(java.util.Arrays.toString(solver.kahns(g)));
    }
}
