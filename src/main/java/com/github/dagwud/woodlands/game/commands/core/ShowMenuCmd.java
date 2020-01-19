package com.github.dagwud.woodlands.game.commands.core;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.GameState;
import com.github.dagwud.woodlands.game.domain.menu.GameMenu;

public class ShowMenuCmd extends AbstractCmd
{
  private final GameMenu menu;
  private final GameState gameState;

  public ShowMenuCmd(GameMenu menu, GameState gameState)
  {
    this.menu = menu;
    this.gameState = gameState;
  }

  @Override
  public void execute()
  {
    ChoiceCmd cmd = new ChoiceCmd(gameState.getPlayer().getChatId(), menu.getPrompt(), menu.getOptions());
    CommandDelegate.execute(cmd);
    gameState.setCurrentMenu(menu);
  }
}