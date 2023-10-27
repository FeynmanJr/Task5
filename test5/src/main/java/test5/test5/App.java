package test5.test5;

import java.util.LinkedHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Account;
import operations.CommandExecutor;

public class App {
	public static void main(String[] args) throws Exception {
		int threadCount = 0;
		int commitCount = 0;
		if (args.length == 2) {
			try {
				threadCount = Integer.parseInt(args[0]);
			} catch (java.lang.NumberFormatException e) {
				System.err.println("Argüman sayı olmalı...");
				e.printStackTrace();
			}
			try {
				commitCount = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				System.err.println("Argüman sayı olmalı...");
				e.printStackTrace();
			}
			Object[] threads = new Object[threadCount];
			for (int i = 0; i<threadCount; i++) {
				 threads[i] = new ThreadObject(commitCount);
				 ((Thread) threads[i]).start();
			}
			
		}
		System.err.println("argument count is not 2");
	}
}

class ThreadObject extends Thread{
	
	int commitCount;
	
	public ThreadObject(int a) {
		super();
		commitCount = a;
	}
	
	public void run () {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Account.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		for (int i = 0; i < commitCount; i++) {

			LinkedHashMap<String, Object> parameters = new LinkedHashMap<String, Object>();
			parameters.put("Accounts", new Account(i, 010101, 2335, 00000, "A"));

			try {
				System.out.println(
						"\nCommand true: " + CommandExecutor.executeCommand("accountsops-insertAccounts", parameters));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * LinkedHashMap<String, Object> parametersDel = new LinkedHashMap<String,
		 * Object>(); parametersDel.put("column",
		 * AccountsOps.columns.NAME.getColumnName()); parametersDel.put("value",
		 * "Tolgahan Yıldırım"); System.out.println("\nCommand true: " +
		 * CommandExecutor.executeCommand("accountsops-deleteAccountsWithValue",
		 * parametersDel));
		 */
		session.close();
		sessionFactory.close();
	}
}

