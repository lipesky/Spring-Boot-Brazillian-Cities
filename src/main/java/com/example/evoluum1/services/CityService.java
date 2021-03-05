package com.example.evoluum1.services;

import java.util.Optional;

import com.example.evoluum1.models.Report;
import com.example.evoluum1.models.ReportRecord;

public interface CityService {
	
	public Optional<ReportRecord> getCityIdByName(String name);
	
	public Report getReport();
}
