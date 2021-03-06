package com.github.dagwud.woodlands.game.commands.core;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.GameStatesRegistry;
import com.github.dagwud.woodlands.game.PlayerState;
import com.github.dagwud.woodlands.game.domain.stats.Stats;
import com.github.dagwud.woodlands.game.messaging.MessagingFactory;

import java.io.IOException;

public class SendMessageCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final int chatId;
  private final String message;
  private final String replyMarkup;

  public SendMessageCmd(int chatId, String message)
  {
    this(chatId, message, null);
  }

  SendMessageCmd(int chatId, String message, String replyMarkup)
  {
    this.chatId = chatId;
    this.message = message;
    this.replyMarkup = replyMarkup;
  }

  @Override
  public void execute() throws IOException
  {
    PlayerState currentPlayerStateLookup = GameStatesRegistry.lookup(chatId);

    String newMessage = message;
    if (currentPlayerStateLookup.getPlayer().getPlayerState() != null)
    {
      if (currentPlayerStateLookup.getPlayer().getActiveCharacter() != null)
      {
        Stats stats = currentPlayerStateLookup.getPlayer().getActiveCharacter().getStats();

        if (stats != null)
        {
          DrunkUpMessageCmd cmd = new DrunkUpMessageCmd(newMessage, stats.getDrunkeness());
          CommandDelegate.execute(cmd);
          newMessage = cmd.getMessage();
        }
      }
    }
    MessagingFactory.create().sender().sendMessage(chatId, newMessage, replyMarkup);
  }

  @Override
  public String toString()
  {
    return "SendMessageCmd{" +
            "chatId=" + chatId +
            ", message='" + message + '\'' +
            '}';
  }
}
