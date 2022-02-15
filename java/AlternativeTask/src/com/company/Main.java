package com.company;

import java.util.*;

public class Main {

    public static String operations = "+-*/";
    public static ArrayList<String> Results = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> Func = new ArrayList<>();
    public static void main(String[] args){

        ArrayList<String> nums = new ArrayList<String>();
        nums.add("997654321");
        toNewBase(nums);
        Set<ArrayList<String>> set = new HashSet<>(Func);
        Func.clear();
        Func.addAll(set);

        for (ArrayList<String> func:Func) {
            CombOfOperations(new ArrayList<String>(), func, 0);
        }

        for (String func: Results) {
            System.out.println(func + " = 100");
        }
        System.out.println(Results.size());


    }
    public static void toNewBase(ArrayList<String> nums){

        Integer del = 10;

        while (Integer.toString(del).length() <= 8){

            for(int i = 0; i < nums.size(); i++){

                if(nums.get(i).length() > 1 && nums.get(i).length() >= Integer.toString(del).length() ){
                    toNewBase(ParseNum(i, nums, del));
                }
            }
            del*=10;
        }

        Func.add(nums);


    }
    private static void CombOfOperations(ArrayList<String> func, ArrayList<String> nums, Integer ind){

        if(ind < nums.size()-1){
            for(int i = 0; i < 4; i++){

                func.add(nums.get(ind));
                func.add(Character.toString(operations.charAt(i)));

                CombOfOperations(func, nums, ind + 1);

                for (int j = 0; j < 2; j++)
                    func.remove(func.size()-1);
            }
        }
        else if (ind == nums.size()-1){
            func.add(nums.get(ind));
            Calculate(func);
            func.remove(func.size()-1);
        }


    }
    private static void Calculate(ArrayList<String> oldFunc){

        ArrayList<String> func = new ArrayList<>(oldFunc);

        while (func.contains("*") || func.contains("/")){

            if (func.contains("*")){
                Integer ind = func.indexOf("*");
                Change(func, ind, Double.valueOf(func.get(ind - 1)) * Double.valueOf(func.get(ind + 1)));
            }
            if(func.contains("/")) {
                Integer ind = func.indexOf("/");
                Change(func, ind, Double.valueOf(func.get(ind - 1)) / Double.valueOf(func.get(ind + 1)));
            }
        }

        while (func.contains("+") || func.contains("-")){

            if (func.contains("+")){
                Integer ind = func.indexOf("+");
                Change(func, ind, Double.valueOf(func.get(ind - 1)) + Double.valueOf(func.get(ind + 1)));
            }
            if(func.contains("-")) {
                Integer ind = func.indexOf("-");
                Change(func, ind, Double.valueOf(func.get(ind - 1)) - Double.valueOf(func.get(ind + 1)));
            }
        }

        if(Objects.equals(func.get(0), "100.0"))
            Results.add(GetListInfo(oldFunc));
    }
    private static void Change(ArrayList<String> func, Integer ind, Double num){

        for(int i = 0; i < 2; i++){
            func.remove(ind - 1);
        }

        func.set(ind - 1, Double.toString(num));
    }

    private static ArrayList<String> ParseNum(Integer ind, ArrayList<String> nums, Integer del){

        ArrayList<String> newNums = new ArrayList<String>();
        for(int i = 0; i < nums.size(); i++){

            if(i == ind){
                newNums.add(Integer.toString(Integer.valueOf(nums.get(i))/del));
                newNums.add(Integer.toString(Integer.valueOf(nums.get(i))%del));
            }
            else {
                newNums.add(nums.get(i));
            }
        }

        return newNums;

    }
    private static String GetListInfo(ArrayList<String> nums){
        String func = "";

        for (String num: nums) {
            func += num;
        }
         return func;
    }

}

