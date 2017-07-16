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
package com.idomine.business;

import javax.faces.application.FacesMessage;

import com.github.adminfaces.starter.util.Utils;
import com.idomine.myruleengine.annotations.NotifyError;
import com.idomine.myruleengine.annotations.NotifyInformation;
import com.idomine.myruleengine.annotations.NotifyWarning;

public class NotificationsHelper
{
    @NotifyInformation
    public static void info(String msg)
    {
        Utils.addDetailMessage(msg,null);
    }

    @NotifyWarning
    public static void warn(String msg)
    {
        Utils.addDetailMessage(msg,FacesMessage.SEVERITY_WARN);
    }

    @NotifyError
    public static void erro(String msg)
    {
        Utils.addDetailMessage(msg,FacesMessage.SEVERITY_ERROR);
    }
    

} 
