/**
 * Shortest Common Super String Problem 
 * DATA SET 1 (in abba land)
 * @author Leszek A Gasieniec
 */

// find the shortest string which contains 

import java.util.*;
public class SuperString1 {


	public static void main(String[] args) {

		int fail=0, evid=-1; // failure flag + evidence

		float REF=25; // reference point

		float Quotient=100;

		String Solution=""; // initialisation of the solution

		String[] S = new String[20];

		S[0] = "babbabbababba";
		S[1] = "bbabbababbabb";
		S[2] = "ababbabbababb";
		S[3] = "abbababbabbab";
		S[4] = "bababbababbab";
		S[5] = "babbababbabab";
		S[6] = "babbabbababba";
		S[7] = "bbababbabbaba";
		S[8] = "ababbababbabb";
		S[9] = "abbababbababb";

		S[10] = "abbabbababbab";
		S[11] = "bababbabbabab";
		S[12] = "babbababbabba";
		S[13] = "babbabbababba";
		S[14] = "bbababbabbaba";
		S[15] = "ababbababbabb";
		S[16] = "abbababbababb";
		S[17] = "abbabbababbab";
		S[18] = "bababbabbabab";
		S[19] = "babbababbabba";


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
