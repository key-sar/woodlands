package com.github.dagwud.woodlands.game.commands.character;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.battle.DeathCmd;
import com.github.dagwud.woodlands.game.commands.battle.KnockUnconsciousCmd;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.domain.IFighter;

public class ReduceHitPointsCmd extends AbstractCmd
{
  private final IFighter character;
  private final int reduceBy;

  public ReduceHitPointsCmd(IFighter character, int reduceBy)
  {
    this.character = character;
    this.reduceBy = reduceBy;
  }

  @Override
  public void execute()
  {
    int newHP = character.getStats().getHitPoints() - reduceBy;
    character.getStats().setHitPoints(newHP);

    if (character.getStats().getHitPoints() <= 0)
    {
      if (character.getStats().getHitPoints() < -character.getStats().getMaxHitPoints())
      {
        // instant death:
        DeathCmd cmd = new DeathCmd(character);
        CommandDelegate.execute(cmd);
      }
      else
      {
        // unconscious:
        KnockUnconsciousCmd cmd = new KnockUnconsciousCmd(character);
        CommandDelegate.execute(cmd);
      }
      character.getStats().setHitPoints(0);
    }
  }
}
