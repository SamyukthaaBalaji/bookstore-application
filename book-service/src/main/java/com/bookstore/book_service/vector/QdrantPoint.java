package com.bookstore.book_service.vector;

import java.util.List;
import java.util.Map;

public class QdrantPoint {
	  private Long id;
	    private List<Double> vector;
	    private QdrantPayload payload;
	    public QdrantPoint() {
	    }

	    public QdrantPoint(Long id, List<Double> vector, QdrantPayload payload) {
	        this.id = id;
	        this.vector = vector;
	        this.payload = payload;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public List<Double> getVector() {
	        return vector;
	    }

	    public void setVector(List<Double> vector) {
	        this.vector = vector;
	    }

	   public QdrantPayload getPayload() {
		    return payload;
	   }

	   public void setPayload(QdrantPayload payload) {
	       this.payload = payload;
	   }

}
