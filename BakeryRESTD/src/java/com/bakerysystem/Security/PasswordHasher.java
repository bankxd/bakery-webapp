package com.bakerysystem.Security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
	
	public static String hashPassword(String input) 
	{ 
		byte [] hash = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 
			hash =  md.digest(input.getBytes(StandardCharsets.UTF_8)); 
			
		}catch(NoSuchAlgorithmException e){}
		
		BigInteger number = new BigInteger(1, hash); 
		StringBuilder hexString = new StringBuilder(number.toString(16)); 
		while (hexString.length() < 32) 
		{ 
			hexString.insert(0, '0'); 
		}
		
		return hexString.toString(); 
	} 
        
        public static void main(String [] agr){
            System.out.println(PasswordHasher.hashPassword("T2password"));;
        }
	
}
