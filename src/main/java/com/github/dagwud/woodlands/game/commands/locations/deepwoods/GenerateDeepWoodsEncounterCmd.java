package com.github.dagwud.woodlands.game.commands.locations.deepwoods;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.PlayerState;
import com.github.dagwud.woodlands.game.Settings;
import com.github.dagwud.woodlands.game.commands.battle.GenerateEncounterCmd;
import com.github.dagwud.woodlands.game.commands.core.RunLaterCmd;
import com.github.dagwud.woodlands.game.domain.ELocation;
import com.github.dagwud.woodlands.gson.game.Creature;

public class GenerateDeepWoodsEncounterCmd extends GenerateEncounterCmd
{
  private static final long serialVersionUID = 1L;

  GenerateDeepWoodsEncounterCmd(PlayerState playerState)
  {
    super(playerState, ELocation.DEEP_WOODS, Settings.DEEP_WOODS_MIN_DIFFICULTY, Settings.DEEP_WOODS_MAX_DIFFICULTY, Creature.CREATURE_TYPE_NORMAL);
  }

  public void scheduleNextEncounter()
  {
    RunLaterCmd nextEncounter = new RunLaterCmd(Settings.DELAY_BETWEEN_ENCOUNTERS_MS, new GenerateDeepWoodsEncounterCmd(getPlayerState()));
    CommandDelegate.execute(nextEncounter);
  }
}
