package com.idomine.business;

import com.idomine.ruleengine.annotations.NotifyCustom;
import com.idomine.ruleengine.annotations.NotifyError;
import com.idomine.ruleengine.annotations.NotifyInformation;
import com.idomine.ruleengine.annotations.NotifyWarning;

public class NotificationsHelper
{
    @NotifyInformation
    public static void info(String msg)
    {
        System.out.println("<<PRIMEFACES INFO>> " + msg);
    }

    @NotifyWarning
    public static void warn(String msg)
    {
        System.out.println("<<PRIMEFACES WARN>> " + msg);
    }

    @NotifyError
    public static void erro(String msg)
    {
        System.out.println("<<PRIMEFACES ERROR>> " + msg);
    }

    @NotifyCustom
    public static void notificationContext(String msg)
    {
        System.out.println("FacesServicesUtil.receberFoco(" + msg + ");");
    }

}
