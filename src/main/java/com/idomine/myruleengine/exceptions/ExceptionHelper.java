package com.idomine.myruleengine.exceptions;

public final class ExceptionHelper
{
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
        throwException(new MyRuleGeneralException(message));
    }

    public static void myRuleReturnTypeException(String message)
    {
        String messageTypeError ="O tipo de retorno do método " + message
                + " notificável deve ser Ntificacao. ";
        throwException(new MyRuleReturnTypeException(messageTypeError));
    }

    public static void myRuleMethodNameException(String message)
    {
        String messageTypeError = "Nome do método [" + message
                + "] inválido. Use regra da liguagem Java para nome de método.";
        throwException(new MyRuleMethodNameException(messageTypeError));
    }

    public static void myRuleMethodNameRepetitionException(String message)
    {
        String messageTypeError = "metodoNome [" + message  + "]  já adicionado.";
        throwException(new MyRuleMethodNameRepetitionException(messageTypeError));
    }

    public static void myRuleNullException(String message)
    {
        String messageTypeError =  "Objeto não pode ser null: " + message;
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
