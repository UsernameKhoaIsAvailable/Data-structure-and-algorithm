package hashMap.stringcompression;

import java.util.HashMap;

public class Main {
    public static String stringCompression(String str) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        int num  = 0;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i-1)) {
                int oldValue = countMap.get(num);
                countMap.put(num, oldValue + 1);
            }
            else {
                num = i;
                countMap.put(num, 1);
            }
        }

        String newStr = "";

        for (int i : countMap.keySet()) {
            newStr += str.charAt(i);
            newStr += countMap.get(i);
        }

        if (newStr.length() < str.length()) {
            return newStr;
        }

        return str;
    }

    public static boolean test1() { return stringCompression("aabcccccaaa").equals("a2b1c5a3"); }
    public static boolean test2() { return stringCompression("aafjehfueufhdj").equals("aafjehfueufhdj"); }
    public static boolean test3() { return stringCompression("bbccdddekf").equals("bbccdddekf"); }
    public static boolean test4() { return stringCompression("bbbdddhhhheeeggrrrr").equals("b3d3h4e3g2r4"); }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());

    }

}
