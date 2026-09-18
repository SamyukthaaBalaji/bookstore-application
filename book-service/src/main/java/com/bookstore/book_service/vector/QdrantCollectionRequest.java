package com.bookstore.book_service.vector;

public class QdrantCollectionRequest {

    private Vectors vectors;

    public QdrantCollectionRequest() {
    }

    public QdrantCollectionRequest(Vectors vectors) {
        this.vectors = vectors;
    }

    public Vectors getVectors() {
        return vectors;
    }

    public void setVectors(Vectors vectors) {
        this.vectors = vectors;
    }

    public static class Vectors {

        private int size;
        private String distance;

        public Vectors() {
        }

        public Vectors(int size, String distance) {
            this.size = size;
            this.distance = distance;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }

}
