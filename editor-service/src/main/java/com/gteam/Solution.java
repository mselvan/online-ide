package com.gteam;

/**
 * Created by nagarajan on 18/12/14.
 */
public class Solution {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.name = "Nagarajan Shanmugam";
        System.out.println(employee.name);
    }
    static class Employee {
        public String name;
    }
}