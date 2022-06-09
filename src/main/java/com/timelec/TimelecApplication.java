package com.timelec;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableScheduling
@SpringBootApplication
public class TimelecApplication {
	
//	@Autowired
//	private EmailSenderService senderService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(TimelecApplication.class, args);    	
	   }
	
//	@Scheduled(cron ="* * * * * *")
//	public void someJob() throws InterruptedException{
//		SimpleDateFormat formater = null;
//		Date aujourdhui = new Date();
//		formater = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(formater.format(aujourdhui));	
//		    
//		ETLResultsVMController test = new ETLResultsVMController();
//		test.ETL(formater.format(aujourdhui));
//		senderService.sendEmail("rahmasakka3@gmail.com", "iData", "this is body of mail"+new Date());
//		Thread.sleep(1000);
//	}
}