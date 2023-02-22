import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class DijkstrasAlgorithm {
    class Pair {
        int distance;
        int node;

        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
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
        pq.add(new Pair(0, S));

        int[] distances = new int[V];
        for(int i=0; i<V; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[S] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.peek();
            int distance = p.distance;
            int node = p.node;
            pq.poll();

            ArrayList<ArrayList<Integer>> neighbours = adj.get(node);

            for(int i=0; i < neighbours.size(); i++) {
                ArrayList<Integer> neighbour = neighbours.get(i);
                int adjNode = neighbour.get(0);
                int edgeWeight = neighbour.get(1);
                if(distance + edgeWeight < distances[adjNode]) {
                    distances[adjNode] = distance + edgeWeight;
                    pq.add(new Pair(distances[adjNode], adjNode));
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
