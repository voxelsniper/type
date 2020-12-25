package com.voxelsniper.type;

public final class TypeUtils {

  public static Type parseBinaryName(String binaryName) {
    int array = binaryName.indexOf('[');
    if (array == -1) {
      return className(binaryName);
    } else {
      int dimensions = (binaryName.length() - array) / 2;
      FieldType componentType = className(binaryName.substring(0, array));
      return new ArrayType(componentType, dimensions);
    }
  }

  private static FieldType className(String className) {
    return switch (className) {
      case "void" -> Types.VOID;
      case "byte" -> Types.BYTE;
      case "char" -> Types.CHAR;
      case "double" -> Types.DOUBLE;
      case "float" -> Types.FLOAT;
      case "long" -> Types.LONG;
      case "short" -> Types.SHORT;
      case "boolean" -> Types.BOOLEAN;
      default -> new ObjectType("L" + className.replace('.', '/') + ";");
    };
  }

  private TypeUtils() {
  }
}
