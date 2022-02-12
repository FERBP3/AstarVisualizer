package view;

import controller.Simulator;
import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import model.Board;

public class Toolbar {

  private Board board;
  private Simulator sim;
  private IntegerProperty turn;
  public Toolbar(Board board, Simulator sim, IntegerProperty turn){
    this.board = board;
    this.sim = sim;
    this.turn = turn;
  }

  public TilePane getView(){
      EventHandler<MouseEvent> scanButtonHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            board.scanSquares();
        }
      };

      EventHandler<MouseEvent> startButtonHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            sim.start();
        }
      };

      EventHandler<MouseEvent> cleanButtonHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            board.clean();
            turn.set(0);
        }
      };

      EventHandler<MouseEvent> stopButtonHandler = new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent e) {
            sim.stop();
        }
      };

      TilePane buttonGroup = new TilePane();
      Button scanButton = new Button("Scan world");
      Button cleanButton = new Button("Clean board");
      Button startAlgorithm = new Button("Run A*");
      Button stopAlgorithm = new Button("Stop");

      scanButton.addEventHandler(MouseEvent.MOUSE_CLICKED, scanButtonHandler);
      cleanButton.addEventHandler(MouseEvent.MOUSE_CLICKED, cleanButtonHandler);
      startAlgorithm.addEventHandler(MouseEvent.MOUSE_CLICKED, startButtonHandler);
      stopAlgorithm.addEventHandler(MouseEvent.MOUSE_CLICKED, stopButtonHandler);

      buttonGroup.getChildren().add(scanButton);
      buttonGroup.getChildren().add(cleanButton);
      buttonGroup.getChildren().add(startAlgorithm);
      buttonGroup.getChildren().add(stopAlgorithm);

      return buttonGroup;
  }
}
