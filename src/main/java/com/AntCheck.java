/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: AntCheck
 * @package: com
 * @date: 2021-10-19 19:42
 * @version: V1.0
 */
package com;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: AntCheck
 * @description:
 * @author: fudai
 * @date: 2021-10-19 19:42
 */
public class AntCheck {

    @Test
    public void check() {

        Map<String, BigDecimal> innerDetailMap = fetchFundDetails("/Users/hb-mac/Downloads/A.txt");
        Map<String, BigDecimal> outerDetailMap = fetchFundDetails("/Users/hb-mac/Downloads/B.txt");
        CheckResult checkResult = CheckResult.of(innerDetailMap.size(), outerDetailMap.size());
        for (String innerKey : innerDetailMap.keySet()) {
            if (outerDetailMap.get(innerKey) == null) {
                CheckResult.Diff diff = CheckResult.Diff.builder().key(innerKey).msg("外部数据缺失").build();
                checkResult.getDiffs().add(diff);
            }
        }
        for (String key : outerDetailMap.keySet()) {
            CheckResult.Diff diff = doCompare(outerDetailMap.get(key), innerDetailMap.get(key), key);
            if (diff != null) {
                checkResult.getDiffs().add(diff);
            }
        }
        if (checkResult.getDiffs().size() == 0) {
            checkResult.setSame(true);
        }
        System.out.println(checkResult);

    }

    private CheckResult.Diff doCompare(BigDecimal outerAmt, BigDecimal innerAmt, String key) {
        if (innerAmt == null) {
            return CheckResult.Diff.builder().key(key).msg("内部数据缺失").outerAmt(outerAmt).build();
        }
        if (outerAmt.compareTo(innerAmt) != 0) {
            return CheckResult.Diff.builder().key(key).msg("金额不一致").innerAmt(innerAmt).outerAmt(outerAmt).build();

        }
        return null;
    }

    public Map<String, BigDecimal> fetchFundDetails(String filePath) {
        Map<String, BigDecimal> detailMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String s;
            while ((s = br.readLine()) != null) {
                if (s.isEmpty()) {
                    continue;
                }
                String[] fundDetailStrArray = s.split("\\|");
                if (fundDetailStrArray.length < 2) {
                    continue;
                }
                detailMap.put(fundDetailStrArray[0], new BigDecimal(fundDetailStrArray[1].replace("CNY", "")));

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return detailMap;

    }

    @Data
    @Builder
    public static class CheckResult {

        private int innerCount;
        private int outerCount;
        private boolean same;
        private List<Diff> diffs;
        private String msg;


        public static CheckResult of(int innerCount, int outerCount) {
            return CheckResult.builder().innerCount(innerCount).outerCount(outerCount).same(false).diffs(new ArrayList<>()).build();
        }

        @Data
        @Builder
        public static class Diff {
            String key;
            String msg;
            BigDecimal innerAmt;
            BigDecimal outerAmt;
        }


    }
}
