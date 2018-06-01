import java.util.*;

public class Approximate
{
	public static String findOutString(String[] words)
	{
		// remove duplicate strings
		words = cleanArray(words);
		// create a linked list of words (To contain the start subwords)
		LinkedList<Word> words2 = new LinkedList<Word>();
		// create a linked list of words (To contain the end subwords)
		LinkedList<Word> words3 = new LinkedList<Word>();
		// for all the strings we have
		for(String word : words)
		{
			// create a word object which contains reference to the global word, and its sub words
			Word tempWord = new Word(word, word);
			// add it to both lists
			words2.add(tempWord);
			words3.add(tempWord);
		}
		// set the length of subwords to be the full length of the word.
		Word.lengthOfSubword=words[0].length();
		// sort the list
		words3.sort(new Comparitor2());
		// reduce the subwords
		for(Word aWords3 : words3)
		{
			aWords3.reduceString();
		}
		// set the length of subwords to 1 less
		Word.lengthOfSubword--;
		// sort the list based on subwords
		words3.sort(new Comparitor2());
		// while the subwords length is greater than 0
		while(Word.lengthOfSubword> 0)
		{
			// for each starting prefix
			for(int i = 0; i < words2.size(); i++)
			{
				// get the word
				Word word2 = words2.get(i);
				for(int j = 0; j < words3.size(); j++)
				{
					// get the suffix
					Word word3 = words3.get(j);
					// if the index is the same it has the same global word
					if(word2.CURRENTINDEX != word3.CURRENTINDEX)
					{
						//do the comparison of the suffix and prefix
						int wordComparison = word2.currentSubWordStart.compareTo(word3.currentSubWord);
						// if they matched merge the words
						if(wordComparison == 0)
						{
							// get the prefix to add to the word
							String substringAddition = word2.WordMainWord.substring(word2.currentSubWord.length(), word2.WordMainWord.length());
							// add the prefix
							word3.WordMainWord = word3.WordMainWord.concat(substringAddition);
							// the words new prefix is the prefix from the other word
							word3.currentSubWord = word2.currentSubWord;
							// remove the word we merged from words3
							words3.remove(j);
							// get the index of the word from words2
							int index = words3.indexOf(words2.get(i));
							// remove it
							words2.remove(i);
							// remove the word we added the prefix to from the list of prefix's
							words3.remove(index);
							// add it at the correct position, i.e the place we removed the previous prefix from
							words3.add(index,word3);
							// because we changed the place of each of the words take the min of the size-1 and the current i.
							i = Math.min(i,words2.size()-1);
							// set the j value to -1 as we need to rescan suffix's
							j = -1;
							// get the new words in prefix.
							word2 = words2.get(i);
						}
						// break if the value of the string we are comparing to is greater than the current
						else if(wordComparison < 0)
						{
							break;
						}
					}
				}
			}
			// reduce the length of the substrings
			for(Word aWords3 : words3)
			{
				aWords3.reduceString();
			}
			// reduce the value of the subword's current length
			Word.lengthOfSubword--;
			// sort the list of suffix's
			words3.sort(new Comparitor2());
		}
		//create a string builder to merge the left over words.
		StringBuilder endString = new StringBuilder();
		for(Word aWords3 : words3)
		{
			endString.append(aWords3.WordMainWord);
		}
		// return the string
		return endString.toString();
	}

	// method to clean the array of duplicated
	private static String[] cleanArray(String[] initialArray)
	{
		// add all elements to a hashset and then convert it to an array
		HashSet<String> temp = new HashSet<String>();
		Collections.addAll(temp, initialArray);
		initialArray = temp.toArray(new String[temp.size()]);
		return initialArray;
	}
}
// comparitor to compare suffix's
class Comparitor2 implements Comparator<Word>
{
	@Override
	public int compare(Word o1, Word o2)
	{
		return o1.currentSubWord.compareTo(o2.currentSubWord);
	}
}

// word to store the current suffix and prefix as well as the global word string.
class Word
{
	private static int currentMaxIndex = 0;
	static int lengthOfSubword;
	final int CURRENTINDEX;
	String WordMainWord;
	String currentSubWord;
	String currentSubWordStart;
	public Word(String word, String mainWord)
	{
		currentSubWord = word;
		currentSubWordStart = word;
		WordMainWord = mainWord;
		CURRENTINDEX = currentMaxIndex;
		currentMaxIndex++;
	}

	// reduce the prefix and suffix
	public void reduceString()
	{
		currentSubWord = currentSubWord.substring(1,currentSubWord.length());
		currentSubWordStart = currentSubWordStart.substring(0,currentSubWordStart.length()-1);
	}

	public String toString()
	{
		return "["+WordMainWord+","+currentSubWord+"]";
	}
}