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
