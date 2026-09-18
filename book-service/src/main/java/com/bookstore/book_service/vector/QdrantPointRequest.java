package com.bookstore.book_service.vector;

import java.util.List;

public class QdrantPointRequest {
	  private List<QdrantPoint> points;

	    public QdrantPointRequest() {
	    }

	    public QdrantPointRequest(List<QdrantPoint> points) {
	        this.points = points;
	    }

	    public List<QdrantPoint> getPoints() {
	        return points;
	    }

	    public void setPoints(List<QdrantPoint> points) {
	        this.points = points;
	    }

}
