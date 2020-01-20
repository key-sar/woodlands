package com.github.dagwud.woodlands.game.commands.character;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.domain.ECharacterClass;
import com.github.dagwud.woodlands.game.domain.GameCharacter;
import com.github.dagwud.woodlands.game.domain.Party;
import com.github.dagwud.woodlands.game.domain.Player;

public class CreateCharacterCmd extends AbstractCmd
{
  private final String characterName;
  private final ECharacterClass characterClass;
  private GameCharacter createdCharacter;
  private Player player;

  CreateCharacterCmd(Player player, String characterName, ECharacterClass characterClass)
  {
    this.player = player;
    this.characterName = characterName;
    this.characterClass = characterClass;
  }

  @Override
  public void execute()
  {
    GameCharacter character = new GameCharacter(player);
    character.setName(characterName);
    character.setCharacterClass(characterClass);

    InitCharacterStatsCmd cmd = new InitCharacterStatsCmd(character);
    CommandDelegate.execute(cmd);
    character.setSetupComplete(true);

    JoinPartyCmd join = new JoinPartyCmd(character, characterName);
    CommandDelegate.execute(join);

    createdCharacter = character;
  }

  public GameCharacter getCreatedCharacter()
  {
    return createdCharacter;
  }
}