package com.github.dagwud.woodlands.game.commands;

import com.github.dagwud.woodlands.game.GameStatesRegistry;
import com.github.dagwud.woodlands.game.commands.admin.*;
import com.github.dagwud.woodlands.game.commands.character.CastSpellPromptCmd;
import com.github.dagwud.woodlands.game.commands.character.ChangeNamePromptCmd;
import com.github.dagwud.woodlands.game.commands.character.LevelUpCmd;
import com.github.dagwud.woodlands.game.commands.character.SwitchCharacterPromptCmd;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.inventory.GiveItemCmd;
import com.github.dagwud.woodlands.game.commands.inventory.InventoryCmd;
import com.github.dagwud.woodlands.game.commands.locations.LookCmd;
import com.github.dagwud.woodlands.game.commands.locations.MoveToLocationCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.BuyDrinksCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.LeaveItemsCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.RetrieveItemsCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.ShortRestCmd;
import com.github.dagwud.woodlands.game.commands.logs.ShowLogsCmd;
import com.github.dagwud.woodlands.game.commands.start.PlayerSetupCmd;
import com.github.dagwud.woodlands.game.commands.start.StartCmd;
import com.github.dagwud.woodlands.game.domain.ELocation;
import com.github.dagwud.woodlands.game.domain.PlayerCharacter;

import java.util.HashMap;
import java.util.Map;

public enum ECommand
{
  HELP("/help", false, (character, chatId) -> new ShowHelpCmd(chatId)),
  SAVE("/save", false, (character, chatId) -> new PersistWorldCmd()),
  START("/start", false, (character, chatId) -> new StartCmd(GameStatesRegistry.lookup(chatId), chatId)),
  NEW("/new", false, (character, chatId) -> new PlayerSetupCmd(character == null ? null : character.getPlayedBy(), chatId)),
  ME("/me", false, (character, chatId) -> new ShowCharacterInfoCmd(chatId, character)),
  PARTY("/party", false, (character, chatId) -> new ShowPartyInfoCmd(chatId, character)),
  INVENTORY("/inv", false, (character, chatId) -> new InventoryCmd(chatId, character)),
  LOOK("/look", false, (character, chatId) -> new LookCmd(chatId, character)),
  GIVE("/give", false, (character, chatId) -> new GiveItemCmd(character.getPlayedBy())),

  PARTY_LIST("/parties", false, (character, chatId) -> new ListPartiesCmd(chatId)),
  CHEAT_LEVEL_UP_TEST("/levelup", false, (character, chatId) -> new LevelUpCmd(chatId, character)),
  CHEAT_FULL_HEAL("/heal", false, (character, chatId) -> new FullHealCmd(chatId, character)),
  KILL("/kill", false, (character, chatId) -> new KillPromptCmd(chatId, character)),
  RESURRECT("/resurrect", false, (character, chatId) -> new ResurrectPromptCmd(chatId, character)),
  REMOVE("/rm", false, (character, chatId) -> new RemovePromptCmd(chatId, character)),
  SETPARTY("/setparty", false, (character, chatId) -> new AdminChangePartyCmd(chatId, character)),
  RESET("/reset", false, (character, chatId) -> new ResetCmd(chatId)),
  LOGS("/log", false, (character, chatId) -> new ShowLogsCmd(chatId)),
  CREATURE_CACHE("/cachecreature", false, ((character, chatId) -> new InvalidateCreatureCacheCmd(chatId))),

  THE_INN("The Inn", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.INN)),
  THE_TAVERN("The Tavern", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.TAVERN)),
  THE_VILLAGE("The Village", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.VILLAGE_SQUARE)),
  VILLAGE_SQUARE("Village Square", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.VILLAGE_SQUARE)),
  THE_MOUNTAIN("The Mountain", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.MOUNTAIN)),
  THE_WOODLANDS("The Woodlands", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.WOODLANDS)),
  DEEP_WOODS("Deep Woods", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.DEEP_WOODS)),
  THE_GORGE("The Gorge", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.THE_GORGE)),

  CAST_A_SPELL("Cast Spell", true, (character, chatId) -> new CastSpellPromptCmd(chatId, character)),

  JOIN("Join a Party", false, (character, chatId) -> new PromptJoinPartyCmd(character)),
  BUY_DRINKS("Buy Drinks", true, (character, chatId) -> new BuyDrinksCmd(chatId, character)),
  SHORT_REST("Short Rest", true, (character, chatId) -> new ShortRestCmd(chatId, character)),
  LEAVE_ITEMS("Leave Items", true, (character, chatId) -> new LeaveItemsCmd(character)),
  RETRIEVE_ITEMS("Retrieve Items", true, (character, chatId) -> new RetrieveItemsCmd(character)),
  SWITCH_CHARACTERS("Switch Characters", true, (character, chatId) -> new SwitchCharacterPromptCmd(character.getPlayedBy())),
  CHANGE_NAME("Change Name", true, (character, chatId) -> new ChangeNamePromptCmd(character.getPlayedBy())),
  ;


  ECommand(String name, boolean menuCmd, ICommandBuilder commandBuilder)
  {
    this.name = name;
    this.menuCmd = menuCmd;
    this.commandBuilder = commandBuilder;
  }

  private static final Map<String, ECommand> COMMANDS = new HashMap<>();

  static
  {
    for (ECommand value : ECommand.values())
    {
      COMMANDS.put(value.name, value);
    }
  }

  public static ECommand by(String name)
  {
    return COMMANDS.get(name);
  }

  private String name;
  private boolean menuCmd;
  private ICommandBuilder commandBuilder;

  public boolean isMenuCmd()
  {
    return menuCmd;
  }

  public boolean matches(String name)
  {
    return this.name.equalsIgnoreCase(name);
  }

  public AbstractCmd build(PlayerCharacter character, int chatId)
  {
    return commandBuilder.build(character, chatId);
  }

  @Override
  public String toString()
  {
    return this.name;
  }
}
