/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: CalculatorAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:56
 * @version: V1.0
 */
package com.leet.code;

import java.util.*;

/**
 * @className: CalculatorAlgorithm
 * @description: 计算器相关算法
 * @author: fudai
 * @date: 2021-07-15 18:56
 */
public class CalculatorAlgorithm {
    /**
     * 计算器
     *
     * @param s 字符串表达式
     * @return 计算结果
     */
    public static int calculator(String s) throws Exception {
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList();
        for (int i = 0; i < chars.length; i++) {
            queue.add(chars[i]);
        }
        return calculatorSub(queue);
    }

    /**
     * 计算器子方法
     * @param queue 队列
     * @return 结果
     * @throws Exception
     */
    public static int calculatorSub(Queue<Character> queue) throws Exception {
        Stack<Integer> stack = new Stack();
        int num = 0;
        char sign = '+';
        while (!queue.isEmpty()) {
            char sub = queue.poll();
            if (sub == ' ') {
                continue;
            }
            if (isNum(sub)) {
                num = 10 * num + (sub - '0');
            }
            if (sub == '(') {
                num = calculatorSub(queue);
            }
            if (!isNum(sub) || queue.isEmpty()) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        throw new Exception("非法的字符：" + sub);
                }
                sign = sub;
                num = 0;
            }
            if (sub == ')') {
                break;
            }
        }
        int result = 0;
        while (!stack.empty()) {
            result = result + stack.pop();
        }
        return result;
    }

    /**
     * 是否数字
     *
     * @param s 字符
     * @return 是否数字
     */
    private static boolean isNum(char s) {
        return s >= '0' && s <= '9';
    }

    /**
     * 结果备忘录
     */
    public static HashMap<String, List<Integer>> memoDiffWaysToCompute = new HashMap<>();

    /**
     * 为运算表达式设计优先级（分治算法）
     *
     * @param input 字符串表达式
     * @return 所有加括号的结果
     */
    public static List<Integer> diffWaysToCompute(String input) {
        if (memoDiffWaysToCompute.containsKey(input)) {
            return memoDiffWaysToCompute.get(input);
        }
        char[] chars = input.toCharArray();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftResult = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightResult = diffWaysToCompute(input.substring(i + 1, chars.length));
                leftResult.forEach(left -> {
                    rightResult.forEach(right -> {
                        if (c == '+') {
                            result.add(left + right);
                        } else if (c == '-') {
                            result.add(left - right);
                        } else if (c == '*') {
                            result.add(left * right);
                        }
                    });
                });
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.valueOf(input));
        }
        memoDiffWaysToCompute.put(input, result);
        return result;
    }
}
