package com.github.dagwud.woodlands.game.commands;

import com.github.dagwud.woodlands.game.GameStatesRegistry;
import com.github.dagwud.woodlands.game.PlayerState;
import com.github.dagwud.woodlands.game.commands.core.AbstractCmd;
import com.github.dagwud.woodlands.game.commands.locations.MoveToLocationCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.BuyDrinksCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.RetrieveItemsCmd;
import com.github.dagwud.woodlands.game.commands.locations.village.ShortRestCmd;
import com.github.dagwud.woodlands.game.commands.start.PlayerSetupCmd;
import com.github.dagwud.woodlands.game.commands.start.StartCmd;
import com.github.dagwud.woodlands.game.domain.ELocation;
import com.github.dagwud.woodlands.game.domain.GameCharacter;

import java.util.HashMap;
import java.util.Map;

public enum ECommand
{
    HELP("/help", false, (character, chatId) -> new ShowHelpCmd(chatId)),
    START("/start", false, (character, chatId) -> new StartCmd(GameStatesRegistry.lookup(chatId), chatId)),
    NEW("/new", false, (character, chatId) -> new PlayerSetupCmd(character.getPlayedBy())),
    ME("/me", false, (character, chatId) -> new ShowCharacterInfoCmd(chatId, character)),

    THE_INN("The Inn", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.INN)),
    THE_TAVERN("The Tavern", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.TAVERN)),
    THE_VILLAGE("The Village", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.VILLAGE_SQUARE)),
    VILLAGE_SQUARE("Village Square", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.VILLAGE_SQUARE)),
    THE_MOUNTAIN("The Mountain", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.MOUNTAIN)),
    THE_WOODLANDS("The Woodlands", true, (character, chatId) -> new MoveToLocationCmd(character, ELocation.WOODLANDS)),

    BUY_DRINKS("Buy Drinks", true, (character, chatId) -> new BuyDrinksCmd(chatId, character)),
    SHORT_REST("Short Rest", true, (character, chatId) -> new ShortRestCmd(chatId, character)),
    RETRIEVE_ITEMS("Retrieve Items", true, (character, chatId) -> new RetrieveItemsCmd(character)),
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

    public AbstractCmd build(GameCharacter character, int chatId)
    {
        return commandBuilder.build(character, chatId);
    }
}