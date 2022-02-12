package model;
public enum Movement{
  LEFT(10),
  RIGHT(10),
  UP(10),
  DOWN(10),
  UP_LEFT(14),
  UP_RIGHT(14),
  DOWN_LEFT(14),
  DOWN_RIGHT(14);

  private int costo;
  private Movement(int costo){
    this.costo = costo;
  }

  public int getCosto(){
    return costo;
  }
}
