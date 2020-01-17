package com.github.dagwud.woodlands.game.domain.stats;

import java.util.HashMap;
import java.util.Map;

public abstract class InitialStats
{
  int shortRestDice;
  int initialStrength;
  int initialStrengthModifier;
  int initialAgility;
  int initialAgilityModifier;
  int initialConstitution;
  int initialConstitutionModifier;
  int initialIntelligenceUnused;
  int initialIntelligenceModifierUnused;
  int initialWisdomUnused;
  int initialWisdomModifierUnused;
  int initialCharismaUnused;
  int initialCharismaModifierUnused;
  int initialProficiencyBonus;
  int initialArmorClass;
  int initialInitiative;
  int initialSpeedUnused;
  int initialHitPoints;
  String initialHitDice;

  // todo map string -> enum for weapon
  Map<String, Integer> weaponMasteryBonusHit;
  Map<String, Integer> weaponMasteryBonusDamage;

  InitialStats()
  {
    weaponMasteryBonusHit = new HashMap<>();
    weaponMasteryBonusDamage = new HashMap<>();
  }

  public int getShortRestDice()
  {
    return shortRestDice;
  }

  public int getInitialStrength()
  {
    return initialStrength;
  }

  public int getInitialStrengthModifier()
  {
    return initialStrengthModifier;
  }

  public int getInitialAgility()
  {
    return initialAgility;
  }

  public int getInitialAgilityModifier()
  {
    return initialAgilityModifier;
  }

  public int getInitialConstitution()
  {
    return initialConstitution;
  }

  public int getInitialConstitutionModifier()
  {
    return initialConstitutionModifier;
  }

  public int getInitialIntelligenceUnused()
  {
    return initialIntelligenceUnused;
  }

  public int getInitialIntelligenceModifierUnused()
  {
    return initialIntelligenceModifierUnused;
  }

  public int getInitialWisdomUnused()
  {
    return initialWisdomUnused;
  }

  public int getInitialWisdomModifierUnused()
  {
    return initialWisdomModifierUnused;
  }

  public int getInitialCharismaUnused()
  {
    return initialCharismaUnused;
  }

  public int getInitialCharismaModifierUnused()
  {
    return initialCharismaModifierUnused;
  }

  public int getInitialProficiencyBonus()
  {
    return initialProficiencyBonus;
  }

  public int getInitialArmorClass()
  {
    return initialArmorClass;
  }

  public int getInitialInitiative()
  {
    return initialInitiative;
  }

  public int getInitialSpeedUnused()
  {
    return initialSpeedUnused;
  }

  public int getInitialHitPoints()
  {
    return initialHitPoints;
  }

  public String getInitialHitDice()
  {
    return initialHitDice;
  }

  public Map<String, Integer> getWeaponMasteryBonusHit()
  {
    return weaponMasteryBonusHit;
  }

  public Map<String, Integer> getWeaponMasteryBonusDamage()
  {
    return weaponMasteryBonusDamage;
  }
}