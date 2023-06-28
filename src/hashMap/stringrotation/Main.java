package hashMap.stringrotation;

import java.util.HashSet;

public class Main {
    public static boolean isSubstring(String str1, String str2) {
        if (str1.contains(str2) || str2.contains(str1)) {
            return true;
        }

        return false;
    }

    public static boolean isRotate(String str1, String str2) {
        if (str1.length() == str2.length()) {
            String concatenatedStr = str2 + str2;

            if (isSubstring(str1, concatenatedStr)) {
                return true;
            }
        }

        return false;
    }

    public static boolean test1() {
        return isRotate("waterbottle", "erbottlewat");
    }

    public static boolean test2() {
        return isRotate("qwerty", "ertyqw");
    }

    public static boolean test3() {
        return !isRotate("helloxinchaohihi", "eililoconhhhhaix");
    }

    public static boolean test4() {
        return !isRotate("hello", "eililoconhhhhaix");
    }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
    }
}
