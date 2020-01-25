package com.github.dagwud.woodlands.game.domain.characters;

import com.github.dagwud.woodlands.game.domain.ECharacterClass;
import com.github.dagwud.woodlands.game.domain.GameCharacter;
import com.github.dagwud.woodlands.game.domain.Player;
import com.github.dagwud.woodlands.game.domain.characters.spells.Spell;

import java.util.Collection;
import java.util.Collections;

public class Trickster extends GameCharacter
{
  Trickster(Player playedBy)
  {
    super(playedBy, ECharacterClass.TRICKSTER);
  }

  @Override
  public Collection<? extends Spell> castPassives()
  {
    return Collections.emptyList();
  }
}
