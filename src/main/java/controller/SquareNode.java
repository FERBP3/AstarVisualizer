package controller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareNode {
  private Rectangle rect;
  private boolean blocked;
  private int row;
  private int col;
  private int hn;

  public SquareNode(Rectangle rect, int row, int col){
    this.rect = rect;
    this.row = row;
    this.col = col;
    this.blocked = false;
  }

  public void heuristic(SquareNode target){
    int diffRow = Math.abs(row-target.getRow());
    int diffCol = Math.abs(col-target.getCol());
    hn = (diffCol+diffRow)*10;
  }

  public Rectangle getRect(){
    return rect;
  }

  public int getRow(){
    return row;
  }

  public int getCol(){
    return col;
  }

  public int hn(){
    return hn;
  }

  public boolean isBlocked(){
    return blocked;
  }

  public boolean setBlocked(){
    return this.blocked = true;
  }

  public void reset(){
    rect.setFill(Color.TRANSPARENT);
    blocked = false;
    hn = 0;
  }
}
