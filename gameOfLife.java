import java.io.File ;
import java.util.* ;

public class gameOfLife
{
    public static int rowsCount = 0;
    public static int colsCount = 0;

	public static void main(String[] args)
		throws java.io.FileNotFoundException
	{  

        //global list of alive Positions
		ArrayList<ArrayList<Boolean>> gameState = readFile("life.txt");
        

        ArrayList<int[]> alivePositions = alivePositions(gameState);
        ArrayList<int[]> deadPositions  = deadPositions(gameState);
        ArrayList<int[]> willSustain  = willSustain(alivePositions,gameState);


        ArrayList<int[]> willFlourish = willFlourish(deadPositions,gameState);
        ArrayList<ArrayList<Boolean>> emptyGrid = emptyGrid();
        ArrayList<ArrayList<Boolean>> nextGen = setNextGen(emptyGrid,willSustain);

        ArrayList<ArrayList<Boolean>> newNextGen = setNextGen(nextGen,willFlourish);  


        

    }


	/*--
	Reading the input file : life.txt
    @param : fileName
    @return : gameState which consists of the array list of positions
	--*/
	public static ArrayList<ArrayList<Boolean>> readFile(String fileName)
		throws java.io.FileNotFoundException
	{

		ArrayList<String> lines = new ArrayList<String>();
		Scanner scan = new Scanner(new File(fileName));

		while(scan.hasNextLine())
			lines.add(scan.nextLine());
		
        scan.close();
		
        String[] lineArray = new String[lines.size()];

        // convert ArrayList to Array of defined size
        lines.toArray(lineArray);

        // We will use pass this gameState throughout the program.
        ArrayList<ArrayList<Boolean>> gameState = null;
        

        boolean notFirstTime = false;
        int countOfLines = 0;

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
                
                /*
                    for all the cells in the column set the sate as true and false 
                    if the size of the row is lesser than the column count then add false 
                    through the ArrayIndexOutOfBoundException
                 */
                for (int j= 1 ; j<=colsCount; j++)
                { 
                    try {

	                    if(splitLineArray[j].equals("*")) {
                            tempArray.add(j-1,true);
                            
                        }
	                    else
                            tempArray.add(j-1,false);
	                }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    	tempArray.add(j-1,false);
                    }
                }
                gameState.add(tempArray);
                countOfLines++;
            }

            

        }

        /* 
            if the size of the rows are less than the ones 
        */
        while(gameState.size() < rowsCount )
        {
           ArrayList<Boolean> tempArray = new ArrayList<Boolean>(colsCount);
           for (int j= 0 ; j<colsCount; j++)
           {
                tempArray.add(j,false);
           }
           gameState.add(tempArray);
           
        } 

      return gameState;

	}

    /*  
    create the list of neighbors for points with x & y co-ordinates
        {x-1,y-1}       {x-1,y}         {x-1,y+1} 
         {x,y-1}         {x,y}           {x,y+1}
        {x+1,y-1}       {x+1,y}         {x+1,y+1}
    */
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

        if(x-1 >= 0 && y-1 >= 0)
            neighbors.add(tempArray1); 

        if(x-1>=0)
            neighbors.add(tempArray2);

        if(x-1>=0 && y+1 <colsCount)
            neighbors.add(tempArray3);

        if(y-1>=0)
            neighbors.add(tempArray4);

        if(y+1 < colsCount)
            neighbors.add(tempArray5);

        if(x+1 < rowsCount && y-1>=0)
            neighbors.add(tempArray6);
        
        if(x+1 < rowsCount)
            neighbors.add(tempArray7);

        if(x+1 < rowsCount && y+1 < colsCount)
            neighbors.add(tempArray8);

        return neighbors;

    }

    // check if the cell is alive in the game list
    public static Boolean isAlive(int x,int y,ArrayList<ArrayList<Boolean>> gameState)
    {
        if( gameState.get(x).get(y) )
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    // create a list of alivePositions in the gameState
    public static ArrayList<int[]> alivePositions(ArrayList<ArrayList<Boolean>> gameState)
    {
        int rowNumber = 0;
        ArrayList<int[]> alivePositions = new ArrayList<int[]>();

        for(ArrayList<Boolean> row : gameState)
        {
            
                      
            for(int i=0; i<row.size();i++)
            {
                if(row.get(i))
                {
                    int[] alivePoint = {rowNumber,i};
                    alivePositions.add(alivePoint);
                }
            }
            
            rowNumber++;
        }

        return alivePositions;
    }

    // create a list of deadPositions in the gameState
    public static ArrayList<int[]> deadPositions(ArrayList<ArrayList<Boolean>> gameState)
    {
        int rowNumber = 0;
        ArrayList<int[]> deadPositions = new ArrayList<int[]>();

        for(ArrayList<Boolean> row : gameState)
        {

            for(int i=0; i<row.size();i++)
            {
                if(!row.get(i))
                {
                    int[] alivePoint = {rowNumber,i};
                    deadPositions.add(alivePoint);    
                }
            }

            
            rowNumber++;
        }

        return deadPositions;
    }

    /* 
    this will give a list of ALIVE cells that will stay alive for the next generation
    @param : the list of Alive Positions in the current game State list
    @return : the list of elements that will sustain for the next generation
    */ 
    public static ArrayList<int[]> willSustain(ArrayList<int[]> alivePositions,ArrayList<ArrayList<Boolean>> gameState)
    {
        ArrayList<int[]> willSustain = new ArrayList<int[]>() ;
        for( int i=0 ; i < alivePositions.size(); i++)
        {

                // for each cell with co-ordinates x and y
                int x = alivePositions.get(i)[0];
                int y = alivePositions.get(i)[1];

                // get neighbors
                ArrayList<int[]> neighbors = getNeighbors(x,y); 

                int countNeighbor = 0;
                for(int[] neighbor : neighbors)
                {   
                    if(isAlive(neighbor[0],neighbor[1],gameState))
                    {   
                        countNeighbor += 1;
                    }

                }

                if(countNeighbor == 2 || countNeighbor == 3)
                {
                    int[] addNeigbhor = {x,y};
                    willSustain.add(addNeigbhor);
                }
                
        }

        return willSustain;
        
    }

     /* 
    this will give a list of dead cells that will stay alive for the next generation
    @param : the list of dead Positions in the current game State list
    @return : the list of elements that will flourish for the next generation
    */ 
    public static ArrayList<int[]> willFlourish(ArrayList<int[]> deadPositions,ArrayList<ArrayList<Boolean>> gameState)
    {
        ArrayList<int[]> willFlourish = new ArrayList<int[]>() ;
        for( int i=0 ; i < deadPositions.size(); i++)
        {
            // for each cell with co-ordinates x and y
            int x = deadPositions.get(i)[0];
            int y = deadPositions.get(i)[1];
            // System.out.println(x+" "+y);

            // get neighbors
            ArrayList<int[]> neighbors = getNeighbors(x,y); 

            //foreach neighbor if neighbor is alive increment counter 
            int countNeighbor = 0;
            for(int[] neighbor : neighbors)
            {   
                if(isAlive(neighbor[0],neighbor[1],gameState))
                {
                    countNeighbor += 1;
                }
            }

            if(countNeighbor == 3)
            {
                int[] addNeigbhor = {x,y};
                willFlourish.add(addNeigbhor);
            }
                
        }

        return willFlourish;
        
    }

    /*
        Create an empty grid of setting false positions
        @return : the empty grid in boolean value of cells to 'FALSE'

     */
    public static ArrayList<ArrayList<Boolean>> emptyGrid()
    {
        ArrayList<ArrayList<Boolean>> emptyGrid = new ArrayList<ArrayList<Boolean>>();
        for(int i = 0 ; i < rowsCount ; i++)
        {
            ArrayList<Boolean> rowArrayList = new ArrayList<Boolean>();
            for(int j=0; j < colsCount ; j++)
            {
                rowArrayList.add(j,false);
            }
            emptyGrid.add(rowArrayList);
        }

        return emptyGrid;
    }

    public static ArrayList<ArrayList<Boolean>>  setNextGen(ArrayList<ArrayList<Boolean>> grid,ArrayList<int[]> givenArrayList)
    {   
        for(int[] cordo : givenArrayList)
        {
            ArrayList<Boolean> row = grid.get(cordo[0]);
            row.set(cordo[1],true);
        }

        return grid;
        
    }


}