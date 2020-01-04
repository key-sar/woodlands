package com.github.dagwud.woodlands.game.commands.invocation;

import com.github.dagwud.woodlands.game.GameState;
import com.github.dagwud.woodlands.game.commands.natives.ActionParameterException;
import com.github.dagwud.woodlands.gson.game.ParamMappings;

import java.util.Map;

abstract class ActionInvoker
{
  abstract void verifyParameters(VariableStack parameters) throws ActionParameterException;

  final void invoke(GameState gameState, VariableStack context, Map<String, String> callParameters, ParamMappings outputMappings) throws ActionInvocationException
  {
    context.pushNewVariablesStackFrame(getActionName(), callParameters);
//    System.out.println(getActionName() + " before call: \n" + context.getCallParameters());

    Variables results = doInvoke(gameState, context, outputMappings);

    context.dropStackFrame();
    mapResults(results, context, outputMappings);
    if (results != null && !((Map<String, String>) results).isEmpty())
    {
      System.out.println(getActionName() + " after call: \n" + context);
    }
  }

  abstract String getActionName();

  abstract Variables doInvoke(GameState gameState, VariableStack context, ParamMappings outputMappings) throws ActionInvocationException;

  private static void mapResults(Variables results, VariableStack callContext, ParamMappings outputMappings)
  {
    for (Map.Entry<String, String> outputMapping : outputMappings.mappings.entrySet()) // todo is it necessary to have OM on context now?
    {
      String outputName = outputMapping.getKey();
      String mapToVariableName = outputMapping.getValue();
      String outputValue = results.get(outputName);
      //todo null handling - mapping refers to an invalid output
      callContext.setValue(mapToVariableName, outputValue);
    }

    if (null != results)
    {
      for (Map.Entry<String, String> result : ((Map<String, String>) results).entrySet())
      {
        if (VariableStack.isGlobalVariable(result.getKey()))
        {
          callContext.setValue(result.getKey(), result.getValue());
        }
      }
    }
  }
}
