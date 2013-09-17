import java.io.File ;
import java.util.Scanner ;
import java.util.ArrayList ;
import java.util.Arrays;

public class gameOfLife
{
	public static void main(String[] args)
		throws java.io.FileNotFoundException
	{
		readFile("life.txt");
	}


	/*--
	Reading the input file
	--*/
	static void readFile(String fileName)
		throws java.io.FileNotFoundException
	{

		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = new Scanner(new File(fileName));
		scan.useDelimiter("");

		while(scan.hasNextLine())
		{
			lines.add(scan.nextLine());
			
		}
		
		int rowsCount = 0;
		int colsCount = 0;
		String[][] rowArray;
		String[] lineArray = new String[lines.size()];

		// convert ArrayList to Array of defined size
		lines.toArray(lineArray);

		for(int i=0;i < lineArray.length ; i++)
		{

			// first line input
			if(i == 0)
			{

				String[] rowsColsCount = lineArray[i].split(" ");
				rowsCount = Integer.parseInt(rowsColsCount[0]);
				colsCount = Integer.parseInt(rowsColsCount[1]);
			
			}
			else 
			{
				/*
				for every row create an array of the 'stars' and 'spaces'				
				 */
				rowArray[i] = lineArray[i].split("");
				for (int j ; j < colsCount ; j++)
				{
					if(rowArray[i][j] == "*")
					{
						System.out.println(rowArray[i][j]);
					}
					else
					{
						System.out.println("-");
					}
				}
				
			}
		}

	}

}