package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import model.Board;

public class Simulator {
  private Timeline timeline;
  private Board board;

  public Simulator(Board board){
    this.board = board;
    this.timeline = new Timeline(new KeyFrame(Duration.millis(100), this::doStep));
    this.timeline.setCycleCount(Timeline.INDEFINITE);
  }

  public void doStep(ActionEvent e){
    this.board.stepAlgorithm();
  }

  public void start(){
    this.timeline.play();
  }

  public void stop(){
    this.timeline.stop();
  }
}
