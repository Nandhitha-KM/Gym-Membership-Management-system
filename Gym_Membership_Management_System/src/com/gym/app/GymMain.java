package com.gym.app;

import java.util.Date;
import java.util.Scanner;

import com.gym.service.GymService;

public class GymMain {
	 private static GymService gymService;
	 public static void main(String[] args) {
		 gymService = new GymService(); 
		 Scanner sc = new Scanner(System.in);
		 System.out.println("--- Gym Membership Console ---");
		 try {
			 boolean r = gymService.enrollMembership("MB1001","Monthly",new Date(),new Date());
			 System.out.println(r ? "ENROLLED" : "FAILED");
		 }
		 catch(Exception e) {
			 System.out.println(e); 
		 }
		 try {
			 boolean r = gymService.cancelMembership(80001); 
			 System.out.println(r ? "CANCELLED" : "FAILED");
		 }
		 catch(Exception e) {
			 System.out.println(e); 
		 }
		 sc.close();
		 
		 
		 
	 }
	 
}
