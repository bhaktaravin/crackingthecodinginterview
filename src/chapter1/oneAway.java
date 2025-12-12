package chapter1;

public class oneAway {
     // Problem 1.5: One Away implementation
    // Check if two strings are one edit (or zero edits) away
    public boolean isOneAway(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        
        // If length difference is more than 1, more than one edit is needed
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }
        
        // Get shorter and longer string
        String shorter = len1 < len2 ? str1 : str2;
        String longer = len1 < len2 ? str2 : str1;
        
        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;
        
        while (index1 < shorter.length() && index2 < longer.length()) {
            if (shorter.charAt(index1) != longer.charAt(index2)) {
                // If this is the second difference, return false
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
                
                // For same length strings, move both pointers (replace case)
                // For different length, only move longer pointer (insert/remove case)
                if (shorter.length() == longer.length()) {
                    index1++;
                }
            } else {
                index1++;
            }
            index2++;
        }
        
        return true;
    }
}
