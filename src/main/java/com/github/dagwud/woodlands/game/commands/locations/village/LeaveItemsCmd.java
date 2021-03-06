package com.github.dagwud.woodlands.game.commands.locations.village;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;

public class LeaveItemsCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final PlayerCharacter character;

  public LeaveItemsCmd(PlayerCharacter character)
  {
    this.character = character;
  }

  @Override
  public void execute()
  {
    StringBuilder response = new StringBuilder();
    if (character.getCarrying().getCarriedLeft() != null)
    {
      response.append("You drop a ").append(character.getCarrying().getCarriedLeft().getName()).append(" from your left hand. ");
      character.getCarrying().setCarriedLeft(null);
    }

    if (character.getCarrying().getCarriedRight() != null)
    {
      response.append("You drop a ").append(character.getCarrying().getCarriedRight().getName()).append(" from your right hand. ");
      character.getCarrying().setCarriedRight(null);
    }

    if (response.length() != 0)
    {
      CommandDelegate.execute(new SendMessageCmd(character.getPlayedBy().getChatId(), response.toString()));
    }
  }
}
