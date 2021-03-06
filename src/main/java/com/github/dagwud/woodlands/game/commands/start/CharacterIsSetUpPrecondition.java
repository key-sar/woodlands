package com.github.dagwud.woodlands.game.commands.start;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.CommandPrerequisite;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;

public class CharacterIsSetUpPrecondition implements CommandPrerequisite
{
  private final int chatId;
  private final PlayerCharacter character;

  public CharacterIsSetUpPrecondition(int chatId, PlayerCharacter character)
  {
    this.chatId = chatId;
    this.character = character;
  }

  @Override
  public boolean verify()
  {
    if (character != null && character.isSetupComplete())
    {
      return true;
    }
    CommandDelegate.execute(new SendMessageCmd(chatId, "You don't have a character at the moment. Try `/new` to create a character"));
    return false;
  }
}
