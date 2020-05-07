import java.util.LinkedList;
import java.util.Queue;

public class Word implements Comparable<Word>
{
	private int count; // Number of occurences of word
	private Queue<Integer> lineNumbers; // List of line number occurences of word
	private String wordText; // Text of word
	
	/**
	 * Default constructor
	 */
	public Word()
	{
		lineNumbers = new LinkedList<>();
	}

	/**
	 * Constructor for input word with no line number
	 * @param inWord 
	 */
	public Word(String inWord)
	{
		this(inWord, 0);
	}

	/**
	 * Constructor for input word and line number
	 * @param inWord
	 * @param lineNumber
	 */
	public Word(String inWord, int lineNumber)
	{
		lineNumbers = new LinkedList<>();
		wordText = inWord;
		countWord(lineNumber);
	}

	/**
	 * Adds lineNumber to list of line numbers if it is not already present, and increments count
	 * @param lineNumber
	 */
	public void countWord(int lineNumber)
	{
		count++;
		if (!lineNumbers.contains(lineNumber))
			lineNumbers.add(lineNumber);
	}

	/**
	 * Compares two Words lexicographically
	 * @return -1 if less than, 0 if equal, and 1 if greater than
	 */
	public int compareTo(Word other)
	{
		return wordText.compareTo(other.wordText);
	}

	/**
	 * returns true if the current word is less than other
	 * @param other 
	 * @return boolean value
	 */
	public boolean lessThan(Word other)
	{
		return (compareTo(other) < 0);
	}

	/**
	 * Returns true if the current word is equal to other
	 * @param other
	 * @return boolean value
	 */
	public boolean equals(Word other)
	{
		return (compareTo(other) == 0);
	}

	/**
	 * Creates and returns a formatted string representing the object.
	 */
	public String toString()
	{
		String bufferedText;
		int bufferSize = 24 - wordText.length() - String.valueOf(count).length(); // Determine number of buffer characters for a 24 char long output

		String bufferString = "";
		if (bufferSize > 0) // If the number of buffer characters is positive, fill a new string with bufferSize '.' characters
		{
			for (int i = 0; i < bufferSize; i++)
			{
				bufferString += ".";
			}
		}
		
		bufferedText = wordText + bufferString + count + ": "; // And create the formatted string filling whitespace with '.'

		for (Integer s2 : lineNumbers) // Then print line numbers seperated by whitespace
		{
			bufferedText += s2.toString() + ' ';
		}
		return bufferedText;
	}
	
	/**
	 * 
	 * @return count
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * Sets count to a new value
	 * @param count
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

	/**
	 * 
	 * @return list of line number occurences of word
	 */
	public Queue<Integer> getLineNumbers()
	{
		return lineNumbers;
	}

	/**
	 * Sets list of line numbers to another list
	 * @param lineNumbers
	 */
	public void setLineNumbers(Queue<Integer> lineNumbers)
	{
		this.lineNumbers = lineNumbers;
	}

	/**
	 * 
	 * @return text of word object
	 */
	public String getWordText()
	{
		return wordText;
	}

	/**
	 * Sets text of word object
	 * @param wordText
	 */
	public void setWordText(String wordText)
	{
		this.wordText = wordText;
	}
}