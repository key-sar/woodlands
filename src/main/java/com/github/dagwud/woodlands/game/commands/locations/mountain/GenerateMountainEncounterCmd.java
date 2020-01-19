package com.github.dagwud.woodlands.game.commands.locations.mountain;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.GameState;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.core.ChanceCalculatorCmd;
import com.github.dagwud.woodlands.game.commands.core.RunLaterCmd;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.creatures.CreaturesCacheFactory;
import com.github.dagwud.woodlands.game.domain.ELocation;
import com.github.dagwud.woodlands.game.domain.Encounter;
import com.github.dagwud.woodlands.game.domain.GameCharacter;
import com.github.dagwud.woodlands.gson.game.Creature;

import java.math.BigDecimal;

public class GenerateMountainEncounterCmd extends AbstractCmd
{
  private final GameState gameState;

  GenerateMountainEncounterCmd(GameState gameState)
  {
    this.gameState = gameState;
  }

  @Override
  public void execute()
  {
    // You've left the mountain; encounter no longer happens:
    if (gameState.getActiveCharacter().getLocation() != ELocation.MOUNTAIN)
    {
      return;
    }

    // There's already an encounter in progress; don't start another one:
    if (gameState.getActiveEncounter() != null)
    {
      scheduleNextEncounter();
      return;
    }

    ChanceCalculatorCmd chance = new ChanceCalculatorCmd(new BigDecimal("75"));
    CommandDelegate.execute(chance);

    if (!chance.getResult())
    {
      SendMessageCmd cmd = new SendMessageCmd(gameState.getPlayer().getChatId(), "Time passes. You keep moving. Nothing interesting happens.");
      CommandDelegate.execute(cmd);
      scheduleNextEncounter();
      return;
    }

    Encounter encounter = startEncounter();
    gameState.setActiveEncounter(encounter);

    EncounterRoundCmd cmd = new EncounterRoundCmd(gameState.getPlayer().getChatId(), encounter);
    CommandDelegate.execute(cmd);

    scheduleNextEncounter();
  }

  private Encounter startEncounter()
  {
    Encounter encounter = createEncounter(gameState.getActiveCharacter());

    String message = "You encountered a " + encounter.getEnemy().name + " (L" + encounter.getEnemy().level + ")";
    SendMessageCmd msg = new SendMessageCmd(gameState.getPlayer().getChatId(), message);
    CommandDelegate.execute(msg);
    return encounter;
  }

  private Encounter createEncounter(GameCharacter host)
  {
    Creature creature = CreaturesCacheFactory.instance().getCache().pickRandom();

    Encounter encounter = new Encounter();
    encounter.setHost(host);
    encounter.setEnemy(creature);
    return encounter;
  }

  private void scheduleNextEncounter()
  {
    RunLaterCmd nextEncounter = new RunLaterCmd(30000, new GenerateMountainEncounterCmd(gameState));
    CommandDelegate.execute(nextEncounter);
  }
}