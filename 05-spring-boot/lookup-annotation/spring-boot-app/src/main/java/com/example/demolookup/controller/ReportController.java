package com.example.demolookup.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demolookup.service.ReportService;

@RestController
public class ReportController {

	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@GetMapping("/report")
	public String generatereport() {
		int id =reportService.generateReport();
		return "Report generated with id: "+id;
	}
}
