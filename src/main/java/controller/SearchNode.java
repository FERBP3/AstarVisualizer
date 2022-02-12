package controller;

import model.Movement;

public class SearchNode implements Comparable<SearchNode>{
  private SquareNode square;
  private SearchNode father;
  private Movement lastMovement;
  private int gn;

  public SearchNode(SquareNode square, Movement mov){
    this.square = square;
    this.lastMovement = mov;
    this.father = null;
  }

  public void setFather(SearchNode father){
    this.father = father;
  }


  public int gn(){
    return gn;
  }

  public int fn(){
    return gn+square.hn();
  }

  public void setGn(int gn){
    this.gn = gn;
  }

  public SquareNode getSquare(){
    return square;
  }

  public Movement getLastMov(){
    return lastMovement;
  }

  public SearchNode getFather(){
    return father;
  }

  @Override
  public int compareTo(SearchNode o) {
    return fn() - o.fn();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SearchNode) || obj == null)
      return false;
    SearchNode other = (SearchNode) obj;
    return square.getRect().equals(other.getSquare().getRect());
  }

}
