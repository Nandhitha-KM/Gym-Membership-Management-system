package com.gym.util;

public class ValidationException extends Exception{

	@Override
	public String toString() {
		return "Validation Failed :Invalid Input";
	}
}
