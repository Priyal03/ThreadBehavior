package com.pri.bank;

// Running loop for Withdrawal Thread
public class WithdrawalWorker implements Runnable {
    private BalanceProcessor account;

    // Constructor to link thread to main shared account
    public WithdrawalWorker (BalanceProcessor mainAccount) {
        account = mainAccount;
    }

    public void run() {
        try {
            // Infinite Loop
            while (true) {
                account.withdrawl();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
        	System.out.println("Exception Handled : "+ e.getMessage());
            e.printStackTrace();
        }
    }
}