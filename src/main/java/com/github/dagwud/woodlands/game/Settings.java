package com.github.dagwud.woodlands.game;

import com.amazonaws.regions.Regions;

import java.math.BigDecimal;

public class Settings
{
  public static final BigDecimal DEFAULT_PERCENT_CHANCE_OF_ENCOUNTER = new BigDecimal("50");
  public static final int DELAY_BETWEEN_ENCOUNTERS_MS = 15_000;
  public static final int DELAY_BETWEEN_ROUNDS_MS = 15_000;
  public static final long SOBER_UP_DELAY_MS = 60_000;
  public static final long SHORT_REST_DURATION_MS = 60_000 * 5;

  public static final int ADMIN_CHAT = 750694421;

  public static final String S3_BUCKET_NAME = "woodlands-state";
  public static final Regions S3_REGION = Regions.EU_WEST_2;

  public static final String AUTO_JOIN_PARTY_NAME = "Hmmm";

  public static final int MOUNTAIN_MIN_DIFFICULTY = 0;
  public static final int MOUNTAIN_MAX_DIFFICULTY = 2;

  public static final int WOODLANDS_MIN_DIFFICULTY = 2;
  public static final int WOODLANDS_MAX_DIFFICULTY = 6;

  public static final int DEEP_WOODS_MIN_DIFFICULTY = 7;
  public static final int DEEP_WOODS_MAX_DIFFICULTY = 9;

  // Any difficulty - but only dragons:
  public static final int THE_GORGE_MIN_DIFFICULTY = Integer.MIN_VALUE;
  public static final int THE_GORGE_MAX_DIFFICULTY = Integer.MAX_VALUE;
}
