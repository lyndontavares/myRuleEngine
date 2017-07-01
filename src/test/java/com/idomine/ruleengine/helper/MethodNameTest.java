package com.idomine.ruleengine.helper;

import org.junit.Test;

import static org.junit.Assert.*;
import com.idomine.ruleengine.helper.MyRuleJavaClassHelper;

public class MethodNameTest
{

    @Test
    public void testeNomeValido()
    {

        assertTrue(MyRuleJavaClassHelper.isJavaMethodName("nomeValido"));
        assertTrue(MyRuleJavaClassHelper.isJavaMethodName("nomeValido9"));
        assertTrue(MyRuleJavaClassHelper.isJavaMethodName("nomeValido_9"));

    }

    @Test
    public void testeNomeInvalido()
    {

        assertFalse(MyRuleJavaClassHelper.isJavaMethodName("NomeInvalido"));
        assertFalse(MyRuleJavaClassHelper.isJavaMethodName("1NomeInvalido"));
        assertFalse(MyRuleJavaClassHelper.isJavaMethodName("nome Invalido"));

    }
    
}
