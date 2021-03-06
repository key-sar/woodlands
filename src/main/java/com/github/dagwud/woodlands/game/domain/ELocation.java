package com.github.dagwud.woodlands.game.domain;

import com.github.dagwud.woodlands.game.domain.menu.*;

public enum ELocation
{
  VILLAGE_SQUARE("The Village", new VillageMenu(), true,
          "The bustle of the village square brings a reassuring comfort to weary adventurers - the only danger here is the prices charged by the local bartender.\n\n" +
                  "The square is dominated on the one side by the local tavern, from which you hear loud music and occasional raised voices.\n\n" +
                  "Facing it, within stumbling range, is the Inn. The innkeeper is a friend, and has offered to look after any items you may wish to leave behind while you're out on your adventures.\n\n" +
                  "To the north lies a path leading up to The Mountain, and another leading out to the Woodlands."),

  INN("The Inn", new InnMenu(), true, "You look around, and see... stuff which hasn't yet been written down."),

  TAVERN("The Tavern", new TavernMenu(), true, "The smell of spilled beer permeates the local tavern, and the floor crunches slightly underfoot as you walk across the dirt from dozens of well-worn shoes.\n\n" +
          "The Raven is a friendly place, where all are welcome as long as they pay for their drinks. A burly guard with a dark oversized coat stands watch, and the magic-imbued rings on a chain around his neck clearly signal this is not a man to be trifled with. \n" +
          "Apart from the guard, who looks permanently annoyed, the patrons of The Raven are all smiles - with the largest being the one plastered over the face of the barman.\n\n" +
          "\"Long journey?\" he asks jovially, though something about his demeanor suggests he’s not that interested in your journey so much as how many coins are in your pocket."),

  MOUNTAIN("The Mountain", new MountainMenu(), true, "The Mountain overlooks the Village, and is home to a variety of small creatures. As such it has become something of a proving ground, where many adventurers home their skills and practice basics.\n\n" +
          "It’s also a generally safe space as long as you’re not alone - tradition dictates that because this is a training area, any adventurer who falls in a fight will be carried back to the Village by their comrades. However, being knocked unconscious while alone does bring the risk that your body may be set upon by the inhabitants of the mountain.\n\n" +
          "As such, it’s best to travel in a group."),

  WOODLANDS("The Woodlands", new WoodlandsMenu(), false, "There's stuff to be seen."),

  DEEP_WOODS("Deep Woods", new DeepWoodsMenu(), false, "Not much is known about the deep woods..."),

  THE_GORGE("The Gorge", new GorgeMenu(), false, "Here be dragons. Dragons can't be attacked with melee weapons - only ranged ones!");


  private final String displayName;
  private final GameMenu menu;
  private final boolean autoRetreat;
  private final String lookText;

  ELocation(String displayName, GameMenu menu, boolean autoRetreat, String lookText)
  {
    this.displayName = displayName;
    this.menu = menu;
    this.autoRetreat = autoRetreat;
    this.lookText = lookText;
  }

  public String toString()
  {
    return displayName;
  }

  public GameMenu getMenu()
  {
    return menu;
  }

  public boolean isAutoRetreat()
  {
    return autoRetreat;
  }

  public String getDisplayName()
  {
    return displayName;
  }

  public String getLookText()
  {
    return lookText;
  }
}
