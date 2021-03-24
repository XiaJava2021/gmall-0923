package com.atguigu.gmall.index.test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author Hasee  2021/3/23 14:25 周二
 * @version JDK 8.017
 * @description:
 */
public class GuavaBlooFilterDemo {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),20,0.3);
        bloomFilter.put("1");
        bloomFilter.put("2");
        bloomFilter.put("3");
        bloomFilter.put("4");
        bloomFilter.put("5");
        bloomFilter.put("6");
        bloomFilter.put("7");
        bloomFilter.put("8");
        bloomFilter.put("9");
        bloomFilter.put("10");
        bloomFilter.put("11");
        bloomFilter.put("12");
        bloomFilter.put("13");
        bloomFilter.put("14");
        bloomFilter.put("15");
        bloomFilter.put("16");
        System.out.println(bloomFilter.mightContain("1"));
    }
}
