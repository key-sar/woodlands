package com.github.dagwud.woodlands.game.commands.inventory;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.domain.GameCharacter;
import com.github.dagwud.woodlands.gson.game.Weapon;

public class DropItemCmd extends AbstractCmd
{
  private final GameCharacter character;
  private final int chatId;
  private final String dropIndex;

  public DropItemCmd(GameCharacter character, int chatId, String dropIndex)
  {
    this.character = character;
    this.chatId = chatId;
    this.dropIndex = dropIndex;
  }

  @Override
  public void execute()
  {
    Weapon dropped = null;
    if (dropIndex.equals("L"))
    {
      dropped = character.getCarrying().getCarriedLeft();
      character.getCarrying().setCarriedLeft(null);
    }
    else if (dropIndex.equals("R"))
    {
      dropped = character.getCarrying().getCarriedRight();
      character.getCarrying().setCarriedRight(null);
    }
    else
    {
      try
      {
        int index = Integer.parseInt(dropIndex);
        dropped = character.getCarrying().getCarriedInactive().get(index);
        character.getCarrying().getCarriedInactive().remove(index);
      }
      catch (NumberFormatException | IndexOutOfBoundsException e)
      {
        dropped = null;
      }
    }

    if (dropped != null)
    {
      SendMessageCmd cmd = new SendMessageCmd(chatId, "You dropped " + dropped.name);
      CommandDelegate.execute(cmd);
    }
    else
    {
      SendMessageCmd cmd = new SendMessageCmd(chatId, "That's not a valid option");
      CommandDelegate.execute(cmd);
    }
  }
}