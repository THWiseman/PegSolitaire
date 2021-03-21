package cs5004.marblesolitaire.model;

import java.util.Arrays;

public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private final int armSize;
  private int[][] board;

  private int[][] makeBoard(int armSize) {
    //top left quadrant should all be -1 (invalid spaces)
    int[][] board = new int[armSize*3][armSize*3];
    for (int i = 0; i < armSize; i++) {
      for (int j = 0; j < armSize; j++) {
        board[i][j] = -1;
      }
      }
    //top middle quadrant should all be 1 (filled spaces)
    for(int i = 0; i<armSize;i++) {
      for(int j = armSize; j<armSize*2; j++) {
        board[i][j] = 1;
      }
    }
    //top right quadrant should all be -1 (invalid spaces)
    for(int i = 0; i<armSize;i++) {
      for(int j = armSize*2; j<armSize*3; j++) {
        board[i][j] = -1;
      }
    }
    //middle row should all be 1 (filled spaces)
    for(int i = armSize; i< armSize*2; i++) {
      for(int j = 0; j < armSize*3; j++) {
        board[i][j]=1;
      }
    }
    //bottom left quadrant should all be -1 (invalid spaces)
    for(int i = armSize*2; i< armSize*3;i++) {
      for(int j = 0; j < armSize; j++) {
        board[i][j] = -1;
      }
    }
    //bottom middle quadrant should all be 1 (filled spaces)
    for(int i = armSize*2; i< armSize*3; i++) {
      for(int j = armSize; j<armSize*2; j++) {
        board[i][j] = 1;
      }
      }
    //bottom right quadrant should all be -1 (invalid spaces)
    for(int i = armSize*2; i<armSize*3;i++) {
      for(int j = armSize*2; j<armSize*3;j++) {
        board[i][j] = -1;
      }
      }
    //make the middle square 0 (empty space)
    return board;
    }

  public MarbleSolitaireModelImpl() {
    this.armSize = 3;
    this.board = makeBoard(armSize);
    int middle = (armSize * 3) / 2;
    board[middle][middle] = 0;
}

  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.armSize = 3;
    this.board = makeBoard(armSize);
    if(sRow < 0 || sRow > armSize*3 || sCol < 0 || sCol > armSize*3 || board[sRow][sCol]==-1) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
          sRow, sCol));
    }
    this.board[sRow][sCol] = 0;
  }

  public MarbleSolitaireModelImpl(int armSize) {
    if (armSize%2==0 || armSize < 1) {
      throw new IllegalArgumentException("Arm thickness must be a positive, odd number");
    }
    this.armSize = armSize;
    this.board = makeBoard(armSize);
    int middle = (armSize*3)/2;
    board[middle][middle] = 0;
  }

  public MarbleSolitaireModelImpl(int armSize, int sRow, int sCol) {
    this.armSize = armSize;
    this.board = makeBoard(armSize);
    if(sRow < 0 || sRow > armSize*3 || sCol < 0 || sCol > armSize*3 || board[sRow][sCol]==-1) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
          sRow, sCol));
    }
    this.board[sRow][sCol] = 0;

  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if(fromRow < 0 || fromCol < 0 || fromRow > armSize*3 || fromCol > armSize*3) {
      throw new IndexOutOfBoundsException("fromRow or fromCol out of bounds.");
    }
    if(toRow < 0 || toCol < 0 || toCol > armSize*3 || toCol > armSize*3) {
      throw new IndexOutOfBoundsException("toRow or toCol out of bounds.");
    }
    if(board[fromRow][fromCol] != 1) {
      throw new IllegalArgumentException("Must move from a square with a marble on it.");
    }
    if(board[toRow][toCol] != 0) {
      throw new IllegalArgumentException("Must move to an empty space.");
    }

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }

  @Override
  public String toString(){
    String returnString = "";
    for(int i = 0; i < armSize*3; i++) {
      for(int j = 0; j < armSize*3; j++) {
        if (board[i][j] == -1) {
          returnString = returnString + String.valueOf(board[i][j]);
        } else {
          returnString = returnString + " " + String.valueOf(board[i][j]);
        }
      }
      returnString = returnString + "\n";
    }
    return returnString;
  }
}
