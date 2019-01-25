package CalculateData;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import DataReader.LoadFile;

public class CalculateExpected {

	LoadFile f1 = new LoadFile();

	public void process(Scanner in) throws FileNotFoundException {

		PrintWriter out = new PrintWriter("CalculatedTotal.txt");
		int invoice = 180001;
		double startingBal = f1.processStartingBal();
		double endingBal = f1.processEndingBal();
		double tradeSum = startingBal;
		out.printf("%-19s %-20s %s", "Invoice Number", "Cash Amount", "Paid/Received");
		out.println();
		out.println();
		while (in.hasNextLine()) {

			double prices = in.nextDouble();
			String owed = in.next();
			out.printf("%-19s $%-19.2f %s", invoice, prices, owed);
			out.println();
			if (owed.equals("P")) {
				startingBal -= prices;
			} else if (owed.equals("R")) {
				startingBal += prices;
			}
			if (endingBal > ((double) Math.round(startingBal * 100d) / 100d)) {
				tradeSum = endingBal - ((double) Math.round(startingBal * 100d) / 100d);
			} else if (endingBal < ((double) Math.round(startingBal * 100d) / 100d)) {
				tradeSum = ((double) Math.round(startingBal * 100d) / 100d) - endingBal;
			}
			invoice++;
		}
		out.println();
		out.printf("%s $%.2f", "Your total after processing the items should be",
				((double) Math.round(startingBal * 100d) / 100d));
		out.println();
		out.printf("%s $%.2f", "You reported possessing", endingBal);
		out.println();
		out.println();
		if (((double) Math.round(startingBal * 100d) / 100d) != endingBal) {
			out.printf("%s $%.2f %s", "ERROR:", tradeSum, "WAS NOT ACCOUNTED FOR");

		} else if (((double) Math.round(startingBal * 100d) / 100d) == endingBal) {
			out.printf("%s $%.2f %s", "Success: The expected", startingBal, "is present.");
		}

		out.close();
	}
}
