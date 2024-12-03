package bank_management_system;
import java.util.*;
import java.sql.*;
public class Bank_App
{
	private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args)
	{
		try {
			Class.forName("com.jdbc.mysql.cj.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Account a=null;
		User u=null;
		Account_Manager am=null;
		try {
			a= new Account(DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=root"),new Scanner(System.in));
			u= new User(DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=root"),new Scanner(System.in));
			am= new Account_Manager(DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=root"),new Scanner(System.in));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\t\t\t\t\tWellcome to GS Bank(GSB)");
		System.out.println("\t\t\t\t------------------------------------\n");
		System.out.println("1.new user\n2.existing user\nenter a choice");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			u.register();
			break;
		}
		case 2:
		{
			String value=u.login();
			System.out.println("1.Already have an bank account\n2.Open a new Account\n3.get my account number");
			int choice1=sc.nextInt();
			switch(choice1)
			{
			case 1:
			{
				String email=value;
				System.out.println("email : "+email);
				System.out.print("Enter your 4 digit pin : ");
				String pin=sc.next();
				Connection conn=null;
				try {
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank?user=root&password=Gokul@027");
					PreparedStatement psmt=conn.prepareStatement("SELECT * FROM ACCOUNTS WHERE EMAIL=? AND PIN=?;");
					psmt.setString(1, email);
					psmt.setString(2, pin);
					ResultSet rst=psmt.executeQuery();

					if(rst.next())
					{
						long acc_no=rst.getLong(1);
						System.out.println("1.Credit History\n2.Debit History\n3.Money Transfer\n4.Balance");
						int choice2=sc.nextInt();
						switch(choice2)
						{
						case 1:
						{
							am.credit_Money(acc_no);
							break;
						}
						case 2:
							am.debit_Money(acc_no);
							break;
						case 3:
							am.transfer_money(acc_no);
							break;
						case 4:
							System.out.println(am.check_Money(acc_no,pin));
						} 
					}
					else throw new RuntimeException("Invalid PIN number");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			case 2:
			{
				a.open_Account(value);
				break;
			}
			case 3:
			{
				System.out.println(a.get_Account_number(value));
				break;
			}
			}
			break;
		}				
		}
	}
}
