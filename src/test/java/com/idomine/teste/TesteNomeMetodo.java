package com.idomine.teste;

import org.junit.Test;

import static org.junit.Assert.*;
import com.idomine.ruleengine.helper.JavaClassHelper;

public class TesteNomeMetodo
{

    @Test
    public void testeNomeValido()
    {

        assertTrue(JavaClassHelper.isJavaMethodName("nomeValido"));
        assertTrue(JavaClassHelper.isJavaMethodName("nomeValido9"));
        assertTrue(JavaClassHelper.isJavaMethodName("nomeValido_9"));

    }

    @Test
    public void testeNomeInvalido()
    {

        assertFalse(JavaClassHelper.isJavaMethodName("NomeInvalido"));
        assertFalse(JavaClassHelper.isJavaMethodName("1NomeInvalido"));
        assertFalse(JavaClassHelper.isJavaMethodName("nome Invalido"));

    }
    
}
