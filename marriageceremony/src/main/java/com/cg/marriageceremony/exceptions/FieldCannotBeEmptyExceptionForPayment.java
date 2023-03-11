package com.cg.marriageceremony.exceptions;

public class FieldCannotBeEmptyExceptionForPayment extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FieldCannotBeEmptyExceptionForPayment() {
	}

	public FieldCannotBeEmptyExceptionForPayment(String message){
		super(message);
	}
}
