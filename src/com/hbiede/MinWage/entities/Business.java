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

	/**
	 * @param incomeAmount Amount to add to a bank balance
	 */
	@Override
	public void incomeDeposit(float incomeAmount) {
		bankBalance += incomeAmount;
	}

	/**
	 * @param spendAmount Amount to subtract from the bank balance
	 */
	@Override
	public void spend(float spendAmount) {
		bankBalance -= spendAmount;
	}
}
