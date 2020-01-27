package com.github.dagwud.woodlands.game.domain;

public abstract class NonPlayerCharacter extends GameCharacter
{
  private final Player ownedBy;

  NonPlayerCharacter(Player ownedBy)
  {
    this.ownedBy = ownedBy;
  }

  public boolean isActive()
  {
    return getStats().getState() == EState.ALIVE;
  }
}