package com.example.demolookup.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.example.demolookup.model.ReportContext;

@Service
public abstract class ReportService {

	//here ReportService is singletone by default and we need prototype ReportContext
	// so we will use lookup method injection to get new instance of ReportContext

    /**
     * Behind the scenes Spring generates:
     *
     * protected ReportContext createReportContext() {
     *     return applicationContext.getBean(ReportContext.class);
     * }
     */
	@Lookup
	protected abstract ReportContext createReportContext();
	
	public  int generateReport() {
		ReportContext context =createReportContext();
		context.process();
		return context.getId();
		
	}

}
