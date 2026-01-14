package com.example.demolookup.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ReportContext {
	// static shared counter for all instances -belongs to class not object
	private static int counter = 0;
	private final int id;

	public ReportContext() {
		counter++;
		this.id = counter;
	}
	public void process() {
		System.out.println("Processing ReportContext with ID: " + id);
	}
	public int getId() {
		return id;
	}
}
