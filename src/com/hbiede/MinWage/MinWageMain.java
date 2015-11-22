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

	public static void main(String[] args) {
		initBusinesses();
		initPeeps();
		for (int i = 1; i <= Reference.cycleCount; i++) {
			Sim.simPayPeriod();
			System.out.printf("Cycle #%d%n", i);
		}


	}

	private static void initBusinesses() {
		for (int i = 0; i <= Reference.businessCount - 1; i++) {
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
			randFrugality = rand.nextInt(20) + 81;
			business = rand.nextInt(businessArray.length);
			randWageTest = rand.nextInt(4);
			if (randWageTest == 0) {
				randWage = (rand.nextInt(Reference.minimumWage * Reference.maxWageFactor) + Reference.minimumWage);
				peepArray[i] = new Peep(randFrugality, randWage, businessArray[business]);
			} else {
				randWageTest = rand.nextInt(66);
				if (randWageTest <= Reference.unemploymentRate) {
					randWage = 0;
					randFrugality = 0;
					peepArray[i] = new Peep(randFrugality, randWage, null);
				} else {
					randWage = Reference.minimumWage;
					peepArray[i] = new Peep(randFrugality, randWage, businessArray[business]);
				}
			}
			if (Reference.isDebugOn)
				System.out.printf("Peep #%d, %d, $%d, Business #%d%n", i, randFrugality, randWage, business);
		}
	}
}