//A slight twist in the logic has been done

public class SudokuSolver_Project {
    
    public static boolean solve(int board[][],int i,int j,int n)
    {
        //Base case
        if(i==n)
        {
            printSudoku(board);
            return true;
        }

        //If we are not inside the board, take the pointer to the next row's 1st column
        if(j==board.length)
        {
            return solve(board,i+1,0,n);
        }


        //If cell is already filled, just move ahead
        if(board[i][j]!=0)
            return solve(board,i,j+1,n);


        //Fill the empty cell with appropriate number
        for(int number=1;number<=9;number++)
        {
            //check if number can be filled or not
            if(isValid(board,i,j,number,n))
            {
                board[i][j]=number;

                boolean subAns=solve(board,i,j+1,n);

                if(subAns==true)
                    return true;
                
                //Bactrack and undo the changes
                board[i][j]=0;
            }
        }

        return false;
    }

    public static boolean isValid(int board[][],int row,int column,int number,int n)
    {
        for(int i=0;i<n;i++)
        {
            //Row check and column check simultaneously
            if(board[row][i]==number || board[i][column]==number)
                return false;
        }

        //3x3 grid check
        int root_n=(int)Math.sqrt(n);
        int start_row=row - row%root_n;
        int start_column=column - column%root_n;

        for(int i=start_row; i<start_row + root_n; i++)
        {
            for(int j=start_column; j<start_column + root_n; j++)
            {
                if(board[i][j]==number)
                    return false;
            }
        }

        return true;
        
    }

    public static void printSudoku(int board[][])
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[i].length;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

}
