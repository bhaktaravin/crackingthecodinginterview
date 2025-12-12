import java.util.HashSet;
import java.util.Set;

public class IsUnique {
    //What if you cant use additional data structures?
    public static boolean isUniqueCharsNoDS(String str) {
        int checker = 0; // Integer to use as bit vector
        for(int i = 0; i < str.length(); i++) {
        int val = str.charAt(i) - 'a'; // Assuming only lowercase 'a' to 'z'
        //print out whats happening 
        System.out.println("Checking character: " + str.charAt(i));
        System.out.println("Checker value: " + Integer.toBinaryString(checker));

        if((checker & (1 << val)) > 0) {
            System.out.println("Duplicate character found: " + str.charAt(i));
            return false;
        }
        System.out.println("No duplicate found for character: " + str.charAt(i));
        checker |= (1 << val);
        }
        return true;
    }

    public static boolean isUniqueChars(String str) {
    Set<Character> seen = new HashSet<>(); // Empty set to track seen characters
    for(char c: str.toCharArray()) {
        //print out whats happening
        System.out.println("Checking character: " + c);
        System.out.println("Seen so far: " + seen);
      if(seen.contains(c)) {
        System.out.println("Duplicate character found: " + c);
        return false;
      }
      System.out.println("No duplicate found for character: " + c);
      seen.add(c);
    }
    return true;
  
}
}
