package model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="batch_data")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int	sıraNo;
	private int	status;
	private int accountNo;
	private int amount;
	private String transactionType;
		
	public Account() {}
	
	public Account(int sıraNo, int status , int accountNo, int amount, String transactionType ) {
		this.sıraNo = sıraNo;
		this.status = status;
		this.accountNo = accountNo;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getsıraNo() {
		return this.sıraNo;
	}

	public void setName(int sıraNo) {
		this.sıraNo = sıraNo;
	}

	public int status() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getaccountNo() {
		return accountNo;
	}

	public void setaccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String gettransactionType() {
		return transactionType;
	}
	public void settransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	
	@Override
	public String toString(){
		return "[ Id: "+id+", Sıra No: "+sıraNo+", Status: "+status+", Account No: "+accountNo+" Amount: " +amount+" Transaction Type: " +transactionType+"]";
	}
}
