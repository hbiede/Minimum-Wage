package com.hbiede.MinWage.entities;

/**
 * com.hbiede.MinWage.entities
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public abstract class Entity {
	public float   bankBalance;
	public boolean isAfloat;

	public Entity(float bankBalance) {
		this.bankBalance = bankBalance;
		this.isAfloat = true;
	}

	public abstract void incomeDeposit(int incomeAmount);

	public abstract void spend(int spendAmount);
}
