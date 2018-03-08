package com.hieunguyen.lab.graph;

import java.util.Set;

/**
 * Created by hieunguyen on 4/11/17.
 */
public class Graph {
    static class Vertex implements Comparable<Vertex> {
        private int id;
        private String name;
        private int tempWeight;

        @Override
        public int compareTo(Vertex v) {
            return tempWeight - v.getTempWeight();
        }

        public Vertex(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTempWeight() {
            return tempWeight;
        }

        public void setTempWeight(int tempWeight) {
            this.tempWeight = tempWeight;
        }
    }

    static class Edge {
        private int weight;
        private Vertex source;
        private Vertex dest;

        public Edge(int weight, Vertex source, Vertex dest) {
            this.weight = weight;
            this.source = source;
            this.dest = dest;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Vertex getSource() {
            return source;
        }

        public void setSource(Vertex source) {
            this.source = source;
        }

        public Vertex getDest() {
            return dest;
        }

        public void setDest(Vertex dest) {
            this.dest = dest;
        }
    }

    private Set<Vertex> vertexes;
    private Set<Edge> edges;

    public Graph(Set<Vertex> vertexes, Set<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public Set<Vertex> getVertexes() {
        return vertexes;
    }

    public void setVertexes(Set<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}
