package com.idomine.myruleengine.helper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public final class MyRuleJavaClassHelper
{

    private static final Set<String> javaKeywords = new HashSet<String>(Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "false", "final", "finally",
            "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long",
            "native", "new", "null", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "true", "try",
            "void", "volatile", "while"));

    private static final Pattern JAVA_CLASS_NAME_PART_PATTERN = Pattern.compile("[A-Za-z_$]+[a-zA-Z0-9_$]*");
    private static final Pattern JAVA_METHOD_NAME_PART_PATTERN = Pattern.compile("[a-z_$]+[a-zA-Z0-9_$]*");

    private MyRuleJavaClassHelper()
    {
    }

    public static boolean isJavaClassName(String text)
    {
        for (String part : text.split("\\."))
        {
            if (javaKeywords.contains(part) ||
                    !JAVA_CLASS_NAME_PART_PATTERN.matcher(part).matches())
            {
                return false;
            }
        }
        return text.length() > 0;
    }

    public static boolean isJavaMethodName(String text)
    {
        for (String part : text.split("\\."))
        {
            if (javaKeywords.contains(part) ||
                    !JAVA_METHOD_NAME_PART_PATTERN.matcher(part).matches())
            {
                return false;
            }
        }
        return text.length() > 0;
    }

}
