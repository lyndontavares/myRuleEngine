package com.idomine.business;

import com.idomine.myruleengine.annotations.NotifyCustom;
import com.idomine.myruleengine.annotations.NotifyError;
import com.idomine.myruleengine.annotations.NotifyInformation;
import com.idomine.myruleengine.annotations.NotifyWarning;

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
