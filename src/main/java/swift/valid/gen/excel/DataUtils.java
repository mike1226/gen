package swift.valid.gen.excel;

public class DataUtils {

    /**
     * Returns the Class object corresponding to the given short name.
     * The short name can be a primitive type name or a fully qualified class name.
     * If the short name is a primitive type name, the corresponding wrapper class name is returned.
     * If the short name is not recognized as a primitive type name, it is assumed to be a fully qualified class name.
     * 
     * @param shortName the short name of the class
     * @return the Class object corresponding to the short name
     * @throws ClassNotFoundException if the class corresponding to the short name is not found
     */
    public static Class<?> getDataClassType(String shortName) throws ClassNotFoundException {
        String fullName = "";
        String swString = shortName.toLowerCase();
        
        switch (swString) {
            case "string":
                fullName = "java.lang.String";
                break;
            case "byte":
                fullName = "java.lang.Byte";
                break;
            case "short":
                fullName =  "java.lang.Short";
                break;
            case "integer":
                fullName =  "java.lang.Integer";
                break;
            case "long":
                fullName =  "java.lang.Long";
                break;
            case "float":
                fullName =  "java.lang.Float";
                break;
            case "double":
                fullName =  "java.lang.Double";
                break;
            case "char":
                fullName =  "java.lang.Character";
                break;
            case "boolean":
                fullName =  "java.lang.Boolean";
                break;
            default:
                fullName =  shortName;
                break;
        }

        // return class name 
        return Class.forName(fullName);
    }

    /**
     * Converts a string value to the class type of the parameter.
     * 
     * @param value the string value to be converted
     * @param clazz the class type of the parameter
     * @return the converted value of the specified class type
     * @throws IllegalArgumentException if the conversion fails
     */
    public static Object convertToClassType(String value, Class<?> clazz) throws IllegalArgumentException {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Byte.class || clazz == byte.class) {
            return Byte.parseByte(value);
        } else if (clazz == Short.class || clazz == short.class) {
            return Short.parseShort(value);
        } else if (clazz == Integer.class || clazz == int.class) {
            Double doubleValue = Double.parseDouble(value);
            Integer integerValue = doubleValue.intValue();
            return integerValue;
        } else if (clazz == Long.class || clazz == long.class) {
            return Long.parseLong(value);
        } else if (clazz == Float.class || clazz == float.class) {
            return Float.parseFloat(value);
        } else if (clazz == Double.class || clazz == double.class) {
            return Double.parseDouble(value);
        } else if (clazz == Character.class || clazz == char.class) {
            if (value.length() != 1) {
                throw new IllegalArgumentException("Invalid character value: " + value);
            }
            return value.charAt(0);
        } else if (clazz == Boolean.class || clazz == boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            throw new IllegalArgumentException("Unsupported class type: " + clazz.getName());
        }
    }


    
    

}
