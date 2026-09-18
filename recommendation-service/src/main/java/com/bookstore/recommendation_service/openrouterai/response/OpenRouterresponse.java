package com.bookstore.recommendation_service.openrouterai.response;

import java.util.List;

public class OpenRouterresponse {
	 private List<Choice> choices;

	    public OpenRouterresponse() {
	    }

	    public List<Choice> getChoices() {
	        return choices;
	    }

	    public void setChoices(List<Choice> choices) {
	        this.choices = choices;
	    }

}
