package controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import javafx.scene.paint.Color;
import model.Board;

public class SearchAlgorithm{
  SquareNode source;
  SquareNode target;
  SearchNode initialNode;
  PriorityQueue<SearchNode> openList;
  HashSet<SquareNode> closedList;
  Board board;
  SearchNode current;

  public SearchAlgorithm(Board board){
    this.board = board;
    this.source = board.getSource();
    this.target = board.getTarget();
    initialNode = new SearchNode(source, null);
    openList = new PriorityQueue<>();
    closedList = new HashSet<>();
    source.heuristic(target);
    openList.add(initialNode);
    current = null;
  }

  public void expandPath(){
    if (current != null && current.getSquare().equals(target)){
      paintPath();
      return;
    }

    if(!openList.isEmpty()){
      current = openList.poll();
      current.getSquare().getRect().setFill(Color.RED);
      closedList.add(current.getSquare());

      LinkedList<SearchNode> neighbours = board.getNeighbours(current);
      for (SearchNode neighbour : neighbours){
        if (closedList.contains(neighbour.getSquare()) || neighbour.getSquare().isBlocked())
          continue;

        neighbour.getSquare().getRect().setFill(Color.ORANGE);
        SearchNode previousNeighbour = null;
        for (SearchNode sn : openList){
          if (sn.equals(neighbour)){
            previousNeighbour = sn;
            break;
          }
        }

        neighbour.getSquare().heuristic(target);
        int new_gn = current.gn()+neighbour.getLastMov().getCosto();
        neighbour.setGn(new_gn);
        neighbour.setFather(current);

        if (previousNeighbour != null){
          if (new_gn < previousNeighbour.gn()){
            openList.remove(previousNeighbour);
            openList.add(neighbour);
          }
        }else{
          openList.add(neighbour);
        }
      }
    }
  }

  public void paintPath(){
    SearchNode finalNode = current;
    while (finalNode != null){
      finalNode.getSquare().getRect().setFill(Color.BLUEVIOLET);
      finalNode = finalNode.getFather();
    }
  }
}
