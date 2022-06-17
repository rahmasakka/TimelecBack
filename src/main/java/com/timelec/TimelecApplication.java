package com.timelec;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.codec.language.bm.Languages.SomeLanguages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.timelec.timelec.vm.controller.ETLResultsVMController;


@EnableScheduling
@SpringBootApplication
public class TimelecApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(TimelecApplication.class, args); 
//		LocalTime clock = LocalTime.now();
		LocalDate dateTa = LocalDate.now();
		System.out.print(dateTa);
	   }
	
	@Scheduled(cron ="* 14 17 * * *")
	public static void someJob() throws InterruptedException{
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		Clock clck = Clock.systemUTC();
		System.out.println(clck);
		System.out.print(simpleDateFormat);
		
		//test.ETL(jour);
		//test.ETL("Hello");
		
	//	test.ETLTest();
		//System.out.println("Hello Cron am Here");
	}
}