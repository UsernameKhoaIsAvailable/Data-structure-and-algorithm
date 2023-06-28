package hashMap.checkpermutation;

import java.util.HashSet;

public class Main {
    public static boolean checkPermutation(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        HashSet<Character> characterHashSet1 = new HashSet<>();
        HashSet<Character> characterHashSet2 = new HashSet<>();

        for (int i = 0; i < str1.length(); i++) { //O(n)
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            characterHashSet1.add(char1);
            characterHashSet2.add(char2);
        }

        return characterHashSet1.size() == characterHashSet2.size();
    }

    public static boolean test1() {
        return !checkPermutation("hhhhhhhhhhh", "hhh");
    }

    public static boolean test2() {
        return checkPermutation("jooj", "ojoj");
    }

    public static boolean test3() {
        return !checkPermutation("qhrteidrfp", "amjrcirndj");
    }

    public static boolean test4() {
        return checkPermutation("ooo", "ooo");
    }

    public static void main(String[] arg) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
    }
}

