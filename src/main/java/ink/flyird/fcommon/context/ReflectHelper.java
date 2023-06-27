package ink.flyird.fcommon.context;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectHelper {
    static void setFinalFieldValue(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);

        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    public static void setFinalFieldValue(String clName,String field,Object newValue){
        try {
            Class<?> clazz=Class.forName(clName);
            Field f=clazz.getDeclaredField(field);
            setFinalFieldValue(f,newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
