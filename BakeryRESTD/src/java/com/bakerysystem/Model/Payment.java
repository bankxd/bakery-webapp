package com.bakerysystem.Model;


public class Payment {
	private boolean paid;

	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	@Override
	public String toString() {
		return "Payment [paid=" + paid + "]";
	}
	public Payment(boolean paid) {
		super();
		this.paid = paid;
	}
}
