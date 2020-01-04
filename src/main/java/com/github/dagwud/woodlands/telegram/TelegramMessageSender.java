package com.github.dagwud.woodlands.telegram;

import java.io.IOException;
import java.util.Map;

public class TelegramMessageSender extends TelegramHelper
{
  public static void sendMessage(int chatId, String message) throws IOException
  {
    Map<String, String> params = buildUrlParams(chatId, message);
    callTelegram("sendMessage", params);
  }

}