package dynamic_programming;

/* 188. Best Time to Buy and Sell Stock IV
Description:
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time
(ie, you must sell the stock before you buy again).

Example 1:
Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

/* Solution
第i次买操作买下当前股票之后剩下的最大利润为第i-1次卖掉股票之后的利润 － 当前的股票价格
第i次卖操作卖掉当前股票之后剩下的最大利润为第i次买操作之后剩下的利润＋当前股票价格
 */

import java.util.Arrays;

public class BestTimeToBuyAndSellStockIV {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return  0;
        }
        // if k >= len / 2, we can do as many transactions as we like
        // so it is the 122. Best Time to Buy and Sell Stock II
        if (k >= prices.length / 2) {
            int max = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] > prices[i-1]) {
                    max += prices[i] - prices[i-1];
                }
            }
            return max;
        }
        // the idea is like the 123. Best Time to Buy and Sell Stock III
        int[] buy = new int[k+1];
        int[] sell = new int[k+1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price: prices) {
            for (int i = 1; i <= k; ++i) {
                buy[i] = Math.max(buy[i], sell[i-1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }
}
