package com.github.dagwud.woodlands.game.commands.start;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.PlayerState;
import com.github.dagwud.woodlands.game.commands.ShowHelpCmd;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;

public class StartCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final int chatId;
  private final PlayerState playerState;

  public StartCmd(PlayerState playerState, int chatId)
  {
    super(new NoActiveCharacterPrerequisite(playerState.getPlayer()));
    this.playerState = playerState;
    this.chatId = chatId;
  }

  @Override
  public void execute()
  {
    ShowHelpCmd cmd = new ShowHelpCmd(chatId);
    CommandDelegate.execute(cmd);
  }
}
