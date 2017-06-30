package com.idomine.model.rules;

import com.idomine.ruleengine.annotations.NotificacaoContext;
import com.idomine.ruleengine.annotations.NotificacaoError;
import com.idomine.ruleengine.annotations.NotificacaoInfo;
import com.idomine.ruleengine.annotations.NotificacaoWarn;

public class NotificacaoHelper
{
    @NotificacaoInfo
    public static void info(String msg)
    {
        System.out.println("<<PRIMEFACES INFO>> " + msg);
    }

    @NotificacaoWarn
    public static void warn(String msg)
    {
        System.out.println("<<PRIMEFACES WARN>> " + msg);
    }

    @NotificacaoError
    public static void erro(String msg)
    {
        System.out.println("<<PRIMEFACES ERROR>> " + msg);
    }

    @NotificacaoContext
    public static void notificationContext(String msg)
    {
        System.out.println("FacesServicesUtil.receberFoco(" + msg + ");");
    }

}
