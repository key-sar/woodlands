package com.github.dagwud.woodlands.game.domain;

public enum ELocation
{
  VILLAGE_SQUARE("The Village"),
  INN("The Inn"),
  TAVERN("The Tavern"),
  MOUNTAIN("The Mountain"),
  WOODLANDS("The Woodlands");

  private final String displayName;

  ELocation(String displayName)
  {
    this.displayName = displayName;
  }

  public String toString()
  {
    return displayName;
  }
}
