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

		while(scan.hasNextLine())
		{
			lines.add(scan.nextLine());
			
		}
		
		int rowsCount = 0;
        int colsCount = 0;
        String[] lineArray = new String[lines.size()];

        // convert ArrayList to Array of defined size
        lines.toArray(lineArray);

        // We will use pass this gameState throughout the program.
        ArrayList<ArrayList<Boolean>> gameState = null;

        boolean notFirstTime = false;

        for(String line:lines){
           
            //Compiler designed to predict if values as true. call it pre-optimization babes. :)
            if(!notFirstTime){

                String[] rowColsCount = line.split(" ");
                rowsCount = Integer.parseInt(rowColsCount[0]);
                colsCount = Integer.parseInt(rowColsCount[1]);
                
                //We pass the rowsCount to the constructor of the arraylist
                gameState = new ArrayList<ArrayList<Boolean>>(rowsCount);
                notFirstTime = true;
            }
            else{
                String[] splitLineArray = line.split("");

                ArrayList<Boolean> tempArray = new ArrayList<Boolean>(colsCount);
                
                for (int j= 0 ; j<colsCount; j++){
                    
                    try {

	                    if(splitLineArray[j].equals("*")){
	                        tempArray.add(j,true);
	                        System.out.println("has to be true");
	                        System.out.println(tempArray.get(j));
	                    }
	                    else{
	                        tempArray.add(j,false);
	                        System.out.println("has to be false");
	                        System.out.println(tempArray.get(j));
	                    }

                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    	tempArray.add(j,false);
                    	System.out.println(tempArray.get(j));
                    }

                }
            }
        }

        //Now to print the gameState
         for(ArrayList<Boolean> tempList: gameState){
             
             StringBuffer buffer = new StringBuffer();
             for(int i = 0 ; i<tempList.size();i++){
                 if(tempList.get(i)==true){
                     buffer.append("*");
                 }
                 else{
                    buffer.append("-");
                 }
             }
             
             /*Prints each row in a line. in the prevous way in which you had done, it would print to a new line for every co-ordinate. Println prints to a new line*/
             System.out.println(buffer);
         }

	}

}