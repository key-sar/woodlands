package com.github.dagwud.woodlands.game.domain;

import com.github.dagwud.woodlands.game.log.Logger;

public abstract class PlayerCharacter extends GameCharacter
{
  private static final long serialVersionUID = 1L;
  private final Player playedBy;
  private final ECharacterClass characterClass;
  private boolean setupComplete;

  public PlayerCharacter(Player playedBy, ECharacterClass characterClass)
  {
    this.playedBy = playedBy;
    this.characterClass = characterClass;
  }

  public ECharacterClass getCharacterClass()
  {
    return characterClass;
  }

  public void setSetupComplete(boolean setupComplete)
  {
    this.setupComplete = setupComplete;
  }

  public boolean isSetupComplete()
  {
    return setupComplete;
  }

  public int determineMaxAllowedItems()
  {
    return 7 + getStats().getLevel();
  }

  public Player getPlayedBy()
  {
    return playedBy;
  }

  public boolean isActive()
  {
    return this == getPlayedBy().getActiveCharacter();
  }
}
