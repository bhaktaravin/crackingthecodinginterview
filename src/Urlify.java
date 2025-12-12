public class Urlify {
    
    public static void urlify(char[] tobeTransformed, int currentLength){
        int j = tobeTransformed.length - 1;
        for(int i = currentLength - 1; i < j; --i){
            if(tobeTransformed[i] == ' '){
                tobeTransformed[j--] = '0';
                tobeTransformed[j--] = '2';
                tobeTransformed[j--] = '%';
            } else {
                tobeTransformed[j--] = tobeTransformed[i];
            }
        }
    }

}
