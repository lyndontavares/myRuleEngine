package com.idomine.ruleengine.exceptions;

public final class ExceptionHelper
{
    private static String RULE_ENGINE = ">>>MyRuleEgine: ";

    private ExceptionHelper()
    {
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T
    {
        throw (T) exception;
    }

    public static void throwException(Throwable exception)
    {
        ExceptionHelper.<RuntimeException> throwException(exception, null);
    }

    public static void myRuleReturnTypeException(String message)
    {
        String messageTypeError = RULE_ENGINE + "O tipo de retorno do método " + message
                + " notificável deve ser Ntificacao. ";
        throwException(new MyRuleReturnTypeException(messageTypeError));
    }
    
    public static void myRuleMethodNameException(String message)
    {
        String messageTypeError = RULE_ENGINE + "Nome do método " + message
                + " inválido. Use regra java para nome de método.";
        throwException(new MyRuleReturnTypeException(messageTypeError));
    }
    

}
