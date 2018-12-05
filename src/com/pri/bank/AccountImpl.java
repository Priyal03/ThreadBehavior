package com.pri.bank;


public class AccountImpl {

	static Thread deposit1,deposit2,deposit3,deposit4,withdrawal1,withdrawal2,withdrawal3,withdrawal4,withdrawal5,withdrawal6;
	
	public static void main(String[] args) {

		// Create a shared Bank Account
		BalanceProcessor account = new BalanceProcessor();
		
		// Create Threads for each operation of the transaction
		DepositWorker depositWorker = new DepositWorker(account);
		WithdrawalWorker withdrawlWorker = new WithdrawalWorker(account);

		// Prepare base of the output structure
		System.out.printf("Deposit Threads\t\t\t\tWithdrawl Threads\t\t\tBalance\t\t\t\n");
		System.out.printf("---------------\t\t\t\t-----------------\t\t\t---------------\t\t\t\n");
		
		// Method to initialize workers for operations
		initializeWorkerThreads(depositWorker, withdrawlWorker);
		
		//Starting operations by invoking threads
		deposit1.start();
        withdrawal1.start();
        deposit2.start();
        withdrawal2.start();
        withdrawal3.start();
        withdrawal4.start();
        withdrawal5.start();
        deposit3.start();
        deposit4.start();
        withdrawal6.start();
        
	}

	private static void initializeWorkerThreads(DepositWorker d, WithdrawalWorker w) {
		
		//Initialize deposit threads 
		deposit1 = new Thread(d, "Depositer1");
		deposit2 = new Thread(d, "Depositer2");
		deposit3 = new Thread(d, "Depositer3");
		deposit4 = new Thread(d, "Depositer4");

		// Initialize withdrawal threads
		withdrawal1 = new Thread(w, "Withdrawer1");
		withdrawal2 = new Thread(w, "Withdrawer2");
		withdrawal3 = new Thread(w, "Withdrawer3");
		withdrawal4 = new Thread(w, "Withdrawer4");
		withdrawal5 = new Thread(w, "Withdrawer5");
		withdrawal6 = new Thread(w, "Withdrawer6");
		
	}
}