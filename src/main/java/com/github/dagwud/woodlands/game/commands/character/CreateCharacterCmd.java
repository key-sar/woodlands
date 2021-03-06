package com.github.dagwud.woodlands.game.commands.character;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.domain.ECharacterClass;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;
import com.github.dagwud.woodlands.game.domain.Player;
import com.github.dagwud.woodlands.game.domain.characters.GameCharacterFactory;

public class CreateCharacterCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private final String characterName;
  private final ECharacterClass characterClass;
  private PlayerCharacter createdCharacter;
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
    PlayerCharacter character = GameCharacterFactory.create(characterClass, player);
    character.setName(characterName);

    InitCharacterStatsCmd cmd = new InitCharacterStatsCmd(character);
    CommandDelegate.execute(cmd);
    character.setSetupComplete(true);

    // Join a private party for just this character by default:
    JoinPartyCmd join = new JoinPartyCmd(character, "_" + characterName);
    CommandDelegate.execute(join);

    createdCharacter = character;
  }

  PlayerCharacter getCreatedCharacter()
  {
    return createdCharacter;
  }
}
