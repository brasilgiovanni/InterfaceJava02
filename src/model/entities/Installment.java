package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Installment {
	
	private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private LocalDate dueDate;
	private Double amount;
	
	public Installment(LocalDate dueDate, Double amount) {
		super();
		this.dueDate = dueDate;
		this.amount = amount;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return dueDate.format(fmt1) + " - " + String.format("%.2f",amount);
	}
	
	
	
	
	
}
