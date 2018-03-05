package com.hieunguyen.lab.bfs;

import java.util.*;

/**
 * Consider an undirected graph consisting of N nodes where each node is labeled from 1 to M and the edge between any two nodes is always of length 6.
 * We define node S to be the starting position for a BFS.
 * Given Q queries in the form of a graph and some starting node, perform each query by calculating the shortest distance from starting node to all the other nodes in the graph.
 * Then print a single line of space-separated integers listing node 's shortest distance to each of the  other nodes (ordered sequentially by node number).
 * If X is disconnected from a node, print -1 as the distance to that node.
 *
 * Input Format
 * The first line contains an integer, Q, denoting the number of queries. The subsequent lines describe each query in the following format:
 *     The first line contains two space-separated integers describing the respective values of N (the number of nodes) and M (the number of edges) in the graph.
 *     Each line i of the M subsequent lines contains two space-separated integers, U and V, describing an edge connecting node U to node V.
 *     The last line contains a single integer, denoting the index of the starting node S.
 *
 * Output Format
 * For each of the Q queries, print a single line of space-separated integers denoting the shortest distances to each of the N - 1 other nodes from starting position S.
 * These distances should be listed sequentially by node number (i.e. 1, N), but should not include node S. If some node is unreachable from S, print -1 as the distance to that node.
 *
 * Sample Input
 * 2
 * 4 2
 * 1 2
 * 1 3
 * 1
 * 3 1
 * 2 3
 * 2
 *
 * Sample Output
 * 6 6 -1
 * -1 6
 * Created by hieunguyen on 3/19/17.
 */
public class ShortestPathGraph {

    private static Map<Node, Integer> shortestPath(Node src) {
        Queue<Node> q = new LinkedList<>();
        SortedMap<Node, Integer> visited = new TreeMap<>((x, y) -> x.id  - y.id);
        q.add(src);
        visited.put(src, 0);
        int distance = 0;
        int size = 0;
        int count = 1;
        while (!q.isEmpty()) {
            Node n = q.poll();
            count--;
            if (!n.equals(src) && visited.containsKey(n)) {
                continue;
            }

            //System.out.println("process: " + n.id + "| visited: " + visited.containsKey(n) + "| current distance: " + distance + "| size: " + n.adjacent.size() + "|children: " + Arrays.toString(n.adjacent.toArray(new Node[n.adjacent.size()])));


            for (Node x : n.adjacent) {
                if (!visited.containsKey(x) && !q.contains(x)) {
                    //System.out.println("Add to q: " + x.id);
                    q.add(x);
                    size++;
                }
            }
            visited.put(n, distance);

            if (count == 0) {
                count = size;
                size = 0;
                distance += 6;
            }
        }
        return visited;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        for (int k = 0; k < q; k++) {
            int n = in.nextInt();

            Map<Integer, Node> nodes = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                Node node = new Node(i);
                nodes.put(i, node);
            }

            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();

                nodes.get(u).add(nodes.get(v));
            }
            int s = in.nextInt();
            Node src = nodes.get(s);

            Map<Node, Integer> nodeMap = shortestPath(src);
            for (Node node : nodes.values()) {
                if (!nodeMap.containsKey(node)) {
                    nodeMap.put(node, -1);
                }
            }

            StringBuilder result = new StringBuilder();
            for (Node node : nodeMap.keySet()) {
                if (!src.equals(node)) {
                    result.append(nodeMap.get(node)).append(" ");
                }
            }
            System.out.println(result.toString().trim());
        }
    }
}

class Node {
    int id;
    Set<Node> adjacent = new HashSet<>();

    Node(int id) {
        this.id = id;
    }

    public void add(Node node) {
        adjacent.add(node);
        node.adjacent.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Node: " + id;
    }
}
