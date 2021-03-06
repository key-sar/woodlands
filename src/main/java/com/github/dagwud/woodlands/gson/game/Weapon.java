package com.github.dagwud.woodlands.gson.game;

import com.github.dagwud.woodlands.game.domain.Fighter;
import com.github.dagwud.woodlands.game.domain.Item;
import com.google.gson.annotations.SerializedName;

public class Weapon extends Item
{
  private static final long serialVersionUID = 1L;

  private static final String MELEE_ICON = "\u2694"; // crossed swords
  private static final String RANGED_ICON = "\ud83c\udff9"; // bow and arrow

  public String name;

  public Damage damage;

  public boolean ranged;

  @SerializedName(value = "custom_icon")
  public String customIcon;

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public String getIcon()
  {
    if (customIcon != null)
    {
      return customIcon;
    }
    return ranged ? RANGED_ICON : MELEE_ICON;
  }

  public String summary(Fighter carrier, boolean includeName)
  {
    return (includeName ? getName() + " " : "") + getIcon() + statsSummary(carrier);
  }

  @Override
  public String statsSummary(Fighter carrier)
  {
    int bonusDamage = carrier.getStats().getWeaponBonusDamage(this);

    String damageText = damage.determineAverageRoll();
    if (bonusDamage != 0)
    {
      damageText += " +" + bonusDamage;
    }
    return damageText;
  }

}
