package com.github.dagwud.woodlands.game.commands.core;

import com.github.dagwud.woodlands.game.CommandDelegate;
import com.github.dagwud.woodlands.game.Settings;
import com.github.dagwud.woodlands.game.log.Logger;

import java.util.concurrent.Callable;

public class RunScheduledCmd implements Callable<String>
{
  private final AbstractCmd cmdToRun;
  private final long delayMS;

  RunScheduledCmd(long delayMS, AbstractCmd cmdToRun)
  {
    this.delayMS = delayMS;
    this.cmdToRun = cmdToRun;
  }

  @Override
  public String call() throws Exception
  {
    try
    {
      Thread.sleep(delayMS);
      CommandDelegate.execute(cmdToRun);
      return null;
    }
    catch (Exception e)
    {
      Logger.error("WARNING: Exception in asynchronous thread; can't be thrown to caller so logging it here:");
      try
      {
        SendMessageCmd adminInfo = new SendMessageCmd(Settings.ADMIN_CHAT, "Exception in asynchronous thread");
        CommandDelegate.execute(adminInfo);
        SendMessageCmd exc = new SendMessageCmd(Settings.ADMIN_CHAT, e.toString());
        CommandDelegate.execute(exc);
      }
      catch (Exception ignore)
      {
        // oh well.
      }
      Logger.logError(e);
      throw e;
    }
  }
}
