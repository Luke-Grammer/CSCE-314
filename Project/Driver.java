
class Driver
{

	public static void main(String[] args)
	{

		try
		{
			if (args.length > 2)
			{
				throw new RuntimeException("Expected fewer command line arguments");
			}
			else if (args.length < 2)
			{
				throw new RuntimeException("Expected more command line arguments");
			}

			long timer = System.currentTimeMillis();

			System.out.println("Attempting to validate input filenames...");
			System.out.println("Filter file: " + args[0]);
			System.out.println("Data file:   " + args[1]);
			Indexer indexer = new Indexer(args[0], args[1]);
			System.out.println("Files successfully validated!\n");
			indexer.DoIndex();

			timer = System.currentTimeMillis() - timer;
			System.out.println("Total execution time: " + (timer / 1000f) + "s");
		} catch (Exception e)
		{
			System.out.println("\nERROR: " + e + "!");
			return;
		}
	}
}