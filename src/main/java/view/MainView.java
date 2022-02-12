package view;

import controller.Simulator;
import model.Board;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MainView {
  public static int WIDTH = 650;
  public static int HEIGHT = 650;
  public static int startSquare = 50;
  public static int sizeSquare = 30;

  public MainView(){}

  public Group getView(){
      int numRect = ((WIDTH-2*startSquare)/sizeSquare) + 1;
      Group root = new Group();
      Board board = new Board(numRect);
      Simulator sim = new Simulator(board);
      IntegerProperty turn = new SimpleIntegerProperty(0);
      Toolbar toolbar = new Toolbar(board, sim, turn);

      root.getChildren().add(toolbar.getView());

      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            Rectangle rect = (Rectangle) e.getSource();
            if (turn.get() == 0){
              rect.setFill(Color.GREEN);
              turn.set(turn.get()+1);
            }else if (turn.get() == 1){
              rect.setFill(Color.RED);
              turn.set(turn.get()+1);
            }else{
              rect.setFill(Color.BLACK);
            }
        }
      };

      int indexRow = 0;
      for (int row=startSquare; row<=HEIGHT-startSquare; row+=sizeSquare){
        int indexCol = 0;
        for (int col=startSquare; col<=WIDTH-startSquare; col+=sizeSquare){
          Rectangle rect = new Rectangle(col, row, sizeSquare, sizeSquare);
          rect.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
          rect.setStroke(Color.BLACK);
          rect.setFill(Color.TRANSPARENT);
          board.setSquareNode(indexRow, indexCol, rect);
          root.getChildren().add(rect);
          indexCol++;
        }
        indexRow++;
      }

      return root;
  }
}
