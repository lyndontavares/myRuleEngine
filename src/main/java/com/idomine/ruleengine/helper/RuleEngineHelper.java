package com.idomine.ruleengine.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.idomine.ruleengine.RuleFact;
import com.idomine.ruleengine.interfaces.InjectFact;

public final class RuleEngineHelper
{
    private RuleEngineHelper()
    {
    }

    public static Object execute(Object o, String metodo, Object... objects)
    {
        Object retorno = false;
        for (Method m : o.getClass().getMethods())
        {
            if (m.getName().equals(metodo))
            {
                try
                {
                    retorno = m.invoke(o);
                    break;
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return retorno;
    }

    public static void prepareFacts(Object o, String metodo, Object... objects)
    {
        for (Method m : o.getClass().getMethods())
        {
            if (m.getName().equals(metodo))
            {
                try
                {
                    m.invoke(o);
                    break;
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //
    public static void prepareFacts2(Object classe, List<RuleFact> fatos)
    {
        for (Field field : classe.getClass(). getDeclaredFields())
        {
        }
    }
    
    public static void prepareFacts(Object classe, List<RuleFact> fatos)
    {
        if (fatos != null)
            for (RuleFact fato : fatos)
            {
                atribuirFato(classe, fato);
            }
    }

    private static void atribuirFato(Object classe, RuleFact fato)
    {
        Field field = localizarAtributo(classe, fato.getNome());
        if (field != null)
        {
            try
            {
                field.setAccessible(true);
                field.set(classe, fato.getValor());
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static Field localizarAtributo(Object classe, String nome)
    {
        for (Field field : classe.getClass(). getDeclaredFields())
        {
            if(  contemAnotacao(field,nome)  )
            {
                return field;
            }
        }
        return null;
    }

    private static boolean contemAnotacao(Field field,String nome)
    {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length != 0)
            for (int j = 0; j < annotations.length; j++)
            {
                if ((annotations[j].annotationType() ==  InjectFact.class) &&  ((InjectFact) annotations[j]).name()[0].equals(nome))
                {
                    return true;
                }
            }
        return false;
    }    
    
    private static boolean contemAnotacaoIFato(Object classe)
    {
        return false;
    }
}
