package hashMap.isunique;

import java.util.HashSet;

public class Main {
    public static boolean isUnique(String str) {
        HashSet<Character> charSet = new HashSet<>();

        for (int i = 0; i < str.length() - 1; i++) {
            char character = str.charAt(i);

            if (charSet.contains(character)) {
               return false;
            }
            else {
                charSet.add(character);
            }
        }

        return true;
    }

    public static boolean test1() {
        return isUnique("abcdef");
    }

    public static boolean test2() {
        return !isUnique("ffffffffffff");
    }

    public static boolean test3() {
        return isUnique("");
    }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
    }

}
