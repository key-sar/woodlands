package com.github.dagwud.woodlands.game.domain.stats;

public class ExplorerInitialStats extends InitialStats
{
  private static final long serialVersionUID = 1L;

  public ExplorerInitialStats()
  {
    shortRestDice = 8;
    initialStrength = 13;
    initialStrengthModifier = 1 + strengthBoost;
    initialAgility = 9;
    initialAgilityModifier = 1 + agilityBoost;
    initialConstitution = 14;
    constitutionBoost = 2;
    initialConstitutionModifier = 2 + constitutionBoost;
    initialIntelligenceUnused = 15;
    initialIntelligenceModifierUnused = 2;
    initialWisdomUnused = 16;
    initialWisdomModifierUnused = 3;
    initialCharismaUnused = 11;
    initialCharismaModifierUnused = 0;
    initialProficiencyBonus = 2;
    initialArmorClass = 15;
    initialInitiative = 1;
    initialSpeedUnused = 30;
    initialHitPoints = 10;
    initialRestDiceFaces = 8;
    weaponMasteryBonusHit.put("Mace", 3);
    weaponMasteryBonusDamage.put("Mace", 1);
  }
}
