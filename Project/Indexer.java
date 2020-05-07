import java.io.*;

public class Indexer
{
	private final String INDEX_FILENAME_OUT = "indexResults.txt";
	private final String FILTER_FILENAME_OUT = "filterResults.txt";

	/** The Binary Search Trees of words to be indexed and words to skip */
	private BinarySearchTree<Word> indexedBST, filteredBST;

	/** The input files containing words to index and words to skip */
	private BufferedReader datafile, filterfile;

	/**
	 * Constructor that opens input Files and initializes empty Binary Search Trees
	 * 
	 * @param index_filename  is the filename of the file containing words to be
	 *                        indexed
	 * @param filter_filename is the filename of the file containing words to skip
	 *                        when indexing
	 * @throws FileNotFoundException when one of the specified input filenames
	 *                               cannot be found
	 */
	public Indexer(String filterFilename, String indexFilename) throws FileNotFoundException
	{
		filteredBST = new BinarySearchTree<>();
		indexedBST = new BinarySearchTree<>();

		datafile = new BufferedReader(new InputStreamReader(new FileInputStream(indexFilename)));
		filterfile = new BufferedReader(new InputStreamReader(new FileInputStream(filterFilename)));
	}

	/**
	 * Fills both Binary Search Trees and outputs the contents of the filled
	 * indexedBST to the file 'filterResults.txt'
	 * 
	 * @throws IOException if there is a problem reading files,
	 */
	public void DoIndex() throws IOException
	{
		FileFilterReader();
		System.out.println("Indexing data file, this may take a few seconds...");
		FileWordReader();

		System.out.println("Data file successfully indexed!");

		OutputResults(INDEX_FILENAME_OUT, FILTER_FILENAME_OUT);

		System.out.println("\nIndexed output stored in " + INDEX_FILENAME_OUT);
		System.out.println("Filtered output stored in " + FILTER_FILENAME_OUT);
	}

	/**
	 * Populates a Binary Search Tree with words from an input file that should be
	 * skipped when indexing
	 * 
	 * @throws IOException when there is a problem reading the input file
	 */
	private void FileFilterReader() throws IOException
	{
		int lineNumber = 1;
		for (String filterText; (filterText = filterfile.readLine()) != null; lineNumber++) // Loop through words to
																							// filter
		{
			Word filterWord = new Word(filterText, lineNumber);
			if (!filteredBST.contains(filterWord)) // If the word is not already in the BST
				filteredBST.insert(filterWord); // Add it
		}
	}

	/**
	 * Populates a Binary Search Tree with words from an input file that are not in
	 * the set of words to be skipped.
	 * 
	 * @throws IOException when there is a problem reading the input file
	 */
	private void FileWordReader() throws IOException
	{
		int lineNumber = 1;
		for (String line; (line = datafile.readLine()) != null; lineNumber++) // Loop through input lines
		{
			String[] tokens = line.split("[^\\w'-]|\\d"); // Split line on invalid characters (including whitespace)
			for (String dataString : tokens) // Loop through words in input line
			{
				dataString = dataString.replaceAll("[^'\\w]'|^'+|'$", ""); // Remove all leading and trailing
																			// apostrophes
				dataString = dataString.replaceAll("\\W-|^-|-$", ""); // Remove all leading and trailing hyphens

				if (dataString.length() == 0) // If the token is now empty, skip it
					continue;

				Word dataWord = new Word(dataString.toLowerCase(), lineNumber);
				Word foundWord = indexedBST.find(dataWord); // Find position in indexedBST

				if (foundWord == null) // If word is not in indexedBST
				{
					if (!filteredBST.contains(dataWord)) // If it's also not in filteredBST
						indexedBST.insert(dataWord); // insert it to indexedBST
				} 
				else // If word is in indexedBST
					foundWord.countWord(lineNumber); // Count it
			}
		}
	}

	/**
	 * Prints the indexed Binary Search Tree and filtered Binary Search Tree to
	 * respective output files
	 * 
	 * @throws IOException when there is a problem writing to an output file
	 */
	private void OutputResults(String indexFilenameOut, String filterFilenameOut) throws IOException
	{
		Writer indexedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexFilenameOut)));
		Writer filteredWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filterFilenameOut)));
		indexedBST.printTree(indexedWriter);
		filteredBST.printTree(filteredWriter);
		indexedWriter.close();
		filteredWriter.close();
	}
}
