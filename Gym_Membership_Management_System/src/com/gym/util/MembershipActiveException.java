package com.gym.util;

public class MembershipActiveException extends Exception {

	@Override
	public String toString() {
		return "Membership Active Exception:Member has an Active Membership " ;
	}
}
