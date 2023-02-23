import java.util.*;

public class DijkstrasAlgorithm {
    class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public class DistanceComparator implements Comparator<Pair>
    {
        public int compare(Pair x, Pair y)
        {
            if(x.distance == y.distance) {
                return x.node - y.node;
            }

            return x.distance - y.distance;
        }
    }

    private int[] dijkstra(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new DistanceComparator());
        pq.add(new Pair(S, 0));

        int[] distances = new int[V];
        for(int i=0; i<V; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[S] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.peek();
            int node = p.node;
            int distance = p.distance;
            pq.poll();
            // neat optimization we can do which ignores stale pairs in our PQ is to skip nodes where we already
            // found a better path routing through other nodes
            if(distances[node] < distance) { 
                continue;
            }

            ArrayList<ArrayList<Integer>> neighbours = adj.get(node);

            for(int i=0; i < neighbours.size(); i++) {
                ArrayList<Integer> neighbour = neighbours.get(i);
                int adjNode = neighbour.get(0);
                int edgeWeight = neighbour.get(1);
                if(distance + edgeWeight < distances[adjNode]) {
                    distances[adjNode] = distance + edgeWeight;
                    pq.add(new Pair(adjNode, distances[adjNode]));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int V = 3, S=2;
        ArrayList<Integer> node1 = new ArrayList<Integer>() {{add(1);add(1);}};
        ArrayList<Integer> node2 = new ArrayList<Integer>() {{add(2);add(6);}};
        ArrayList<Integer> node3 = new ArrayList<Integer>() {{add(2);add(3);}};
        ArrayList<Integer> node4 = new ArrayList<Integer>() {{add(0);add(1);}};
        ArrayList<Integer> node5 = new ArrayList<Integer>() {{add(1);add(3);}};
        ArrayList<Integer> node6 = new ArrayList<Integer>() {{add(0);add(6);}};

        ArrayList<ArrayList<Integer>> inter1 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node1);
                add(node2);
            }
        };
        ArrayList<ArrayList<Integer>> inter2 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node3);
                add(node4);
            }
        };
        ArrayList<ArrayList<Integer>> inter3 = new ArrayList<ArrayList<Integer>>(){
            {
                add(node5);
                add(node6);
            }
        };
        ArrayList<ArrayList<ArrayList<Integer>>> adj= new ArrayList<ArrayList<ArrayList<Integer>>>(){
            {
                add(inter1); // for 1st node
                add(inter2); // for 2nd node
                add(inter3); // for 3rd node
            }
        };
        //add final values of adj here.
        DijkstrasAlgorithm obj = new DijkstrasAlgorithm();
        int[] res= obj.dijkstra(V,adj,S);

        for(int i=0;i<V;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();

    }
}
