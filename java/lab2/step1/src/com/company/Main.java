package com.company;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args)
    {
        System.out.println(solution("5"));
        System.out.println(solution("-35000"));
        System.out.println(solution("512"));
        System.out.println(solution("2500000000"));

    }
    public static String solution(String input)
    {
        int realLeangth = 0;
        if(input.charAt(0) == '-')
            realLeangth = 1;

        if(input.length() - realLeangth <= 3)
            if (CompareByteToShort(input))
                return Types.ShortString;
            else
                return Types.ByteString;

        else if(input.length() - realLeangth <= 5)
            if (CompareShortToInt(input))
                return Types.IntString;
            else
                return Types.ShortString;

        else if(input.length() - realLeangth <= 10)
            if (CompareIntToLong(input))
                return Types.LongString;
            else
                return Types.IntString;

        else if(input.length() <= 20)
            if (CompareLongToError(input, realLeangth))
                return "Error";
            else
                return Types.LongString;

        return "Error";
    }
    private static boolean CompareByteToShort(String input)
    {
        if(input.charAt(0) != '-')
            return Short.valueOf(input) > 127;
        else
            return Short.valueOf(input) < -128;
    }
    private static boolean CompareShortToInt(String input)
    {
        if(input.charAt(0) != '-')
            return Integer.valueOf(input) > Short.MAX_VALUE;
        else
            return Integer.valueOf(input) < Short.MIN_VALUE;
    }
    private static boolean CompareIntToLong(String input)
    {
        if(input.charAt(0) != '-')
            return Long.valueOf(input) > Integer.MAX_VALUE;
        else
            return Long.valueOf(input) < Integer.MIN_VALUE;
    }
    private static boolean CompareLongToError(String input, Integer num)
    {
        var longLimit = Long.MAX_VALUE;
        if(num == 1)
            longLimit = Long.MIN_VALUE;

        for(int i = 0 + num; i < Math.max(input.length(), Long.toString((longLimit)).length()); i++)
        {
            if(input.charAt(i) != Long.toString((longLimit)).charAt(i))
                return input.charAt(i) < Long.toString((longLimit)).charAt(i);
        }

        return true;
    }
}
class Types{
    public static String IntString = "int";
    public static String LongString = "long";
    public static String ShortString = "short";
    public static String ByteString = "byte";
}
