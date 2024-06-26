package cloud;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import cloud.BankAccount;

class ATM 
{
	private BankAccount account;
	private int pin = 1111;
	private List<String> miniStatement;
	
	public ATM(double initialBalance) 
	{
        this.account = new BankAccount(initialBalance);
	this.miniStatement = new ArrayList<>();
	}

	public void checkPin()
	{
		System.out.println("\nEnter your PIN : ");
		Scanner scan=new Scanner(System.in);
		int enteredPin= scan.nextInt();
		if(enteredPin==pin)
			menu();
		else
		{
			System.out.println("\nEntered PIN is invalid");
			System.out.println("Enter a valid PIN : ");
			int reEnteredPin= scan.nextInt();
			if(reEnteredPin==pin)
				menu();
			else
				System.out.println("\nSorry..!! You can not access account");
		}
	}

	public void menu()
	{
		System.out.println("\n1. Check A/C Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Deposit Money");
		System.out.println("4. Mini Statement");
        	System.out.println("5. Exit");
		System.out.println("\nEnter your choice : ");
		
		Scanner scan=new Scanner(System.in);
		int ch= scan.nextInt();

		switch(ch)
		{
			case 1: checkBalance();
				break;
			case 2: withdraw();
				break;
			case 3: deposit();
				break;
			case 4:	miniStatement();
               			break;
            		case 5:	System.out.println("\nThank You for coming..!!");
                		System.exit(0);
            		default:System.out.println("\nEnter a valid choice");
                		menu();
		}
	}
	
	public void checkBalance()
	{
		System.out.println("\nBalance : "+account.getBalance());
		menu();
	}

	public void withdraw()
	{
		System.out.println("\nEnter the Amount to Withdraw : ");
		Scanner scan=new Scanner(System.in);
		double amount = scan.nextDouble(); 
		
		if(amount>account.getBalance())
			System.out.println("\nInsufficient Balance");
		else
		{
			account.setBalance(account.getBalance()-amount);
			miniStatement.add("Withdraw: " + amount);
            		if (miniStatement.size() > 5) 
			{
                		miniStatement.remove(0);
           		}
			System.out.println("\nMoney Withdrawl Successfully");
		}
		menu();
	}

	public void deposit()
	{
		System.out.println("\nEnter the Amount to Deposit : ");
		Scanner scan=new Scanner(System.in);
		double amount = scan.nextDouble();
		if(amount==0)
			System.out.println("\nNo Amount Deposited");
		else
		{
			account.setBalance(account.getBalance()+amount);
			miniStatement.add("Deposit: " + amount);
            		if (miniStatement.size() > 5)
			{
                		miniStatement.remove(0);
            		}
			System.out.println("\nMoney Deposited Successfully");
		}
		menu();
	}

	public void miniStatement()
	{
        	System.out.println("Mini Statement :\n");
        	for (String transaction : miniStatement) 
		{
            		System.out.println(transaction);
        	}
        	menu();
    	}

	public static void main(String args[])
	{
		ATM atm= new ATM(1000.0);
		atm.checkPin();
	}

}