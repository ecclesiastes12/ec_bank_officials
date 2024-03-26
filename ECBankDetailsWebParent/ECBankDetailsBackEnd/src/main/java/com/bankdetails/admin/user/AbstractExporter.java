package com.bankdetails.admin.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import jakarta.servlet.http.HttpServletResponse;

public class AbstractExporter {

	
	//method for writing csv file
	public void setResponseHeader(HttpServletResponse response, String contentType, String extension) throws IOException {

		  //creates date format object 
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		  
		  //creates time stamp that format the current date and time 
		String timestamp = dateFormatter.format(new Date());
		  
		  
		  // users_ can be any name of your choice //creates file name. 
		//String fileName = "users_" + timestamp + ".xlsx";
		 String fileName = "users_" + timestamp + extension; 
		    
		  //header for the file to be downloaded. 
		String headerKey = "Content-Disposition"; 
		String headerValue = "attachment; filename=" + fileName; 
		
		//set response header which contain the headerkey and headerValue
		response.setHeader(headerKey, headerValue);
		  
		//set the content type eg. csv, xlsx
		response.setContentType(contentType);
		
	}
}
