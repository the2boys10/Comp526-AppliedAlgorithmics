

import java.util.*;


public class DiscoveryProgram
{
	// set a bound for the max allowed height of the sequence
	public static int bestFoundRecursive = 128;
	// array to store the current growth rate of the bamboo
	public static int GrowthRate[] = new int[8];
	// array to store the current height of each bamboo
	public static int values[] = new int[8];
	// linkedlist to contain the order of cuts i.e the list of perque 1
	public static LinkedList<Integer> orderOfCuts = new LinkedList<Integer>();
	// linkedlist to contain the order of cuts i.e the list of perque 2
	public static LinkedList<Integer> orderOfCutsSecond = new LinkedList<Integer>();
	// linkedlist to contain the previous values of bamboo shoots before cutting them for perque 1
	public static LinkedList<Integer> trackOfValues = new LinkedList<Integer>();
	// linkedlist to contain the previous values of bamboo shoots before cutting them for perque 2
	public static LinkedList<Integer> trackOfValuesSecond = new LinkedList<Integer>();
	// linkedlist to contain the last picked option for bamboo shoot 1
	public static LinkedList<Integer> optionPickedLast = new LinkedList<Integer>();
	// linkedlist to contain the last picked option for bamboo shoot 2
	public static LinkedList<Integer> optionPickedLastSecond = new LinkedList<Integer>();
	// linkedlist to contain the previously found "most optimal solution"
	public static LinkedList<Integer> previousOrderOfCuts = new LinkedList<Integer>();
	// set the max value to the best found recursive value
	public static int maxValue = bestFoundRecursive;
	// set the max amount of iterations of our search alogithm (cut off for sequences that cannot satisfy the best
	// best found recursive value)
	public static int maxIterations = 2000000000;
	// length of the non-repeating sequence we want to find.
	public static int lengthWanted = 4000;

	public static void main(String[] args)
	{
		// we have not already failed at finding a repeating sequence
		boolean didFail = false;
		// we have not already failed at finding a repeating sequence previously
		boolean didFailPrevious = false;
		// set our growth rates
		GrowthRate[0] = 1;
		GrowthRate[1] = 2;
		GrowthRate[2] = 4;
		GrowthRate[3] = 8;
		GrowthRate[4] = 16;
		GrowthRate[5] = 32;
		GrowthRate[6] = 64;
		GrowthRate[7] = 128;
		do
		{
			// set our bamboo starting heights
			values[0] = 0;
			values[1] = 0;
			values[2] = 0;
			values[3] = 0;
			values[4] = 0;
			values[5] = 0;
			values[6] = 0;
			values[7] = 0;
			// reset all our linkedlists
			orderOfCutsSecond.clear();
			orderOfCuts.clear();
			trackOfValues.clear();
			trackOfValuesSecond.clear();
			optionPickedLastSecond.clear();
			optionPickedLast.clear();
			// last round we did not reset a cut
			boolean lastRoundRemove = false;
			// set our current amount of iterations
			int howManyIterations = 0;
			// while we have not reached the non-repeating length and are less than the max iterations we would like.
			while(orderOfCuts.size() <= lengthWanted && howManyIterations <= maxIterations)
			{
				// set the current amount of bamboo shoots above the restriction on height to be 0 and counts the amount
				// that are currently above it.
				int howManyAboveThreshhold = 0;
				for(int i = 0; i < values.length; i++)
				{
					values[i] += GrowthRate[i];
					if(values[i] + GrowthRate[i]> bestFoundRecursive)
					{
						howManyAboveThreshhold++;
					}
				}
				// if we have 3 ore more bamboo shoots above our max height restriction on the first cut we have failed
				// from the get go, so break out of our loop.
				if(howManyAboveThreshhold >= 3 && orderOfCuts.size() == 0)
				{
					break;
				}
				// if we are not in the first round and we have 3 or more then reset the last cut.
				if(howManyAboveThreshhold >= 3)
				{
					lastRoundRemove = resetLastRound();
				}
				// if there are only 2 bamboo's above the height restriction
				else if(howManyAboveThreshhold == 2)
				{
					// if we reset a previous round then we have already tried cutting the 2 bamboo shoots and it has
					// not worked so reset this round as well.
					if(lastRoundRemove == true)
					{
						// if we are on the first round then we cannot remove another cut because we are at the base
						// so stop.
						if(orderOfCuts.size() == 0)
						{
							break;
						}
						// reset the round.
						lastRoundRemove = resetLastRound();
					}
					// else find the 2 bamboo shoots that are above the max height restriction and cut them.
					else
					{
						int i = 0;
						for(; i < values.length; i++)
						{
							if(values[i]  + GrowthRate[i] > bestFoundRecursive)
							{
								break;
							}
						}
						int j = i;
						for(i = i + 1; i < values.length; i++)
						{
							if(values[i]  + GrowthRate[i]> bestFoundRecursive)
							{
								break;
							}
						}
						trackOfValues.add(values[j]);
						values[j] = 0;
						orderOfCuts.add(j);
						trackOfValuesSecond.add(values[i]);
						values[i] = 0;
						orderOfCutsSecond.add(i);
						lastRoundRemove = false;
					}
				}
				// if we only find one bamboo shoot above the max height then we need to cut this shoot as well as
				// any other bamboo shoot.
				else if(howManyAboveThreshhold == 1)
				{
					// if we removed a cut previously then
					if(lastRoundRemove == true)
					{
						// find out the cut we chose last for our other bamboo shoot and choose the next one
						int lastOptionPickedSecond = optionPickedLastSecond.removeLast() + 1;
						int i = 0;
						for(i = 0; i < values.length; i++)
						{
							if(values[i]  + GrowthRate[i]> bestFoundRecursive)
							{
								break;
							}
						}
						// if we are trying to cut the same bamboo shoot for both people add 1 to our "other bamboo"
						if(i == lastOptionPickedSecond)
						{
							lastOptionPickedSecond++;
						}
						// if we are still below the index of the last bamboo shoot then cut both chosen bamboo shoots
						if(lastOptionPickedSecond < values.length)
						{
							optionPickedLastSecond.add(lastOptionPickedSecond);
							trackOfValues.add(values[i]);
							values[i] = 0;
							orderOfCuts.add(i);
							trackOfValuesSecond.add(values[lastOptionPickedSecond]);
							values[lastOptionPickedSecond] = 0;
							orderOfCutsSecond.add(lastOptionPickedSecond);
							lastRoundRemove = false;
						}
						// else reset the round.
						else
						{
							if(orderOfCuts.size() == 0)
							{
								break;
							}
							lastRoundRemove = resetLastRound();
						}
					}
					// else we did not remove a round previous to this one i.e it is the first time we have encountered
					// this specific round instance
					else
					{
						// find the bamboo that is above its height restriction and add it to our cut's
						int i = 0;
						for(i = 0; i < values.length; i++)
						{
							if(values[i]  + GrowthRate[i]> bestFoundRecursive)
							{
								trackOfValues.add(values[i]);
								values[i] = 0;
								orderOfCuts.add(i);
								break;
							}
						}
						// if the bamboo that was above our height restriction was at index 0 then set our "other"
						// bamboo that we are going to cut to be at index 1
						if(i == 0)
						{
							i++;
						}
						// else set it to be bamboo at index 0 and add it to our cuts.
						else
						{
							i = 0;
						}
						optionPickedLastSecond.add(i);
						trackOfValuesSecond.add(values[i]);
						values[i] = 0;
						orderOfCutsSecond.add(i);
						lastRoundRemove = false;
					}
				}
				// else there are no bamboo shoots that are not satisfying our bamboo height restriction so we can pick
				// two "other" bamboo shoots
				else
				{
					// if we already been in this instance and found that it dosn't work then try another combination
					if(lastRoundRemove == true)
					{
						// get our last 2 bamboo shoots that we picked previously
						int lastOptionPicked = optionPickedLast.removeLast();
						int lastOptionPickedSecond = optionPickedLastSecond.removeLast();
						// if the last combination we tried cutting was index 6 and 7 ( we have tried all combinations)
						// then check we are not at our base case if we are then stop else reset the round.
						if(lastOptionPicked == values.length - 2 && lastOptionPickedSecond == values.length - 1)
						{
							if(orderOfCuts.size() == 0)
							{
								break;
							}
							lastRoundRemove = resetLastRound();
						}
						// else we still have different combinations we can try
						else
						{
							// if our second option has reached max index then increase the first option by 1 and reset
							// the second options index to the first option + 1
							if(lastOptionPickedSecond == values.length - 1)
							{
								lastRoundRemove = addToCuts(lastOptionPicked + 1, lastOptionPicked + 2);
							}
							// else just increase the index of our second option.
							else
							{
								lastRoundRemove = addToCuts(lastOptionPicked, lastOptionPickedSecond + 1);
							}
						}
					}
					// else its the first time we have encountered this instance so try the combination of index (0,1)
					else
					{
						lastRoundRemove = addToCuts(0, 1);
					}
				}
				// increase the iterations we have done so far.
				howManyIterations++;
			}
			// if we have exited the loop due to reacing the max iterations or because we have reached the base case
			// then set didFail to be true and multiply our best found recursive by 2 - 1, if we have already failed once already then exit loop.
			if(howManyIterations == maxIterations + 1 || orderOfCuts.size() == 0)
			{
				System.out.println("Failed to find a non-repeating sequence with minimal height of " + bestFoundRecursive);
				if(didFail)
				{
					didFailPrevious = true;
				}
				else
				{
					didFail = true;
					bestFoundRecursive = bestFoundRecursive * 2;
				}
			}
			// else we have exited nicely
			else
			{
				// add our two cut perque's together into one perque (makes it easier to test)
				LinkedList<Integer> mergedlists = new LinkedList<Integer>();
				while(orderOfCuts.size() > 0)
				{
					mergedlists.add(orderOfCuts.removeFirst());
					mergedlists.add(orderOfCutsSecond.removeFirst());
				}
				// try to find a repeating sequence in our perque.
				mergedlists = findRepeating(mergedlists);
				// if we find a repeating sequence (the first element of our perque is not 2000 then)
				if(mergedlists.getFirst() != 2000)
				{
					// Test the repeating sequence independantly on a fixed number of repetitions.
					maxValue = testIt(mergedlists);
					// print out that we have found a repeating sequence with maxValue and restriction value.
					System.out.println("Found repeating sequence with output " + maxValue + " and restriction " + bestFoundRecursive);
					// store this sequence
					previousOrderOfCuts = mergedlists;
					// if our value returned by our testing is less than our current recursive value then set that to be
					// our new restriction.
					if(maxValue <= bestFoundRecursive)
					{
						bestFoundRecursive = maxValue;
					}
				}
				// else we failed to find a repeating sequence
				else
				{
					System.out.println("Couldn't find a repeating sequence for height of " + bestFoundRecursive + " (Normally an issue with height being set too high)");
				}
				// if we have failed previously then try to find a sequence with restriction-1.
				if(didFail)
				{
					bestFoundRecursive--;
				}
				// else reduce our new restriction by a larger amount.
				else
				{
					bestFoundRecursive = bestFoundRecursive / 2;
				}
			}
		}
		while(! didFailPrevious);
		// if we have not found a previousOrderOfCuts then tell the user.
		if(previousOrderOfCuts.size() == 0)
		{
			System.out.println("\n\nCouldn't find a repeating sequence, your starting recursive value was too small");
		}
		// else print out that we have found a minimal (optimal) sequence
		// create 2 linkedlists which contain our 2 seperate perque's
		else
		{
			System.out.println("\n\nThe following sequence did not fail and was minimal with max height " + maxValue);
			LinkedList<Integer> perqueOne = new LinkedList<Integer>();
			LinkedList<Integer> perqueTwo = new LinkedList<Integer>();
			maxValue = testIt(previousOrderOfCuts);
			System.out.println(maxValue);
			while(previousOrderOfCuts.size() > 0)
			{
				perqueOne.add(previousOrderOfCuts.removeFirst());
				perqueTwo.add(previousOrderOfCuts.removeFirst());
			}
			// try to find smaller repeating sequences for each individual perque
			//perqueOne = findRepeatingSingular(perqueOne);
			//perqueTwo = findRepeatingSingular(perqueTwo);
			// print out our perque.
			while(perqueOne.size() > 0 || perqueTwo.size() > 0)
			{
				if(perqueOne.size() > 0)
				{
					System.out.print("Perque1.add(" + perqueOne.removeFirst() + ");\t");
				}
				if(perqueTwo.size() > 0)
				{
					System.out.println("Perque2.add(" + perqueTwo.removeFirst() + ");");
				}
			}
		}
	}

	// method to test our sequence independantly
	public static int testIt(LinkedList<Integer> testSet)
	{
		int values2[] = new int[8];
		// seperate bamboo height array
		values2[0] = 0;
		values2[1] = 0;
		values2[2] = 0;
		values2[3] = 0;
		values2[4] = 0;
		values2[5] = 0;
		values2[6] = 0;
		values2[7] = 0;
		// set the max value found so far to be 0
		int maxValue = 0;
		// for 20000 repeats
		for(int k = 0; k < 20000; k++)
		{
			// for all our decided cuts
			for(int i = 0; i < testSet.size() / 2; i++)
			{
				// add our growth rates to each bamboo.
				for(int j = 0; j < 8; j++)
				{
					values2[j] += GrowthRate[j];
				}
				for(int j = 0; j < 8; j++)
				{
					if(values2[j] > maxValue)
					{
						maxValue = values2[j];
					}
				}
				// cut the 2 bamboo shoots we have previously decided on
				values2[testSet.get(i * 2)] = 0;
				values2[testSet.get((i * 2) + 1)] = 0;
				// find the max value.
			}
		}
		return maxValue;
	}

	// add first and second to our dedicated bamboo cuts
	private static boolean addToCuts(int first, int second)
	{
		optionPickedLast.add(first);
		optionPickedLastSecond.add(second);
		trackOfValues.add(values[first]);
		values[first] = 0;
		orderOfCuts.add(first);
		trackOfValuesSecond.add(values[second]);
		values[second] = 0;
		orderOfCutsSecond.add(second);
		boolean lastRoundRemove = false;
		return lastRoundRemove;
	}

	// reset the current round.
	private static boolean resetLastRound()
	{
		for(int i = 0; i < values.length; i++)
		{
			values[i] -= GrowthRate[i];
		}
		values[orderOfCuts.removeLast()] = trackOfValues.removeLast();
		values[orderOfCutsSecond.removeLast()] = trackOfValuesSecond.removeLast();
		for(int i = 0; i < values.length; i++)
		{
			values[i] -= GrowthRate[i];
		}
		boolean lastRoundRemove = true;
		return lastRoundRemove;
	}

	// method to find a repeating sequence
	private static LinkedList<Integer> findRepeating(LinkedList<Integer> savedOrder)
	{
		// find out the length of our original sequence.
		int sizeOriginal = savedOrder.size();
		// remove the first and last 1/3 of the sequence found.
		for(int i = 0; i < sizeOriginal / 6; i++)
		{
			savedOrder.removeFirst();
			savedOrder.removeFirst();
			savedOrder.removeLast();
			savedOrder.removeLast();
		}
		// set our initial repeating length to 2
		int repeatingSequenceLength = 2;
		// we have found 0 consecutive repeats.
		int consecutiveRepeating = 0;
		// while we have not found atleast 10 repeating sequences
		while(consecutiveRepeating < 10)
		{
			// set consecutive repeats to 0
			consecutiveRepeating = 0;
			// if we can no longer get 10 repeating sequences then state we have not found a repeating sequence.
			if(repeatingSequenceLength == (savedOrder.size()/10)+1)
			{
				savedOrder.clear();
				savedOrder.add(2000);
				return savedOrder;
			}
			// set a new displacement value.
			int maxDisplacement = savedOrder.size() / repeatingSequenceLength;
			// increase our displacement
			for(int displacement = 1; displacement < maxDisplacement; displacement++)
			{
				// check if we have a replicated sequence of length repeating sequencelength for a specific displacement
				// value.
				boolean foundDiscrepency = false;
				for(int i = 0; i < repeatingSequenceLength; i++)
				{
					if(savedOrder.get(i + (repeatingSequenceLength * displacement)) != savedOrder.get(i))
					{
						// if we found a discrepency where the sequence has not repeated then flag it and exit.
						foundDiscrepency = true;
						break;
					}
				}
				// if we havn't found a discrepency then increment how many consecutive repeating sequences by 1.
				if(foundDiscrepency == false)
				{
					consecutiveRepeating++;
				}
				// else break and try a new sequence.
				else
				{
					break;
				}
			}
			// if we did not reach our consecutive repeating bound then find a larger repeating sequence.
			if(consecutiveRepeating < 10)
			{
				repeatingSequenceLength++;
				repeatingSequenceLength++;
			}
		}
		// create a new linkedlist with our new sequence.
		LinkedList<Integer> newList = new LinkedList<Integer>();
		for(int i = 0; i < repeatingSequenceLength; i++)
		{
			newList.add(savedOrder.get(i));
		}
		// return it.
		return newList;
	}

	// for each singular list try to find a smaller repeating sequence.
	private static LinkedList<Integer> findRepeatingSingular(LinkedList<Integer> savedOrder)
	{
		// set our initial repeating length to 1.
		int repeatingSequenceLength = 1;
		// we have 0 consecutive repeating
		int consecutiveRepeating = 0;
		// whilst we have less than 2 repeating values.
		while(consecutiveRepeating < 2)
		{
			consecutiveRepeating = 0;
			// if we can no longer get 2 consecutive repeating sequences then return our previous repeating sequence.
			if(repeatingSequenceLength == savedOrder.size()/2+1)
			{
				return savedOrder;
			}
			// else find our max displacement possible
			int maxDisplacement = savedOrder.size() / repeatingSequenceLength;
			for(int displacement = 1; displacement < maxDisplacement; displacement++)
			{
				// for each displacement value find out if there is a discrepency if there is then break out
				// else increment the amount of consecutive repeating sequences.
				boolean foundDiscrepency = false;
				for(int i = 0; i < repeatingSequenceLength; i++)
				{
					if(savedOrder.get(i + (repeatingSequenceLength * displacement)) != savedOrder.get(i))
					{
						foundDiscrepency = true;
						break;
					}
				}
				if(foundDiscrepency == false)
				{
					consecutiveRepeating++;
				}
				else
				{
					break;
				}
			}
			// if the amount of consectutive repeating sequences is less than 2 then increment the length of our
			// repeating sequence.
			if(consecutiveRepeating < 2)
			{
				repeatingSequenceLength++;
			}
		}
		// create a new list with our new repeating sequence and return it.
		LinkedList<Integer> newList = new LinkedList<Integer>();
		for(int i = 0; i < repeatingSequenceLength; i++)
		{
			newList.add(savedOrder.get(i));
		}
		return newList;
	}

}