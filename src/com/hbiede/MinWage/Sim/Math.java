package com.hbiede.MinWage.Sim;

import com.hbiede.MinWage.MinWageMain;
import com.hbiede.MinWage.Reference;
import com.hbiede.MinWage.entities.Entity;

/**
 * com.hbiede.MinWage.Sim
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public class Math {
	static int bankruptPeeps;
	static int bankruptBusinesses;
	static int paidMinimum;
	static int bankruptFromMinimum;
	static int paidNonmin;
	static int bankruptFromNonmin;

	public static void math() {
		calcSurvivedMin();
		System.out.printf("%n%nMinimum Wage: %d%n", Reference.minimumWage);
		System.out.printf("Peep Count: %d%n", Reference.peepCount);
		System.out.printf("Number Peeps Paid MinWage : %d%n%n", paidMinimum);
		System.out.printf("Average Peep Bank Balance: %f%n", averageBankBalance(MinWageMain.peepArray));
		System.out.printf("Average Business Bank Balance: %f%n", averageBankBalance(MinWageMain.businessArray));
		System.out.printf("Average Wage Paid: %f%n%n", avgWage());
		System.out.printf("Percent Peeps/Businuesses Survived: %f%n", calcSurvived());
		System.out.printf("Number Peeps Survived : %d%n%n", Reference.peepCount - bankruptPeeps);
		System.out.printf("Percent Businesses Survived: %f%n", (1.0F - (float) bankruptBusinesses / Reference.businessCount) * 100);
		System.out.printf("Number Businesses Survived : %d%n%n", Reference.businessCount - bankruptBusinesses);
		System.out.printf("Percent Peeps Survived on MinWage: %f%n", calcSurvivedMin());
		System.out.printf("Number Peeps Survived on MinWage : %d%n%n", paidMinimum - bankruptFromMinimum);
		System.out.printf("Percent Peeps Survived not on MinWage: %f%n", calcSurvivedNonmin());
		System.out.printf("Number Peeps Survived not on MinWage : %d%n%n", paidNonmin - bankruptFromNonmin);
		System.out.printf("Cycles: %d%n", Reference.cycleCount);
	}

	/**
	 * @param population Population to be averaged
	 * @return
	 */
	public static float averageBankBalance(Entity[] population) {
		float balanceSum = 0;
		for (int i = 0; i <= population.length - 1; i++) balanceSum += population[i].bankBalance;
		return balanceSum / population.length;
	}

	public static float avgWage() {
		int wageSum = 0;
		for (int i = 0; i <= MinWageMain.peepArray.length - 1; i++) {
			wageSum += MinWageMain.peepArray[i].startingWage;
		}
		return (float) wageSum / MinWageMain.peepArray.length;
	}

	public static float calcSurvived() {
		bankruptPeeps = 0;
		bankruptBusinesses = 0;
		for (int i = 0; i <= MinWageMain.peepArray.length - 1; i++) {
			if (!MinWageMain.peepArray[i].isAfloat) bankruptPeeps++;
		}
		for (int i = 0; i <= MinWageMain.businessArray.length - 1; i++)
			if (!MinWageMain.businessArray[i].isAfloat) bankruptBusinesses++;
		return 100 - ((float) (bankruptPeeps + bankruptBusinesses) / (Reference.peepCount + Reference.businessCount) * 100);
	}

	public static float calcSurvivedMin() {
		bankruptFromMinimum = 0;
		paidMinimum = 0;
		for (int i = 0; i <= MinWageMain.peepArray.length - 1; i++) {
			if (MinWageMain.peepArray[i].startingWage == Reference.minimumWage) {
				paidMinimum++;
				if (!MinWageMain.peepArray[i].isAfloat) bankruptFromMinimum++;
			}
		}
		return (1.0F - (float) bankruptFromMinimum / paidMinimum) * 100;
	}

	public static float calcSurvivedNonmin() {
		bankruptFromNonmin = 0;
		paidNonmin = 0;
		for (int i = 0; i <= MinWageMain.peepArray.length - 1; i++) {
			if (MinWageMain.peepArray[i].startingWage != Reference.minimumWage) {
				paidNonmin++;
				if (!MinWageMain.peepArray[i].isAfloat) bankruptFromNonmin++;
			}
		}
		return (1 - (float) bankruptFromNonmin / paidNonmin) * 100;
	}
}
