package com.voxelsniper.type;

public sealed interface Type permits FieldType, MethodType, InternalType {

  String descriptor();

  void accept(TypeVisitor visitor);

  @Override
  String toString();
  
  static Type getType(String descriptor) {
    return switch (descriptor.charAt(0)) {
      case 'V' -> Types.VOID;
      case 'B' -> Types.BYTE;
      case 'C' -> Types.CHAR;
      case 'D' -> Types.DOUBLE;
      case 'F' -> Types.FLOAT;
      case 'I' -> Types.INT;
      case 'J' -> Types.LONG;
      case 'S' -> Types.SHORT;
      case 'Z' -> Types.BOOLEAN;
      case 'L' -> new ObjectType(descriptor);
      case '[' -> new ArrayType(descriptor);
      case '(' -> new MethodType(descriptor);
      default -> throw new IllegalArgumentException();
    };
  }

  static FieldType getFieldType(String descriptor) {
    return switch (descriptor.charAt(0)) {
      case 'V' -> Types.VOID;
      case 'B' -> Types.BYTE;
      case 'C' -> Types.CHAR;
      case 'D' -> Types.DOUBLE;
      case 'F' -> Types.FLOAT;
      case 'I' -> Types.INT;
      case 'J' -> Types.LONG;
      case 'S' -> Types.SHORT;
      case 'Z' -> Types.BOOLEAN;
      case 'L' -> new ObjectType(descriptor);
      case '[' -> new ArrayType(descriptor);
      default -> throw new IllegalArgumentException();
    };
  }
  
  static ObjectType getObjectType(String descriptor) {
    if (descriptor.charAt(0) != 'L') {
      throw new IllegalArgumentException();
    }
    return new ObjectType(descriptor);
  }
  
  static ArrayType getArrayType(String descriptor) {
    if (descriptor.charAt(0) != '[') {
      throw new IllegalArgumentException();
    }
    return new ArrayType(descriptor);
  }

  static MethodType getMethodType(String descriptor) {
    if (descriptor.charAt(0) != '(') {
      throw new IllegalArgumentException();
    }
    return new MethodType(descriptor);
  }
  
  static InternalType getInternalType(String internal) {
    return new InternalType(internal);
  }
}
