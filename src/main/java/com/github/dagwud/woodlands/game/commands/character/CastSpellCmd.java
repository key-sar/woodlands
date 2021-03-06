package com.github.dagwud.woodlands.game.commands.character;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.core.SendMessageCmd;
import com.github.dagwud.woodlands.game.commands.prerequisites.AbleToActPrerequisite;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;
import com.github.dagwud.woodlands.game.domain.characters.spells.Spell;
import com.github.dagwud.woodlands.game.log.Logger;

public class CastSpellCmd extends AbstractCmd
{
  private static final long serialVersionUID = 1L;

  private Spell spell;

  public CastSpellCmd(Spell spell)
  {
    super(new AbleToActPrerequisite(spell.getCaster()));
    this.spell = spell;
  }

  @Override
  public void execute()
  {
    if (spell.isCast())
    {
      Logger.error("WARNING: " + spell.getSpellName() + " has already been cast");
    }

    int mana = spell.getCaster().getStats().getMana();
    int manaCost = spell.getManaCost();
    if (mana < manaCost)
    {
      if (spell.getCaster() instanceof PlayerCharacter)
      {
        PlayerCharacter character = (PlayerCharacter) spell.getCaster();
        SendMessageCmd cmd = new SendMessageCmd(character.getPlayedBy().getChatId(), "You don't have enough mana to cast " + spell.buildSpellDescription());
        CommandDelegate.execute(cmd);
      }
      return;
    }
    if (spell.cast())
    {
      spell.setCast(true);
      spell.getCaster().getStats().setMana(mana - manaCost);

      SendMessageCmd success = new SendMessageCmd(((PlayerCharacter) spell.getCaster()).getPlayedBy().getChatId(), "You cast " + spell.buildSpellDescription());
      CommandDelegate.execute(success);
    }
  }

  @Override
  public String toString()
  {
    return "CastSpellCmd{" +
            "spell=" + spell +
            '}';
  }
}
