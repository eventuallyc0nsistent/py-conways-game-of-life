import java.io.File ;
import java.util.Scanner ;
import java.util.ArrayList ;
import java.util.Arrays;

public class gameOfLife
{
	public static void main(String[] args)
		throws java.io.FileNotFoundException
	{
		ArrayList<ArrayList<Boolean>> gameState = readFile("life.txt");
        ArrayList<int[]> neighbors = getNeighbors(2,3);
        
	}


	/*--
	Reading the input file
	--*/
	public static ArrayList<ArrayList<Boolean>> readFile(String fileName)
		throws java.io.FileNotFoundException
	{

		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = new Scanner(new File(fileName));

		while(scan.hasNextLine())
			lines.add(scan.nextLine());
		
        scan.close();

		int rowsCount = 0;
        int colsCount = 0;
        String[] lineArray = new String[lines.size()];

        // convert ArrayList to Array of defined size
        lines.toArray(lineArray);

        // We will use pass this gameState throughout the program.
        ArrayList<ArrayList<Boolean>> gameState = null;

        boolean notFirstTime = false;

        for(String line:lines)
        {
            //check if its the First Line
            if(!notFirstTime){

                String[] rowColsCount = line.split(" ");
                rowsCount = Integer.parseInt(rowColsCount[0]);
                colsCount = Integer.parseInt(rowColsCount[1]);
                
                //We pass the rowsCount to the constructor of the arraylist
                gameState = new ArrayList<ArrayList<Boolean>>(rowsCount);
                notFirstTime = true;
            }
            else
            {
                String[] splitLineArray = line.split("");

                ArrayList<Boolean> tempArray = new ArrayList<Boolean>(colsCount);
                
                for (int j= 0 ; j<colsCount; j++)
                {
                    try {

	                    if(splitLineArray[j].equals("*"))
                            tempArray.add(j,true);
	                    else
                            tempArray.add(j,false);
	                }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    	tempArray.add(j,false);
                    }
                }
                gameState.add(tempArray);
            }

        } 

      return gameState;

	}

    // create the list of neighbors for points with x & y co-ordinates
    // {x-1,y-1}       {x-1,y}         {x-1,y+1} 
    //  {x,y-1}         {x,y}           {x,y+1}
    // {x+1,y-1}       {x+1,y}         {x+1,y+1}
   public static ArrayList<int[]> getNeighbors(int x, int y)
   {
        ArrayList<int[]> neighbors = new ArrayList<int[]>();

        int[] tempArray1 = {x-1,y-1};
        int[] tempArray2 = {x-1,y};
        int[] tempArray3 = {x-1,y+1};

        int[] tempArray4 = {x,y-1};
        int[] tempArray5 = {x,y+1};

        int[] tempArray6 = {x+1,y-1};
        int[] tempArray7 = {x+1,y};
        int[] tempArray8 = {x+1,y+1};

        if(x-1 > 0 && y-1 > 0)
            neighbors.add(tempArray1);

        if(x-1>0)
        {
            neighbors.add(tempArray2);
            neighbors.add(tempArray3);
        }

        if(y-1>0)
        {
            neighbors.add(tempArray4);
            neighbors.add(tempArray6);
        }
        
        neighbors.add(tempArray5);
        neighbors.add(tempArray7);
        neighbors.add(tempArray8);

        return neighbors;

    }

    public static ArrayList<Boolean> isAlive(int x,int y,ArrayList<ArrayList<Boolean>> gameState)
    {
        
    }

}