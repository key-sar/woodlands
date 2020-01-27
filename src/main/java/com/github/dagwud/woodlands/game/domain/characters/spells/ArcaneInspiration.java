package com.github.dagwud.woodlands.game.domain.characters.spells;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;

import java.util.HashMap;
import java.util.Map;

public class ArcaneInspiration extends PartySpell
{
  private static final int MANA_BOOST = 1;
  private Map<PlayerCharacter, Integer> boosted;

  public ArcaneInspiration(PlayerCharacter caster)
  {
    super("Arcane Inspiration", caster);
    boosted = new HashMap<>();
  }

  @Override
  public void cast()
  {
    for (PlayerCharacter target : getCaster().getParty().getActivePlayerCharacters())
    {
      int initial = getCaster().getStats().getMaxMana();
      target.getStats().setMaxMana(initial + MANA_BOOST);
      boosted.put(target, MANA_BOOST);

      if (target != getCaster())
      {
        SendMessageCmd cmd = new SendMessageCmd(target.getPlayedBy().getChatId(),
                getCaster().getName() + " is boosting your maximum mana by +" + MANA_BOOST);
        CommandDelegate.execute(cmd);
      }
    }
  }

  @Override
  public void expire()
  {
    for (PlayerCharacter character : boosted.keySet())
    {
      character.getStats().setMaxMana(character.getStats().getMaxMana() - MANA_BOOST);
      if (character.getStats().getMana() > character.getStats().getMaxMana())
      {
        character.getStats().setMana(character.getStats().getMaxMana());
      }

      SendMessageCmd cmd = new SendMessageCmd(character.getPlayedBy().getChatId(),
              getCaster().getName() + " is no longer boosting your maximum mana");
      CommandDelegate.execute(cmd);
    }
  }
}
