package com.timelec;


import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.timelec.timelec.vm.controller.ETLResultsVMController;


@EnableScheduling
@SpringBootApplication
public class TimelecApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(TimelecApplication.class, args);    	
	   }
	
//	@Scheduled(cron ="* * * * * *")
	public void someJob() throws InterruptedException{
		SimpleDateFormat formater = null;
		Date aujourdhui = new Date();
		//System.out.println(aujourdhui);
		formater = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(formater.format(aujourdhui));	
		    
		ETLResultsVMController test = new ETLResultsVMController();
		test.ETL(formater.format(aujourdhui));
	}
}