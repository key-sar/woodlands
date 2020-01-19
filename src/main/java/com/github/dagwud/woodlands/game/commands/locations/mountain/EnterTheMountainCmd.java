package com.github.dagwud.woodlands.game.commands.locations.mountain;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.GameState;
import com.github.dagwud.woodlands.game.Settings;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.core.RunLaterCmd;

public class EnterTheMountainCmd extends AbstractCmd
{
  private final GameState gameState;

  public EnterTheMountainCmd(GameState gameState)
  {
    this.gameState = gameState;
  }

  @Override
  public void execute()
  {
    RunLaterCmd cmd = new RunLaterCmd(Settings.DELAY_BETWEEN_ENCOUNTERS_MS,
            new GenerateMountainEncounterCmd(gameState));
    CommandDelegate.execute(cmd);
  }
}
