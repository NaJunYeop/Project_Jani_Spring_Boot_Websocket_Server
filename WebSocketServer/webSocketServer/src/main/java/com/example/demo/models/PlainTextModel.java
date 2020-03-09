package com.example.demo.models;

public class PlainTextModel {
	private String text;

	public PlainTextModel() {
		
	}
	
    public PlainTextModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
