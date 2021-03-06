package com.github.dagwud.woodlands.game.commands.invocation;

import java.util.*;

public class Variables extends HashMap<String, String>
{
  private static final long serialVersionUID = 1L;

  private final String contextName;

  public Variables()
  {
    super();
    contextName = "(anon)";
  }

  @Override
  public String toString()
  {
    return "Variables{" +
            "contextName='" + contextName + '\'' +
            '}';
  }

  @Override
  public String put(String key, String value)
  {
    //todo inline
    return super.put(key, value);
  }

}
