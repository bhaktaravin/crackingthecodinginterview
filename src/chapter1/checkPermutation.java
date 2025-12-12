package chapter1;

import java.util.HashMap;

public class checkPermutation {

    // ASCII optimized version (O(n) time, O(1) space)
    // Returns false if non-ASCII characters are detected
    public boolean checkPermutationASCII(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        // Check if strings contain only ASCII characters
        if (!isASCII(str1) || !isASCII(str2)) {
            throw new IllegalArgumentException("Non-ASCII characters detected. Please use Unicode mode.");
        }

        int[] charCount = new int[128]; // Assuming ASCII

        for (char c : str1.toCharArray()) {
            charCount[c]++;
        }

        for (char c : str2.toCharArray()) {
            charCount[c]--;
            if (charCount[c] < 0) {
                return false;
            }
        }

        return true;
    }
    
    // Helper method to check if string contains only ASCII characters
    private boolean isASCII(String str) {
        for (char c : str.toCharArray()) {
            if (c > 127) {
                return false;
            }
        }
        return true;
    }
    
    // Unicode version using HashMap (O(n) time, O(k) space where k = unique chars)
    public boolean checkPermutationUnicode(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (char c : str1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str2.toCharArray()) {
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0) {
                return false;
            }
            map.put(c, count);
        }
        
        return true;
    }
    
    // Default method for backward compatibility
    public boolean checkPermutation(String str1, String str2) {
        return checkPermutationASCII(str1, str2);
    }
}