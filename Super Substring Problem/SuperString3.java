/**
 * Shortest Common Super String Problem
 * DATA SET 3 (random instances)
 * @author Leszek A Gasieniec
 */

// find the shortest string which contains

import java.util.*;

public class SuperString3 {


	public static void main(String[] args) {

		int fail=0, evid=-1; // failure flag + evidence

		float REF=100; // reference point

		float Quotient=100;

		String Solution=""; // initialisation of the solution

		String[] S = new String[20];

		String[] R = new String[100]; // random sequence

		int[] T = new int[100]; // cover test

		Random rnumb = new Random();

		int r=0, cover=0;

		// generate a random sequence R of length 100

		for (int i = 0; i < 100; i++) {
			if (rnumb.nextInt(1000)>850) {R[i]="1";} else {R[i]="0";}
		}




		for (;cover==0;){

			for (int i = 0; i < 100; i++) {
				if ((i<15)||(i>84)) {T[i]=1;} else {T[i]=0;}
			}

			cover=1;

			// draw randomly 20 strings (of length 15 each) from R[], make sure R[] is covered

			for (int i = 0; i < 20; i++) {
				S[i]="";
			}

			for (int i = 0; i < 20; i++) {
				if (i==0) {
					for(int j=0; j<15; j++){
						S[i]=S[i]+R[j];
					}
					// System.out.println(S[0]);
				}

				if (i==1) {
					for(int j=0; j<15; j++){
						S[i]=S[i]+R[85+j];
					}
					// System.out.println(S[0]);
				}

				if (i>1) {
					r= rnumb.nextInt(85);
					for(int j=0; j<15; j++){
						S[i]=S[i]+R[r+j]; T[r+j]=1;
					}
				}
			}

			// check whether we have a proper coverage

			for (int i = 0; i < 100; i++) {
				if (T[i]==0) {cover=0;}
			}

			if (cover==0) {System.out.println("Invalid cover");} else {System.out.println("Valid cover");}

		} // end of the loop generating good cover

		// reset the random sequence

		for (int i = 0; i < 100; i++) {
			R[i]="0";
		}

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING ABOVE THIS LINE
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// Insert your solution below
		// In the current solution all strings from S[] are simply concatenated.
		// Try to improve this solution. Go as close as possible with quality quotient to (or even below) 1

		Solution=Approximate.findOutString(S);
		System.out.println("Approximate solution = \"" + Solution + "\", with length = " + Solution.length());
		Solution=Exact.findOutString(S);
		System.out.println("Exact solution = \"" + Solution + "\", with length = " + Solution.length());

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING BELOW THIS LINE
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// computes how many string are not included

		for (int i = 0; i < 20; i++) {
			if (!Solution.contains(S[i])) {fail++;evid=i;}
		}

		// Calculation of the quality quotient (wrt the reference REF)

		Quotient=((float)Solution.length())/REF;

		if (fail>0) {System.out.println(" This is not a superstring, string S["+evid+"] is not included, #fails= "+fail);}
		else {System.out.println(" Well done! Your quality quotient= "+Quotient);}


	}

}

