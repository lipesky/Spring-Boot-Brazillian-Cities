package com.example.evoluum1.models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class Report {
	private List<ReportRecord> data;

	/**
	 * @param data
	 */
	public Report(List<ReportRecord> data) {
		super();
		this.data = data;
	}

	/**
	 * @return the data
	 */
	public List<ReportRecord> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<ReportRecord> data) {
		this.data = data;
	}
	
	public OutputStream getCSVStream() throws IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if(this.data.size() > 0) {
			baos.write(this.data.get(0).getCSVSchema().getBytes());
		}
		for(ReportRecord record: this.data) {
			baos.write(record.toCSV().getBytes());
		}
		
		return baos; 
	}
}
