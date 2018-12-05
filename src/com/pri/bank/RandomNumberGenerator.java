package com.pri.bank;

import java.util.Random;

public class RandomNumberGenerator {
    	
    // Takes String operator and outputs random number
    public static int newNumber (String operator){
            
        int randomAmount = 0;

        // If user is depositing money 
        if (operator == Constants.DEPOSIT ){ 
        	randomAmount = integerRange(Constants.MIN_DEPOSIT, Constants.MAX_DEPOSIT); 
            return randomAmount;

        // If user is withdrawing money
        } else {
        	randomAmount = integerRange(Constants.MIN_WITHDRAWAL, Constants.MAX_WITHDRAWAL);
            return randomAmount;
        }
    }
    
    // Makes sure RandomNumber is within given range.
    private static int integerRange(int min, int max) {
    
        Random randomGenerator = new Random();
        int number = 0;

        // Generating a Random Number within range
        while (min >= number || max <= number) {
        	number = randomGenerator.nextInt();
        }

        return number;
    }
    
}
