package hashMap.oneaway;

import java.util.HashSet;

public class Main {
    public static boolean oneAway(String str1, String str2) {
        if (Math.abs(str1.length() - str2.length()) == 1 || str1.length() == str2.length()) {
            HashSet<Character> charSet = new HashSet<>();
            String str = str1 + str2;
            int set1 = 0;

            for (int i = 0; i < str.length(); i++) {
                char strChar = str.charAt(i);
                charSet.add(strChar);

                if (i == str1.length() - 1) {
                    set1 = charSet.size();
                }
            }

            if (Math.abs(set1 - charSet.size()) == 1 || set1 == charSet.size()) {
                return true;
            }
        }

        return false;
    }

    public static boolean test1() { return oneAway("pale", "ple"); }

    public static boolean test2() {
        return oneAway("pales", "pale");
    }

    public static boolean test3() { return oneAway("pale", "bale"); }

    public static boolean test4() { return !oneAway("pale", "bake"); }

    public static boolean test5() { return oneAway("palaga", "plaga"); }

    public static void main(String[] arg) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}

