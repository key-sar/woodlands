package com.github.dagwud.woodlands.game.domain.stats;

import com.github.dagwud.woodlands.game.domain.EState;
import com.github.dagwud.woodlands.gson.game.Weapon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Stats implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int level;
  private int experience;
  private int hitPoints;
  private int maxHitPoints;
  private int mana;
  private Stat maxMana = new Stat();
  private Stat strength = new Stat();
  private Stat agility = new Stat();
  private Stat constitution = new Stat();
  private int restPoints;
  private int restPointsMax;
  private int restDiceFace;
  private int hitBoost;

  private double damageMultiplier = 1;
  private int criticalStrikeChanceBonus;

  private Map<String, Integer> weaponBonusHit = new HashMap<>();
  private Map<String, Integer> weaponBonusDamage = new HashMap<>();
  private EState state;

  // thus endeth the serious stats and begin the fun stats
  private int drunkeness;
  private int defenceRatingBoost;

  public int getLevel()
  {
    return level;
  }

  public void setLevel(int level)
  {
    this.level = level;
  }

  public int getHitPoints()
  {
    return hitPoints;
  }

  public void setHitPoints(int hitPoints)
  {
    this.hitPoints = hitPoints;
  }

  public int getMaxHitPoints()
  {
    return maxHitPoints;
  }

  public void setMaxHitPoints(int maxHitPoints)
  {
    this.maxHitPoints = maxHitPoints;
  }

  public int getMana()
  {
    return mana;
  }

  public void setMana(int mana)
  {
    this.mana = mana;
  }

  public Stat getMaxMana()
  {
    return maxMana;
  }

  public void setMaxMana(Stat maxMana)
  {
    this.maxMana = maxMana;
  }

  public Stat getStrength()
  {
    return strength;
  }

  public void setStrength(int strength, int bonus)
  {
    this.strength = new Stat(strength, bonus);
  }

  public Stat getAgility()
  {
    return agility;
  }

  public int getAgilityModifier()
  {
    return Math.floorDiv(getAgility().total() - 10, 2);
  }

  public void setAgility(int agility, int bonus)
  {
    this.agility = new Stat(agility, bonus);
  }

  public Stat getConstitution()
  {
    return constitution;
  }

  public void setConstitution(int constitution, int bonus)
  {
    this.constitution = new Stat(constitution, bonus);
  }

  public int getWeaponBonusHit(Weapon weapon)
  {
    return weaponBonusHit.getOrDefault(weapon.getName(), 0);
  }

  public void setWeaponBonusHit(Map<String, Integer> weaponBonusHit)
  {
    this.weaponBonusHit = weaponBonusHit;
  }

  public int getWeaponBonusDamage(Weapon weapon)
  {
    return determineProficiency() + weaponBonusDamage.getOrDefault(weapon.getName(), 0);
  }

  public void setWeaponBonusDamage(Map<String, Integer> weaponBonusDamage)
  {
    this.weaponBonusDamage = weaponBonusDamage;
  }

  public Map<String, Integer> getWeaponBonusDamage()
  {
    return weaponBonusDamage;
  }

  public EState getState()
  {
    return state;
  }

  public void setState(EState state)
  {
    this.state = state;
  }

  public int getBaseDefenceRating()
  {
    return 10 + getAgilityModifier();
  }

  public int getDrunkeness()
  {
    return drunkeness;
  }

  public void setDrunkeness(int drunkeness)
  {
    this.drunkeness = drunkeness;
  }

  public int getExperience()
  {
    return experience;
  }

  public void setExperience(int experience)
  {
    this.experience = experience;
  }

  public int getCriticalStrikeChanceBonus()
  {
    return criticalStrikeChanceBonus;
  }

  public void setCriticalStrikeChanceBonus(int criticalStrikeChanceBonus)
  {
    this.criticalStrikeChanceBonus = criticalStrikeChanceBonus;
  }

  private int determineProficiency()
  {
    return Math.floorDiv(getLevel() + 8, 4);
  }

  public int getRestPoints()
  {
    return restPoints;
  }

  public void setRestPoints(int restPoints)
  {
    this.restPoints = restPoints;
  }

  public int getRestPointsMax()
  {
    return restPointsMax;
  }

  public void setRestPointsMax(int restPointsMax)
  {
    this.restPointsMax = restPointsMax;
  }

  public int getRestDiceFace()
  {
    return restDiceFace;
  }

  public void setRestDiceFace(int restDiceFace)
  {
    this.restDiceFace = restDiceFace;
  }

  public int getConstitutionModifier()
  {
    return Math.floorDiv(getConstitution().total() - 10, 2);
  }

  public int getHitBoost()
  {
    return hitBoost;
  }

  public void setHitBoost(int hitBoost)
  {
    this.hitBoost = hitBoost;
  }

  public int determineHitChanceBoost()
  {
    return hitBoost - (getDrunkeness() / 2);
  }

  public int determineDrunkenStrength()
  {
    return Math.min(getDrunkeness() / 2, 4);
  }

  public double getDamageMultiplier()
  {
    return damageMultiplier;
  }

  public void setDamageMultiplier(double damageMultiplier)
  {
    this.damageMultiplier = damageMultiplier;
  }

  public int getDefenceRatingBoost()
  {
    return defenceRatingBoost;
  }

  public void setDefenceRatingBoost(int defenceRatingBoost)
  {
    this.defenceRatingBoost = defenceRatingBoost;
  }
}
