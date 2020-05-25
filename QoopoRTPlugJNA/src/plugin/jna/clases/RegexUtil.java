/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.clases;

/**
 *
 * @author alberto
 */
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//
//import qj.util.funct.F1;
//import qj.util.funct.Fs;
//import qj.util.funct.P1;
//import qj.util.funct.P2;
//import qj.util.math.Range;
//import qj.util.structure.Structure;
//import qj.util.structure.StructureBuilder;

public class RegexUtil extends RegexUtil4 {

//    public static void main(String[] args) {
//        System.out.println(replaceAll("a bb esr", "a", "@@", "bb", "^^", "awr", ""));
//    }
    public static String[] groups(Matcher matcher) {
        String[] ret = new String[matcher.groupCount()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = matcher.group(i + 1);
        }
        return ret;
    }

    private static boolean matchAll(String ptn, Collection<String> col) {
        Pattern pattern = Pattern.compile(ptn);
        for (String s : col) {
            if (!pattern.matcher(s).matches()) {
                return false;
            }
        }
        return true;
    }

    public static String toString(Matcher matcher) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= matcher.groupCount(); i++) {
            sb.append("Group ").append(i).append(": ").append(matcher.group(i)).append("\n");
        }

        return sb.toString();
    }

    public static boolean endsWith(String ptn, String str) {
        return Pattern.compile(ptn + "$").matcher(str).find();
    }

    public static boolean startWith(String ptn, String str) {
        return Pattern.compile("^" + ptn).matcher(str).find();
    }

    public static Matcher lastFound(Pattern ptn, int pos, CharSequence text) {
        Matcher matcher = ptn.matcher(text);
        int last = -1;
        while (matcher.find()) {
            int start = matcher.start();
//                      if (start==pos) {
//                              return matcher;
//                      } else 
            if (start >= pos) {
                if (last > -1) {
                    matcher.find(last - 1);
                    return matcher;
                } else {
                    return null;
                }
            }
            last = start;
        }
        if (last > -1) {
            matcher.find(last);
            return matcher;
        } else {
            return null;
        }
    }

    public static class AnyGroup {
    }

    public static class NonCapturingGroup extends AnyGroup {

        public NonCapturingGroup() {
        }

    }

    public static class Group extends AnyGroup implements Comparable<Group> {

        public final int index;

        public Group(int index) {
            this.index = index;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + index;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Group other = (Group) obj;
            if (index != other.index) {
                return false;
            }
            return true;
        }

        public int compareTo(Group o) {
            return index - o.index;
        }

        @Override
        public String toString() {
            return "Group[" + index + "]";
        }

    }

    public static Integer getInt(String key, Pattern ptn) {
        Matcher matcher = ptn.matcher(key);
        if (!matcher.find()) {
            return null;
        }
        return Integer.valueOf(matcher.group(1));
    }

    public static boolean matches(String key, Pattern ptn) {
        return ptn.matcher(key).matches();
    }

    public static boolean matchesAny(String[] keys, Pattern ptn) {
        for (String key : keys) {
            if (key == null) {
                continue;
            }
            if (ptn.matcher(key).matches()) {
                return true;
            }
        }
        return false;
    }

    public static int indexOf(String ptn, int groupId, String text) {
        int from = 0;
        return indexOf(ptn, groupId, text, from);
    }

    public static int indexOf(String ptn, int groupId, String text, int from) {
        Matcher matcher = Pattern.compile(ptn).matcher(text);
        if (matcher.find(from)) {
            return matcher.start(groupId);
        } else {
            return -1;
        }
    }

    /**
     * Get the string group out of given text
     *
     * @param ptnStr
     * @param groupId
     * @param text
     * @return
     */
    public static String getString(String ptnStr, int groupId, String text) {
        if (text == null) {
            return null;
        }
//        Pattern ptn = cachedCompileF.e(ptnStr); //Pattern.compile(ptnStr)
        Pattern ptn = Pattern.compile(ptnStr);
        return getString(ptn, groupId, text);
    }

    /**
     * Get the string group out of given text
     *
     * @param ptn
     * @param groupId
     * @param text
     * @return
     */
    public static String getString(Pattern ptn, int groupId, String text) {
        Matcher matcher = ptn.matcher(text);
        if (matcher.find()) {
            return matcher.group(groupId);
        } else {
            return null;
        }
    }

    public static Integer getGroup(int pos, Matcher matcher) {
        for (int i = matcher.groupCount(); i > -1; i--) {
            if (pos >= matcher.start(i)
                    && pos <= matcher.end(i)) {
                return i;
            }
        }
        return null;
    }

    public static boolean find(Pattern ptn, String string) {
        return ptn.matcher(string).find();
    }

    public static String find(Pattern ptn, Collection<String> excepts, String string) {
        Matcher matcher = ptn.matcher(string);
        while (matcher.find()) {
            String val = matcher.group();
            if (!excepts.contains(val)) {
                return val;
            }
        }
        return null;
    }

    public static int countFinds(Pattern ptn, String string) {
        Matcher matcher = ptn.matcher(string);
        int count = 0;
//              System.out.println(string);
        while (matcher.find()) {
//                      System.out.println(matcher.group());
            count++;
        }
        return count;
    }

    public static int countFinds(Pattern ptn, Collection<String> excepts, String string) {
        Matcher matcher = ptn.matcher(string);
        int count = 0;
//              System.out.println(string);
        while (matcher.find()) {
            if (!excepts.contains(matcher.group())) {
                count++;
            }
        }
        return count;
    }

    public static String lastFind(Pattern ptn, Collection<String> excepts, String string) {
        Matcher matcher = ptn.matcher(string);
        String last = null;
        while (matcher.find()) {
            String val = matcher.group();
            if (!excepts.contains(val)) {
                last = val;
            }
        }
        return last;
    }

    public static String nextFind(Pattern ptn, Collection<String> excepts, String string) {
        Matcher matcher = ptn.matcher(string);
        while (matcher.find()) {
            String val = matcher.group();
            if (!excepts.contains(val)) {
                return val;
            }
        }
        return null;
    }

    public static String lastFind(Pattern ptn, int group, String string) {
        Matcher matcher = ptn.matcher(string);
        String last = null;
        while (matcher.find()) {
            last = matcher.group(group);
        }
        return last;
    }

}
