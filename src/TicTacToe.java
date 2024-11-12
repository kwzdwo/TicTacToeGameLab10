import java.util.Scanner;

public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // X always moves first

    public static void main(String[] args)
    {
        clearBoard();
        Scanner in = new Scanner(System.in);

        while (true)
        {
            display();
            System.out.println("Player " + currentPlayer + "'s turn");
            int row, col;
            do
            {
                System.out.print("Enter row (1-3): ");
                row = in.nextInt() - 1;
                System.out.print("Enter column (1-3): ");
                col = in.nextInt() - 1;
            } while (!isValidMove(row, col));

            board[row][col] = currentPlayer;

            if (isWin(currentPlayer))
            {
                display();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isTie())
            {
                display();
                System.out.println("It's a tie!");
                break;
            }

            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }

        in.close();
    }

    private static void clearBoard()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    private static void display()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                System.out.print(board[i][j]);
                if (j < COL - 1)
                {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROW || col < 0 || col >= COL || !board[row][col].equals(" "))
        {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        return true;
    }

    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player)
    {
        for (int row = 0; row < ROW; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
                || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false; // There's still an empty space, not a tie yet
                }
            }
        }
        return true; // All spaces filled, it's a tie
    }
}