/**
 * The MIT License
 *
 *  Copyright (c) 2017, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
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

    public static void myRuleCovertionException(String message)
    {
        String messageTypeError =  "Check types of facts. See @InjectFact annotation. " + message;
        throwException(new MyRuleConvertionException(messageTypeError));
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
