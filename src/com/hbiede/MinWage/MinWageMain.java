package com.hbiede.MinWage;

import com.hbiede.MinWage.Sim.Sim;
import com.hbiede.MinWage.entities.Business;
import com.hbiede.MinWage.entities.Peep;

import java.util.Random;

/**
 * com.hbiede.MinWage
 * Created for Adams Central Science Fair 2016
 * Project Title: The Effects of Minimum Wage Changes on Simulated Micro Economies
 */

public class MinWageMain {
	public static Peep[]     peepArray     = new Peep[Reference.peepCount];
	public static Business[] businessArray = new Business[Reference.businessCount];
	public static int cycleCount;
	public static int businessesAfloat = Reference.businessCount;
	public static int businessesAfloatPrevious;
	public static int peepsAfloat = Reference.peepCount;
	public static int peepsAfloatPrevious;

	public static void main(String[] args) {
		cycleCount = 0;
		initBusinesses();
		initPeeps();
		do {
			cycleCount++;
			Sim.simPayPeriod();
			businessesAfloatPrevious = businessesAfloat;
			businessesAfloat = 0;
			peepsAfloatPrevious = peepsAfloat;
			peepsAfloat = 0;
			for (int i = 0; i < MinWageMain.businessArray.length; i++) {
				if (MinWageMain.businessArray[i].isAfloat)
					businessesAfloat++;
			}
			for (int i = 0; i < MinWageMain.peepArray.length; i++) {
				if (MinWageMain.peepArray[i].isAfloat)
					peepsAfloat++;
			}
			if (Reference.isDebugOn)
				System.out.printf("Cycle #%d. Businesses Remaining: %d. Bankrupt this Cycle: %d%nPeople Remaining: %d. People Bankrupt this Cycle: %d%n", cycleCount, businessesAfloat, businessesAfloatPrevious - businessesAfloat, peepsAfloat, peepsAfloatPrevious - peepsAfloat);
		} while (businessesAfloat > 1 && peepsAfloat > 1);
		System.out.printf("Cycles: %d\n", cycleCount);

	}

	private static void initBusinesses() {
		for (int i = 0; i < Reference.businessCount; i++) {
			businessArray[i] = new Business(i);
			if (Reference.isDebugOn) System.out.println("Business #" + i);
		}
	}

	private static void initPeeps() {
		int    randFrugality;
		int    business;
		int    randWageTest;
		int    randWage;
		Random rand = new Random();
		for (int i = 0; i <= Reference.peepCount - 1; i++) {
			randFrugality = rand.nextInt(3) + 98;
			business = rand.nextInt(businessArray.length);
			randWageTest = rand.nextInt(4);
			if (randWageTest == 0) {
				randWage = (rand.nextInt(Reference.minimumWage * Reference.maxWageFactor) + Reference.minimumWage);
			} else {
					randWage = Reference.minimumWage;
			}
			peepArray[i] = new Peep(randFrugality, randWage, businessArray[business]);
			if (Reference.isDebugOn)
				System.out.printf("Peep #%d, %d, $%d, Business #%d\n", i, randFrugality, randWage, business);
		}
	}
}
