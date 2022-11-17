
var arr = [[], [], [], [], [], [], [], [], []]

for (var i = 0; i < 9; i++) {
    for (var j = 0; j < 9; j++) {
        arr[i][j] = document.getElementById(i * 9 + j);

    }
}


var board = [[], [], [], [], [], [], [], [], []]

function FillBoard(board) {
    for (var i = 0; i < 9; i++) {
        for (var j = 0; j < 9; j++) {
            if (board[i][j] != 0) {
                arr[i][j].innerText = board[i][j];
            }

            else
                arr[i][j].innerText = ''
        }
    }
}

let GetPuzzle = document.getElementById('GetPuzzle')
let SolvePuzzle = document.getElementById('SolvePuzzle')

GetPuzzle.onclick = function () {
    var xhrRequest = new XMLHttpRequest() //requesting the API which will give new puzzles everytime
    xhrRequest.onload = function () {
        var response = JSON.parse(xhrRequest.response)
        console.log(response)
        board = response.board
        FillBoard(board) // This function will fill up the board
    }
    xhrRequest.open('get', 'https://sugoku.herokuapp.com/board?difficulty=easy')
    //we can change the difficulty of the puzzle to easy, medium, hard and random
    xhrRequest.send()
}

SolvePuzzle.onclick = () => {
    solve(board, 0, 0, 9);
};


function solve(board, i, j, n) {
    //Base case
    if (i == n) {
        // printSudoku(board);
        FillBoard(board);
        return true;
    }

    //If we are not inside the board, take the pointer to the next row's 1st column
    if (j == n) {
        return solve(board, i + 1, 0, n);
    }


    //If cell is already filled, just move ahead
    if (board[i][j] != 0)
        return solve(board, i, j + 1, n);


    //Fill the empty cell with appropriate number
    for (let number = 1; number <= 9; number++) {
        //check if number can be filled or not
        if (isValid(board, i, j, number, n)) {
            board[i][j] = number;

            let subAns = solve(board, i, j + 1, n);

            if (subAns) {
                return true;
            }

            //Bactrack and undo the changes
            board[i][j] = 0;
        }
    }

    return false;
}

function isValid(board, row, column, number, n) {
    for (let i = 0; i < n; i++) {
        //Row check and column check simultaneously
        if (board[row][i] == number || board[i][column] == number)
            return false;
    }

    //3x3 grid check
    let root_n = Math.sqrt(n);
    let start_row = row - row % root_n;
    let start_column = column - column % root_n;

    for (let i = start_row; i < start_row + root_n; i++) {
        for (let j = start_column; j < start_column + root_n; j++) {
            if (board[i][j] == number)
                return false;
        }
    }

    return true;

}