package com.hieunguyen.lab.graph;

import java.util.*;

import com.hieunguyen.lab.graph.Graph.*;

/**
 * Djikstra algorithm to find a shortest path in a weighted graph.
 * Created by hieunguyen on 4/11/17.
 */
public class ShortestPath {

    private static Map<Vertex, Vertex> getPath(Graph graph, Vertex source) {
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        for (Vertex v : graph.getVertexes()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        Set<Vertex> settledVertexes = new HashSet<>();
        Set<Vertex> unsettledVertexes = new HashSet<>();
        unsettledVertexes.add(source);

        while(!unsettledVertexes.isEmpty()) {
            Vertex vertex = getLowestDistanceVertex(unsettledVertexes, distances);
            unsettledVertexes.remove(vertex);
            settledVertexes.add(vertex);
            evaluateNeighbors(graph, vertex, settledVertexes, unsettledVertexes, distances, predecessors);
        }

        return predecessors;
    }

    private static Vertex getLowestDistanceVertex(Set<Vertex> unsettledVertexes, Map<Vertex, Integer> distances) {
        Vertex result = null;
        for (Vertex v : unsettledVertexes) {
            if (result == null) {
                result = v;
                continue;
            }
            if (distances.get(v) < distances.get(result)) {
                result = v;
            }
        }
        return result;
    }

    private static void evaluateNeighbors(Graph graph, Vertex vertex,
                                          Set<Vertex> settledVertexes,
                                          Set<Vertex> unsettledVertexes,
                                          Map<Vertex, Integer> distances,
                                          Map<Vertex, Vertex> predecessors) {
        Set<Vertex> neighbors = getNeighbors(graph, vertex, settledVertexes);
        for (Vertex v : neighbors) {
            int edgeDistance = getDistance(graph, vertex, v);
            int newDistance = edgeDistance + distances.get(vertex);
            if (newDistance < distances.get(v)) {
                distances.put(v, newDistance);
                unsettledVertexes.add(v);
                predecessors.put(v, vertex);
            }
        }
    }

    private static Set<Vertex> getNeighbors(Graph graph, Vertex vertex, Set<Vertex> settledVertexes) {
        Set<Vertex> neighbors = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            Vertex v = edge.getSource();
            if (v.equals(vertex) && !settledVertexes.contains(edge.getDest())) {
                neighbors.add(edge.getDest());
            }
        }
        return neighbors;
    }

    private static int getDistance(Graph graph, Vertex source, Vertex dest) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().equals(source) && edge.getDest().equals(dest)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Impossible Exception");
    }

    public static LinkedList<Vertex> findShortestPath(Graph graph, Vertex source, Vertex target) {
        LinkedList<Vertex> result = new LinkedList<>();
        Map<Vertex, Vertex> predecessors = getPath(graph, source);
        if (!predecessors.containsKey(target)) {
            return result;
        }
        result.add(target);
        Vertex v = target;
        while(predecessors.containsKey(v)) {
            v = predecessors.get(v);
            result.add(v);
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Vertex source = new Vertex(1, "A");
        Vertex b = new Vertex(2, "B");
        Vertex c = new Vertex(3, "C");
        Vertex d = new Vertex(4, "D");
        Vertex e = new Vertex(5, "E");
        Vertex target = new Vertex(6, "F");

        Set<Vertex> vertexes = new HashSet<>();
        vertexes.add(source);
        vertexes.add(b);
        vertexes.add(c);
        vertexes.add(d);
        vertexes.add(e);
        vertexes.add(target);

        Set<Edge> edges = new HashSet<>();
        edges.add(new Edge(10, source, b));
        edges.add(new Edge(15, source, c));
        edges.add(new Edge(12, b, d));
        edges.add(new Edge(15, b, target));
        edges.add(new Edge(1, d, target));
        edges.add(new Edge(2, d, e));
        edges.add(new Edge(5, target, e));
        edges.add(new Edge(10, c, e));

        Graph graph = new Graph(vertexes, edges);

        LinkedList<Vertex> paths = findShortestPath(graph, source, target);

        for (Vertex v: paths) {
            System.out.println(v.getName());
        }
    }
}
