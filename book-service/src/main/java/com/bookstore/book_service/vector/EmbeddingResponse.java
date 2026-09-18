package com.bookstore.book_service.vector;

import java.util.List;

public class EmbeddingResponse {
    private String model;
	   private List<List<Double>> embeddings;

	   public EmbeddingResponse() {
	    }

	    public String getModel() {
	        return model;
	    }

	    public void setModel(String model) {
	        this.model = model;
	    }

	    public List<List<Double>> getEmbeddings() {
	        return embeddings;
	    }

	    public void setEmbeddings(List<List<Double>> embeddings) {
	        this.embeddings = embeddings;
	    }
	

}
