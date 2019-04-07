package hash_table;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* 535. Encode and Decode TinyURL
Description:
TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl
and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service.
There is no restriction on how your encode/decode algorithm should work.
You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 */

public class EncodeAndDecodeTinyURL {
    private String dict = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Map<String, String> map = new HashMap<>();
    private Random rand = new Random();
    private String code = getRand();

    private String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(dict.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (map.containsKey(code)) {
            code = getRand();
        }
        map.put(code, longUrl);
        return "http://tinyurl.com/" + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String key = shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
        return map.get(key);
    }
}
