package hashMap.urlify;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static char[] URLify(char[] charArray) {
        int i = 0;
        HashMap<Integer, Character> charMap = new HashMap();

        while (charArray[i] != 0) {
            charMap.put(i, charArray[i]);
            i++;
        }

        i = 0;

        for (char _char : charMap.values()) {
            if (_char == ' ') {
                charArray[i] = '%';
                charArray[i+1] = '2';
                charArray[i+2] = '0';
                i += 2;
            }
            else {
                charArray[i] = _char;
            }

            i++;
        }

        return charArray;
    }

    public static boolean test() {
        char[] chars = new char[20];
        chars[0] = 'M';
        chars[1] = 'r';
        chars[2] = ' ';
        chars[3] = 'J';
        chars[4] = 'o';
        chars[5] = 'h';
        chars[6] = 'n';
        chars[7] = ' ';
        chars[8] = 'S';
        chars[9] = 'm';
        chars[10] = 'i';
        chars[11] = 't';
        chars[12] = 'h';

        char[] chars1 = new char[20];
        chars1[0] = 'M';
        chars1[1] = 'r';
        chars1[2] = '%';
        chars1[3] = '2';
        chars1[4] = '0';
        chars1[5] = 'J';
        chars1[6] = 'o';
        chars1[7] = 'h';
        chars1[8] = 'n';
        chars1[9] = '%';
        chars1[10] = '2';
        chars1[11] = '0';
        chars1[12] = 'S';
        chars1[13] = 'm';
        chars1[14] = 'i';
        chars1[15] = 't';
        chars1[16] = 'h';
        chars = URLify(chars);
        return Arrays.equals(chars, chars1);
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
