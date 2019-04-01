package com.atb.service;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class FileSrvImpl {
	
	
	public void writeCsvMsg(String msg) throws IOException
	{
		
        String[] csvMsg={msg};
		String archCSV = "/home/alvaro/workspace/SpringJmsJdbcMultiConfig/msgActiveMq.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(archCSV));

		writer.writeNext(csvMsg);

		writer.close();
	}
}
