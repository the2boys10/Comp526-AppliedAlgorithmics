/**
 * Bamboo Fence Trimming Problem
 * DATA SET 3 (Small integers)
 * @author Leszek A Gasieniec
 */


import java.util.*;

public class BambooFencePow2 {


	public static void main(String[] args) {


		Queue<Integer> Perque1 = new LinkedList<Integer>();
		Queue<Integer> Perque2 = new LinkedList<Integer>();

		int Guard = 0;
		int[] GrowthRate;
		GrowthRate = new int[8];
		GrowthRate[0] = 1;
		GrowthRate[1] = 2;
		GrowthRate[2] = 4;
		GrowthRate[3] = 8;
		GrowthRate[4] = 16;
		GrowthRate[5] = 32;
		GrowthRate[6] = 64;
		GrowthRate[7] = 128;

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING ABOVE THIS LINE
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// feed the queue with a combination of indices 0,1,2,3,4,5,6,7 NOT LONGER than 1000
		// use operation Perque1.add(i) and Perque2.add(i) to feed the two queues
		// the following sequence of indices corresponds to the Round Robin (alternating between the queues) Approach

		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(3);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(7);	Perque2.add(0);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(3);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(2);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(3);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(1);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(3);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(2);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(5);	Perque2.add(7);
		Perque1.add(6);	Perque2.add(7);
		Perque1.add(4);	Perque2.add(7);
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// DO NOT CHANGE ANYTHING BELOW THIS LINE
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// use the periodic queue 10000 times

		int[] Count;
		Count = new int[8];
		Count[0]=1;
		Count[1]=1;
		Count[2]=1;
		Count[3]=1;
		Count[4]=1;
		Count[5]=1;
		Count[6]=1;
		Count[7]=1;

		int Index1, Index2;          // next index drawn from the queue

		float Quotient = 1.0f; // we want to see this quotient as small as possible

		int H=GrowthRate[0]+GrowthRate[1]+GrowthRate[2]+GrowthRate[3]+GrowthRate[4]+GrowthRate[5]+GrowthRate[6]+GrowthRate[7];
		// H is the sum of all growth rates
		float HalfH=((float)H/(float)2);

		int MaxHeight=0;


		for (int i = 0; i < 9999; i++) {

			// draw the next index from the front of the queue
			Index1=Perque1.remove(); Index2=Perque2.remove();

			//update maximum height if needed
			for (int j=0; j<8; j++)
				if ((Count[j]*GrowthRate[j])>MaxHeight) {MaxHeight=(Count[j]*GrowthRate[j]);}

			//update the height of each bamboo with cut and its daily growth rate
			for (int j=0; j<8; j++)
				if((Index1==j)||(Index2==j)) {Count[j]=1;}
				else {Count[j]++;}

			// stored the used index at the end of the queue
			Perque1.add(Index1); Perque2.add(Index2);


		} // end loop for



		// Compute quotient and print it
		Quotient=((float)MaxHeight/HalfH);
		System.out.println(" Quotient= " + Quotient + " MaxHeight= " + MaxHeight + " HalfH= " + HalfH);
	}

}


