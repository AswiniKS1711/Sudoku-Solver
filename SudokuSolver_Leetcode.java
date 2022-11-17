public class SudokuSolver_Leetcode 
{

        public void solveSudoku(char[][] board) {
            solve(board);
        }
        
        private boolean solve(char board[][])
        {
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board[i].length;j++)
                {
                    if(board[i][j]=='.')
                    {
                        for(char c='1';c<='9';c++)
                        {
                            if(isvalid(board,i,j,c))
                            {
                                board[i][j]=c;
                                
                                if(solve(board)==true)
                                    return true;
                                else
                                    board[i][j]='.';
                            }
                        }
                        return false; //If unable to fill any number 
                    }
                }
            }
            
            return true;
        }
        
        private boolean isvalid(char board[][],int row,int column,char c)
        {
            for(int i=0;i<=8;i++)
            {
                if(board[row][i] == c)
                    return false;
                
                if(board[i][column] == c)
                    return false;
                
                if(board[3*(row/3)+i/3][3*(column/3)+i%3] == c)
                    return false;
            }
            
            return true;
        }
}
