import java.util.Scanner;

//-------------------- Abstraction -----------------------
abstract class ATMOperations {
	public abstract void withdraw(double amount);

	public abstract void deposit(double amount);

	public abstract double checkBalance();
}

//-------------------- Encapsulation ---------------------
class BankAc {
	private double balance;
	private int pin;

	public BankAc(double balance, int pin) {
		this.balance = balance;
		this.pin = pin;
	}

	public boolean validatePin(int enteredPin) {
		return this.pin == enteredPin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}

//-------------------- Inheritance + Polymorphism --------
class ATM extends ATMOperations {

	private BankAc account;

	public ATM(BankAc account) {
		this.account = account;
	}

	@Override
	public void withdraw(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount.");
		} else if (amount > account.getBalance()) {
			System.out.println("Insufficient balance.");
		} else {
			double newBalance = account.getBalance() - amount;
			account.setBalance(newBalance);
			System.out.println("Withdrawal successful.");
		}
	}

	@Override
	public void deposit(double amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount.");
		} else {
			double newBalance = account.getBalance() + amount;
			account.setBalance(newBalance);
			System.out.println("Deposit successful.");
		}
	}

	@Override
	public double checkBalance() {
		return account.getBalance();
	}
}

//----------------------- Main UI Class -------------------------
public class ATMSimulation {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// User account with initial balance & PIN
		BankAc acc = new BankAc(10000.0, 1234);

		// Polymorphism (abstract class reference)
		ATMOperations atm = new ATM(acc);

		System.out.println("********** Welcome to ATM **********");

		System.out.print("Enter PIN: ");
		int pin = sc.nextInt();

		if (!acc.validatePin(pin)) {
			System.out.println("Invalid PIN. Exiting...");
			return;

		}

		System.out.println("Login Successful!");

		while (true) {
			System.out.println("\n----- Menu -----");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Exit");
			System.out.print("Choose Option: ");

			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Current Balance: " + atm.checkBalance());
			} else if (choice == 2) {
				System.out.print("Enter amount to deposit: ");
				double amt = sc.nextDouble();
				atm.deposit(amt);
			} else if (choice == 3) {
				System.out.print("Enter amount to withdraw: ");
				double amt = sc.nextDouble();
				atm.withdraw(amt);
			} else if (choice == 4) {
				System.out.println("Thank you for using ATM!");
				break;
			} else {
				System.out.println("Invalid choice.");
			}
		}

		sc.close();
	}
}