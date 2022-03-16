package com.shopcart.exception;

public class NegativeQuantity extends RuntimeException{

    private static final long serialVersionUID = 7465743800857659454L;

	public NegativeQuantity(String message) {
        super(message);
    }
}