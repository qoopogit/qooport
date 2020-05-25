package rt.interfaces;

import java.lang.reflect.InvocationTargetException;

//acceso registro
public interface AR {

    public void writeStringValue(int hkey, String key, String valueName, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    public void deleteValue(int hkey, String key, String value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

    public String getPassDM();

}
