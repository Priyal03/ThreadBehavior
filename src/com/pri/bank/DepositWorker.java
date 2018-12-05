package com.pri.bank;


// Running loop for Deposit Thread
public class DepositWorker implements Runnable {
	
    private BalanceProcessor account;

    // Constructor to link thread to main shared account
    public DepositWorker (BalanceProcessor mainAccount) {
        account = mainAccount;
    }

    public void run() {
        try {
            // Infinite Loop
            while (true) {
                account.deposit();
                Thread.sleep(7500);
            }
        } catch (InterruptedException e) {
        	System.out.println("Exception Handled : "+ e.getMessage());
            e.printStackTrace();
        }
    }
}