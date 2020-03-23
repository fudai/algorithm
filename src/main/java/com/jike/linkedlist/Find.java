/**
 * Copyright (c) 2009-2020 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: Find
 * @package: linkedlist
 * @date: 2020-02-14 16:15
 * @version: V1.0
 */
package com.jike.linkedlist;


/**
 * @className: Find
 * @description:
 * @author: fudai
 * @date: 2020-02-14 16:15
 */
public class Find {
    static int find(char[] a, int n, char key) {
        if (a.length == 0 || n <= 0) {
            return -1;
        }
        int i = 0;
        while (i < n) {
            if (a[i] == key) {
                return i;
            }
            i++;
        }
        return -1;
    }

    static int find2(char[] a, int n, char key) {
        if (a.length == 0 || n <= 0) {
            return -1;
        }
        if (key == a[n - 1]) {
            return n - 1;
        }
        char temp = a[n - 1];
        a[n - 1] = key;
        int i = 0;
        while (a[i] != key) {
            i++;
        }
        a[n - 1] = temp;
        if (i == n - 1) {
            return -1;
        } else {
            return i;
        }

    }

    public static void main(String[] args) {
        char[] a = {'q', 'w', 'e', 'r', 't'};
//        System.out.println(find(a, a.length, 't'));
        System.out.println(find2(a, a.length, 'q'));
//        ThreadLocal threadLocal =
    }
}
