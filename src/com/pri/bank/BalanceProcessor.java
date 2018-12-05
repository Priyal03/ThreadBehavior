package com.pri.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BalanceProcessor {
	
	private int balance;
	private Lock accessLock;
	private Condition sufficientFunds;

	// Constructor to setup an Account with Locking System
	public BalanceProcessor() {
		balance = 0;
		accessLock = new ReentrantLock();
		sufficientFunds = accessLock.newCondition();
	}

	// Locking system for Handling a deposit
	public void deposit() throws InterruptedException {

		int creditAmount = RandomNumberGenerator.newNumber(Constants.DEPOSIT);
		accessLock.lock();
		try {
			balance += creditAmount;
			System.out.printf(
					"%s deposits $%-3d\t\t\t\t\t\t\tBalance is $%-3d\n",
					Thread.currentThread().getName(), creditAmount, balance);
			sufficientFunds.signalAll();
		} finally {
			accessLock.unlock();
		}
	}

	// Locking system for Handling a withdrawal
	public void withdrawl() throws InterruptedException {
		
		int debitAmount = RandomNumberGenerator.newNumber(Constants.WITHDRAWAL);
		accessLock.lock();
		try {
			// If account has sufficient funds then perform the transactions.
			if (balance > debitAmount) {
				balance -= debitAmount;
				System.out
						.printf("\t\t\t\t\t%s withdraws $%-3d\t\tBalance is $%-3d\n",
								Thread.currentThread().getName(), debitAmount, balance);
			}

			// If account has insufficient funds, wait until deposit to try again
			else {
				while (balance < debitAmount) {
					System.out
							.printf("\t\t\t\t\t%s withdraws $%-3d\t\tWithdrawl - Blocked - Insufficient Funds\n",
									Thread.currentThread().getName(), debitAmount);
					sufficientFunds.await();
				}
			}

		} catch (InterruptedException e) {
			System.out.println("Exception Handled " + e.getMessage());
			e.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}
}