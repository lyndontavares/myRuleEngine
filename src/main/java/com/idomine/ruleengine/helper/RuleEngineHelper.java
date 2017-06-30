package com.idomine.ruleengine.helper;

import static com.idomine.ruleengine.exceptions.ExceptionHelper.myRuleReturnTypeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.idomine.ruleengine.RuleFact;
import com.idomine.ruleengine.annotations.InjectFact;
import com.idomine.ruleengine.notification.Notificacao;

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
            if (m.getName().equals(metodo) )
            {
                
                checarTipoRetornoMetodo(m);
                
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

    private static void checarTipoRetornoMetodo(Method m)
    {
        if ( !m.getReturnType().equals(Notificacao.class))
        {
            myRuleReturnTypeException(m.getName());
        }
    }

    public static void executeNotificacao(Class<?> o, String msg, Class<?> anotacao)
    {
        Class<?> cc = null;
        try
        {
            cc = Class.forName(o.getName());
        }
        catch (ClassNotFoundException e1)
        {
            e1.printStackTrace();
        }

        for (Method m : cc.getMethods())
        {
            if (metodoContemAnotacao(m, anotacao))
            {
                try
                {
                    m.invoke(o, msg);
                    break;
                }
                catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
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
        for (Field field : classe.getClass().getDeclaredFields())
        {
            if (contemAnotacao(field, nome))
            {
                return field;
            }
        }
        return null;
    }

    private static boolean metodoContemAnotacao(Method method, Class<?> anotacao)
    {
        Annotation[] annotations = method.getDeclaredAnnotations();
        if (annotations.length != 0)
            for (int j = 0; j < annotations.length; j++)
            {
                if ((annotations[j].annotationType() == anotacao))
                {
                    return true;
                }
            }
        return false;
    }

    private static boolean contemAnotacao(Field field, String nome)
    {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations.length != 0)
            for (int j = 0; j < annotations.length; j++)
            {
                if ((annotations[j].annotationType() == InjectFact.class)
                        && ((InjectFact) annotations[j]).name()[0].equals(nome))
                {
                    return true;
                }
            }
        return false;
    }

    public static List<String> metodosNotificaveisReturnandoNotificacao(Object rule)
    {
        List<String> metodos = new ArrayList<>();
        for (Method metodo : metodosPublicos(rule))
        {
            if (metodo.getReturnType().equals(Notificacao.class))
            {
                metodos.add(metodo.getName());
            }
        }
        return metodos;
    }

    public static List<String> metodosNotificaveisRetornandoListNotificacao(Object rule)
    {
        List<String> metodos = new ArrayList<>();
        for (Method metodo : metodosPublicos(rule))
        {
            if (metodo.getReturnType().equals(List.class))
            {
                metodos.add(metodo.getName());
            }
        }
        return metodos;
    }

    public static List<String> metodosNotificaveisRetornandoBoolean(Object rule)
    {
        List<String> metodos = new ArrayList<>();
        for (Method metodo : metodosPublicos(rule))
        {
            if (metodo.getReturnType().equals(Boolean.class))
            {
                metodos.add(metodo.getName());
            }
        }
        return metodos;
    }

    public static List<Method> metodosPublicos(Object rule)
    {
        List<Method> listaRetorno = new ArrayList<>();

        for (Method m : rule.getClass().getMethods())
        {
            try
            {
                listaRetorno.add(m);
            }
            catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            }
        }
        return listaRetorno;
    }

    public static boolean metodoNotificavel(Object rule, String nomeMetodo)
    {
        return metodosNotificaveis(rule).indexOf((Object) nomeMetodo)>-1;
    }
    
    public static List<String> metodosNotificaveis(Object rule)
    {
        List<String> listaRetorno = new ArrayList<>();

        for (Method m : rule.getClass().getMethods())
        {
            if (m.getReturnType().equals(Notificacao.class))
            {
                try
                {
                    listaRetorno.add(m.getName());
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return listaRetorno;
    }

}
