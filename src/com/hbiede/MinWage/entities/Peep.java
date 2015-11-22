package com.hbiede.MinWage.entities;

import com.hbiede.MinWage.Reference;

/**
 * com.hbiede.MinWage.entities
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public class Peep extends Entity {
	public final int      frugality;
	public       Business job;
	public       int      wage;

	public Peep(int f, int wage, Business job) {
		super(Reference.startBalancePeep);
		this.frugality = f;
		this.wage = wage;
		this.job = job;

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
