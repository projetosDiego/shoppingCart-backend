package com.shopcart.exception;

public class ItemAlreadyAdded extends RuntimeException{

    private static final long serialVersionUID = 7465743800857659454L;

	public ItemAlreadyAdded(String message) {
        super(message);
    }
}