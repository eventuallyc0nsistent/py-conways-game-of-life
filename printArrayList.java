  //Now to print the gameState
         for(ArrayList<Boolean> tempList: gameState)
         {
             
            StringBuffer buffer = new StringBuffer();
            for(int i = 0 ; i<tempList.size();i++)
             {
                 if(tempList.get(i)==true)
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