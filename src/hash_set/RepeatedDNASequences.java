package hash_set;

/* 187. Repeated DNA Sequences
Description:
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:
Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Set seen = new HashSet(), res = new HashSet();
        for (int i = 0; i + 9 < s.length(); ++i) {
            String sub = s.substring(i, i + 10);
            if (!seen.contains(sub)) {
                seen.add(sub);
            } else {
                res.add(sub);
            }
        }
        return new ArrayList(res);
    }
}
