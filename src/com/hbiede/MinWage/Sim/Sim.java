package com.hbiede.MinWage.Sim;

import com.hbiede.MinWage.MinWageMain;
import com.hbiede.MinWage.Reference;

import java.util.Random;

/**
 * com.hbiede.MinWage.Sim
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public class Sim {
	public static void simPayPeriod() {
		int   businessLocation;
		float spendingAmount;
		float cycleSpendingAmount;
		float spentAmount;
		int   cycleCount;

		/** Peep Payment */
		for (int i = 0; i < MinWageMain.peepArray.length; i++) {
			if (!(MinWageMain.peepArray[i].job == null)) {
				if (MinWageMain.peepArray[i].job.bankBalance > MinWageMain.peepArray[i].wage) {
					MinWageMain.peepArray[i].incomeDeposit(MinWageMain.peepArray[i].wage * Reference.workWeekLength);
					MinWageMain.peepArray[i].job.spend(MinWageMain.peepArray[i].wage * Reference.workWeekLength);
					if (Reference.isDebugOn)
						System.out.printf("Paid Peep #%d: $%d. Bank Balance: $%f. Paid from Business #%d. Bank Balance: $%f%n", i, MinWageMain.peepArray[i].wage * Reference.workWeekLength, MinWageMain.peepArray[i].bankBalance, MinWageMain.peepArray[i].job.refNumber, MinWageMain.peepArray[i].job.bankBalance);
				} else {
					if (Reference.isDebugOn)
						System.out.printf("Business #%d is bankrupt. Balance: %f%n", MinWageMain.peepArray[i].job.refNumber, MinWageMain.peepArray[i].job.bankBalance);
					MinWageMain.peepArray[i].job.isAfloat = false;
				}
				if (!MinWageMain.peepArray[i].job.isAfloat) {
					if (Reference.isDebugOn)
						System.out.printf("Peep #%d was laid off from business %d%n", i, MinWageMain.peepArray[i].job.refNumber);
					MinWageMain.peepArray[i].job = null;
					MinWageMain.peepArray[i].wage = 0;
				}

			}

			/** Peep Shopping Day */
			if (MinWageMain.peepArray[i].bankBalance > 0) {
				Random rand = new Random();
				spentAmount = 0;
				spendingAmount = ((rand.nextFloat() + .3F) * (MinWageMain.peepArray[i].frugality / 100) * MinWageMain.peepArray[i].wage);
				if (spendingAmount < 1) MinWageMain.peepArray[i].isAfloat = false;
				if (spendingAmount < Reference.minimumSpendingCap) spendingAmount = Reference.minimumSpendingCap;
				cycleCount = 0;
				do {
					int j = 0;
					do {
						businessLocation = rand.nextInt(MinWageMain.businessArray.length);
					} while (!MinWageMain.businessArray[businessLocation].isAfloat);
					cycleSpendingAmount = rand.nextFloat() * spendingAmount;
					spendingAmount -= cycleSpendingAmount;
					MinWageMain.peepArray[i].spend(cycleSpendingAmount);
					MinWageMain.businessArray[businessLocation].incomeDeposit(cycleSpendingAmount);
					spentAmount += cycleSpendingAmount;
					if (Reference.isDebugOn)
						System.out.printf("Peep #%d spending %f at Business %d%n", i, cycleSpendingAmount, businessLocation);
					cycleCount++;
				} while (spentAmount < spendingAmount && cycleCount < 5);
			}
			if (MinWageMain.peepArray[i].isAfloat) {
				if (MinWageMain.peepArray[i].bankBalance <= 0) {
					if (Reference.isDebugOn)
						System.out.printf("Peep #%d is bankrupt.%n", i);
					MinWageMain.peepArray[i].isAfloat = false;
				}
			}
		}
	}
}
