package DataReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import CalculateData.CalculateExpected;

public class LoadFile {

	Scanner user = new Scanner(System.in);

	public void loadFile() {

		CalculateExpected program = new CalculateExpected();

		boolean fileLoaded = false;
		while (fileLoaded == false) {
			System.out.print("Please enter the name of the file in which the trade information is being stored:  ");

			String fileName = user.next();

			try (Scanner in = new Scanner(new File(fileName))) {
				System.out.println();
				System.out.println("Success!");
				System.out.println();
				program.process(in);
				fileLoaded = true;
			} catch (IOException e) {
				System.out.println();
				System.out.println("ERROR please enter a valid filename to scan");
				System.out.println();
			}

		}
		user.close();
		System.out.println();
		System.out.print("Calculations complete. Please refer to file \"CalculatedTotal.txt\" for details.");
	}

	public double processStartingBal() {
		System.out.print("Please input the amount of cash you began with:  ");
		double startingBal = user.nextDouble();
		System.out.println();
		return startingBal;
	}

	public double processEndingBal() {
		System.out.print("Please input the amount of cash you ended with:  ");
		double endingBal = user.nextDouble();
		System.out.println();
		return endingBal;
	}
}