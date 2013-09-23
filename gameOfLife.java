import java.io.File ;
import java.util.* ;

public class gameOfLife
{
    public static int rowsCount = 0;
    public static int colsCount = 0;

    public static void main(String[] args)
        throws java.io.FileNotFoundException
    {  
        int iterations;

        if(args.length == 0)
           iterations = 10;
        else
           iterations = Integer.parseInt(args[0]);
       
        ArrayList<ArrayList<Boolean>> gameState = readFile("life.txt");
        ArrayList<int[]> alivePositions = alivePositions(gameState);
        ArrayList<int[]> deadPositions  = deadPositions(gameState);
        ArrayList<int[]> willSustain  = willSustain(alivePositions,gameState);
        ArrayList<int[]> willFlourish = willFlourish(deadPositions,gameState);
        ArrayList<ArrayList<Boolean>> emptyGrid = emptyGrid();
        ArrayList<ArrayList<Boolean>> nextGen = setNextGen(emptyGrid,willSustain);
        ArrayList<ArrayList<Boolean>> newNextGen = setNextGen(nextGen,willFlourish);  
        printStates(newNextGen);

        for(int i = 0 ; i < iterations-1 ; i++ )
        {
            alivePositions = alivePositions(newNextGen);
            deadPositions  = deadPositions(newNextGen);
            willSustain  = willSustain(alivePositions,newNextGen);


            willFlourish = willFlourish(deadPositions,newNextGen);
            emptyGrid = emptyGrid();
            nextGen = setNextGen(emptyGrid,willSustain);

            newNextGen = setNextGen(nextGen,willFlourish);  
            printStates(newNextGen);
        }

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
            then add extra empty rows to our game
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

    @param : the x and y co-ordinates of the cell
    @return : a list of neighbors
    */
   public static ArrayList<int[]> getNeighbors(int x, int y)
   {
        ArrayList<int[]> neighbors = new ArrayList<int[]>();

        int[] position1 = {x-1,y-1};
        int[] position2 = {x-1,y};
        int[] position3 = {x-1,y+1};

        int[] position4 = {x,y-1};
        int[] position5 = {x,y+1};

        int[] position6 = {x+1,y-1};
        int[] position7 = {x+1,y};
        int[] position8 = {x+1,y+1};

        if(x-1 >= 0 && y-1 >= 0)
            neighbors.add(position1); 

        if(x-1>=0)
            neighbors.add(position2);

        if(x-1>=0 && y+1 <colsCount)
            neighbors.add(position3);

        if(y-1>=0)
            neighbors.add(position4);

        if(y+1 < colsCount)
            neighbors.add(position5);

        if(x+1 < rowsCount && y-1>=0)
            neighbors.add(position6);
        
        if(x+1 < rowsCount)
            neighbors.add(position7);

        if(x+1 < rowsCount && y+1 < colsCount)
            neighbors.add(position8);

        return neighbors;
    }

    /*
        check if the cell is alive in the original gameState list
        if alive return Boolean

     */ 
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

    /*
        create a list of alivePositions in the gameState
        @param : The original gameState list
        @return : the list of positions alive in an arrayList tuple e.g. : [{1,2}{2,3}]
     */ 
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

    /*
        create a list of deadPositions in the gameState
        @param : The original gameState list
        @return : the list of positions dead in an arrayList tuple e.g. : [{1,2}{2,3}]
     */
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
    @return : the list of elements that will stay alive for the next generation
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

    /*
        for the given grid set the variables to true or false for the next generation
        @param : the grid of positions with true-false set . the list of positions that you would want to set to true for the next generation
        @return : the grid that is formed from the list
     */
    public static ArrayList<ArrayList<Boolean>>  setNextGen(ArrayList<ArrayList<Boolean>> grid,ArrayList<int[]> givenArrayList)
    {   
        for(int[] cordo : givenArrayList)
        {
            ArrayList<Boolean> row = grid.get(cordo[0]);
            row.set(cordo[1],true);
        }

        return grid;
        
    }

    public static void printStates(ArrayList<ArrayList<Boolean>> gameState)
    {
        for(ArrayList<Boolean> row: gameState)
         {
             
            StringBuffer buffer = new StringBuffer();
            for(int i = 0 ; i<row.size();i++)
             {
                 if(row.get(i)==true)
                 {
                     buffer.append("*");
                 }
                 else
                 {
                    buffer.append("-");
                 }
            }

            System.out.println(buffer.toString());
            System.out.println();
            
         }

        System.out.println("===========================");
    }


}