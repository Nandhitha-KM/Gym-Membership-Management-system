package com.gym.util;

import java.util.Arrays;

public class MembershipLimitExceededException extends Exception{

	@Override
	public String toString() {
		return "Membership Limit Exceeded: Cannot Enroll" ;
	}
}
