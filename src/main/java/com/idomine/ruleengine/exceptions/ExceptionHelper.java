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
    public static void myRuleException(String message)
    {
        String messageTypeError = RULE_ENGINE ;
        throwException(new MyRuleGeneralException(messageTypeError));
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
        throwException(new MyRuleMethodNameException(messageTypeError));
    }

    public static void myRuleNullException(String message)
    {
        String messageTypeError = RULE_ENGINE + "Objeto não pode ser null: " + message;
        throwException(new MyRuleNullException(messageTypeError));
    }

    public static void checkNull(final Object o, String message)
    {
        if (o == null)
        {
            myRuleNullException(message);
        }
    }

    public static void checkIsTrue(Boolean logic, String message)
    {
        if (!logic)
        {
            myRuleException(message);
        }
    }
    public static void checkIsFalse(Boolean logic, String message)
    {
        if (logic)
        {
            myRuleException(message);
        }
    }


}
