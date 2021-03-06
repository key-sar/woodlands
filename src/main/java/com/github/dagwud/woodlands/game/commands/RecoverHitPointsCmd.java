package com.github.dagwud.woodlands.game.commands;

import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.domain.EState;
import com.github.dagwud.woodlands.game.domain.Fighter;

public class RecoverHitPointsCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final Fighter target;
  private final int hitPointsRecovered;

  public RecoverHitPointsCmd(Fighter target, int hitPointsRecovered)
  {
    this.target = target;
    this.hitPointsRecovered = hitPointsRecovered;
  }

  @Override
  public void execute()
  {
    target.getStats().setHitPoints(target.getStats().getHitPoints() + hitPointsRecovered);
    if (target.getStats().getHitPoints() > 0)
    {
      target.getStats().setState(EState.ALIVE);
    }
  }
}
