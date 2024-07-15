package fr.diginamic.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@EnableScheduling
//@SpringBootApplication
//public class TestEnableScheduling {
//	private int count =1;
//	public static void main(String[] args) {
//		SpringApplication.run(TestEnableScheduling.class,args);
//	}
//	@Scheduled(cron= "* * * * * *")
//		private void tick() {
//			if(count++%2==0) {
//				System.out.println("tack");
//			}else {
//				System.out.println("tick");
//			}
//		
//	}
//
//}
