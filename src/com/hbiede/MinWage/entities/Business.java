package com.hbiede.MinWage.entities;

import com.hbiede.MinWage.Reference;

/**
 * com.hbiede.MinWage.entities
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public class Business extends Entity {
	public final int refNumber;

	public Business(int refNumber) {
		super(Reference.startBalanceBusiness);
		this.refNumber = refNumber;
	}

	@Override
	public void incomeDeposit(int incomeAmount) {
		bankBalance = bankBalance + incomeAmount;
	}

	@Override
	public void spend(int spendAmount) {
		bankBalance = bankBalance - spendAmount;
	}
}
