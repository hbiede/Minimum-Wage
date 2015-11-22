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
		/** Business Interest Collection */
		for (int i = 0; i <= MinWageMain.businessArray.length - 1; i++) {
			MinWageMain.businessArray[i].bankBalance = MinWageMain.businessArray[i].bankBalance * (1 + Reference.interestRate / 100);
		}
		for (int i = 0; i <= MinWageMain.peepArray.length - 1; i++) {
			/** Peep Payment */
			if (MinWageMain.peepArray[i].isAfloat) {
				if (!(MinWageMain.peepArray[i].job == null)) {
					if (MinWageMain.peepArray[i].job.bankBalance > MinWageMain.peepArray[i].wage) {
						MinWageMain.peepArray[i].incomeDeposit(MinWageMain.peepArray[i].wage * Reference.workWeekLength);
						MinWageMain.peepArray[i].job.spend(MinWageMain.peepArray[i].wage);
					} else {
						System.out.printf("Business #%d is bankrupt%n", MinWageMain.peepArray[i].job.refNumber);
						MinWageMain.peepArray[i].job.isAfloat = false;
					}
					if (Reference.isDebugOn)
						System.out.printf("Paid Peep #%d: $%d. Bank Balance: $%f%n", i, MinWageMain.peepArray[i].wage * Reference.workWeekLength, MinWageMain.peepArray[i].bankBalance);
					if (!MinWageMain.peepArray[i].job.isAfloat) {
						System.out.printf("Peep #%d was laid off from business %d%n", i, MinWageMain.peepArray[i].job.refNumber);
						MinWageMain.peepArray[i].job = null;
						MinWageMain.peepArray[i].wage = 0;
					}

				}

				/** Unemployment Assistant */
				if (MinWageMain.peepArray[i].wage == 0) {
					MinWageMain.peepArray[i].incomeDeposit(100);
					if (Reference.isDebugOn) System.out.printf("Paid Peep #%d from government aid: $%d%n", i, 100);
				}

				/** Peep Shopping Day */
				if (MinWageMain.peepArray[i].bankBalance > 0) {
					Random rand = new Random();
					spentAmount = 0;
					spendingAmount = (float) ((rand.nextFloat() + .1) * 0.04 * MinWageMain.peepArray[i].bankBalance);
					if (spendingAmount < Reference.minimumSpendingCap) spendingAmount = Reference.minimumSpendingCap;
					cycleCount = 0;
					do {
						do {
							businessLocation = rand.nextInt(MinWageMain.businessArray.length);
						} while (!MinWageMain.businessArray[businessLocation].isAfloat);
						cycleSpendingAmount = rand.nextFloat() * spendingAmount;
						spendingAmount = spentAmount - cycleSpendingAmount;
						MinWageMain.peepArray[i].bankBalance = MinWageMain.peepArray[i].bankBalance - cycleSpendingAmount;
						MinWageMain.businessArray[businessLocation].bankBalance = MinWageMain.businessArray[businessLocation].bankBalance + cycleSpendingAmount;
						spentAmount = spentAmount + cycleSpendingAmount;
						if (Reference.isDebugOn)
							System.out.printf("Peep #%d spending %f at Business %d%n", i, cycleSpendingAmount, businessLocation);
						cycleCount++;
					} while (spentAmount < spendingAmount && cycleCount < 5);
				}
				if (MinWageMain.peepArray[i].bankBalance <= 0) {
					System.out.println(i);
					System.out.printf("Peep #%d is bankrupt.%n", i);
					MinWageMain.peepArray[i].isAfloat = false;
				}
			}
		}
	}
}
