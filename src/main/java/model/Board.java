package model;

import java.util.LinkedList;

import controller.SearchAlgorithm;
import controller.SearchNode;
import controller.SquareNode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
  private SquareNode[][] state;
  private SquareNode source;
  private SquareNode target;
  private SearchAlgorithm alg;

  public Board(int numSquare){
    this.source = null;
    this.target = null;
    this.state = new SquareNode[numSquare][numSquare];
  }

  public void setSquareNode(int row, int col, Rectangle rect){
    state[row][col] = new SquareNode(rect, row, col);
  }

  public SquareNode getSource(){
    return source;
  }

  public SquareNode getTarget(){
    return target;
  }

  public void clean(){
    for (int row=0; row<state.length; row++){
      for (int col=0; col<state.length; col++){
        state[row][col].reset();
      }
    }
    source = null;
    target = null;
  }

  public void stepAlgorithm(){
    alg.expandPath();
  }

  public void scanSquares(){
    for (int row=0; row<state.length; row++){
      for (int col=0; col<state.length; col++){
        if (state[row][col].getRect().getFill().equals(Color.GREEN))
          source = state[row][col];
        else if (state[row][col].getRect().getFill().equals(Color.RED))
          target = state[row][col];
        else if (state[row][col].getRect().getFill().equals(Color.BLACK))
          state[row][col].setBlocked();
      }
    }
    this.alg = new SearchAlgorithm(this);
  }

  public LinkedList<SearchNode> getNeighbours(SearchNode current){
    LinkedList<SearchNode> neighbours = new LinkedList<>();
    int row = current.getSquare().getRow();
    int col = current.getSquare().getCol();

    if (row < 0 || row >= state.length)
      return neighbours;
    if (col < 0 || col >= state.length)
      return neighbours;

    LinkedList<Movement> movimientos = new LinkedList<>();
    movimientos.add(Movement.LEFT);
    movimientos.add(Movement.RIGHT);
    movimientos.add(Movement.UP);
    movimientos.add(Movement.DOWN);
    movimientos.add(Movement.UP_LEFT);
    movimientos.add(Movement.UP_RIGHT);
    movimientos.add(Movement.DOWN_LEFT);
    movimientos.add(Movement.DOWN_RIGHT);

    for (Movement mov : movimientos){
      SquareNode neighbour = applyMovement(row, col, mov);
      if (neighbour != null){
        SearchNode node = new SearchNode(neighbour, mov);
        neighbour.heuristic(target);
        neighbours.add(node);
      }
    }
    return neighbours;
  }

  private SquareNode applyMovement(int row, int col, Movement mov){
    SquareNode neighbour = null;

    if (mov == Movement.UP){
      if (row > 0)
        neighbour = state[row-1][col];
    }else if (mov == Movement.RIGHT){
      if (col < state.length-1)
        neighbour = state[row][col+1];
    }else if (mov == Movement.DOWN){
      if (row < state.length-1)
        neighbour = state[row+1][col];
    }else if (mov == Movement.LEFT){
      if (col > 0)
        neighbour = state[row][col-1];
    }else if (mov == Movement.UP_LEFT){
      if (row > 0 && col > 0)
        neighbour = state[row-1][col-1];
    }else if (mov == Movement.UP_RIGHT){
      if (row > 0 && col < state.length-1)
        neighbour = state[row-1][col+1];
    }else if (mov == Movement.DOWN_LEFT){
      if (row < state.length-1 && col > 0)
        neighbour = state[row+1][col-1];
    }else if (mov == Movement.DOWN_RIGHT){
      if (row < state.length-1 && col < state.length-1)
        neighbour = state[row+1][col+1];
    }
    return neighbour;
  }
}
