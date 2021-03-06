package com.github.dagwud.woodlands.game.commands.battle;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.character.ReduceHitPointsCmd;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.domain.DamageInflicted;
import com.github.dagwud.woodlands.game.domain.Fighter;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;

public class DealDamageCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final DamageInflicted damageInflicted;
  private final Fighter inflictedOn;

  public DealDamageCmd(DamageInflicted damageInflicted, Fighter inflictedOn)
  {
    this.damageInflicted = damageInflicted;
    this.inflictedOn = inflictedOn;
  }

  @Override
  public void execute()
  {
    boolean wasDead = inflictedOn.isDead();

    int totalDamageInflicted = damageInflicted.getBaseDamage() + damageInflicted.getBonusDamage();
    ReduceHitPointsCmd cmd = new ReduceHitPointsCmd(inflictedOn, totalDamageInflicted);
    CommandDelegate.execute(cmd);

    damageInflicted.setKillingBlow(inflictedOn.isDead() && !wasDead);
  }
}
