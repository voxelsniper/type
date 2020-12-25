package com.voxelsniper.type;

public final class MethodType implements Type {

  private final String descriptor;
  private final FieldType[] parameterTypes;
  private final FieldType returnType;

  MethodType(String descriptor) {
    this.descriptor = descriptor;
    this.parameterTypes = getParameterTypes(descriptor);
    this.returnType = getReturnType(descriptor);
  }

  public MethodType(FieldType[] parameterTypes, FieldType returnType) {
    StringBuilder builder = new StringBuilder("(");
    for (FieldType parameterType : parameterTypes) {
      builder.append(parameterType.descriptor());
    }
    builder.append(")");
    builder.append(returnType.descriptor());
    this.descriptor = builder.toString();
    this.parameterTypes = parameterTypes;
    this.returnType = returnType;
  }

  public static FieldType[] getParameterTypes(String descriptor) {
    int size = 0;
    int cursor = 1;
    while (descriptor.charAt(cursor) != ')') {
      while (descriptor.charAt(cursor) == '[') {
        cursor++;
      }
      if (descriptor.charAt(cursor++) == 'L') {
        cursor = descriptor.indexOf(';', cursor) + 1;
      }
      ++size;
    }
    FieldType[] types = new FieldType[size];
    int index = 0;
    cursor = 1;
    while (descriptor.charAt(cursor) != ')') {
      final int mark = cursor;
      while (descriptor.charAt(cursor) == '[') {
        cursor++;
      }
      if (descriptor.charAt(cursor++) == 'L') {
        cursor = descriptor.indexOf(';', cursor) + 1;
      }
      types[index++] = Type.getFieldType(descriptor.substring(mark, cursor));
    }
    return types;
  }

  public static FieldType getReturnType(String descriptor) {
    return Type.getFieldType(descriptor.substring(descriptor.lastIndexOf(')') + 1));
  }

  public FieldType[] parameterTypes() {
    return this.parameterTypes;
  }

  public FieldType returnType() {
    return this.returnType;
  }

  public int argumentAndReturnLengths() {
    int acc = 1;
    for (FieldType fieldType : this.parameterTypes) {
      acc += fieldType.length();
    }
    return acc << 2 | this.returnType.length();
  }

  @Override
  public String descriptor() {
    return this.descriptor;
  }

  @Override
  public void accept(TypeVisitor visitor) {
    visitor.visitMethod(this);
  }

  @Override
  public int hashCode() {
    return 31 * this.descriptor.hashCode();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof MethodType methodType)) {
      return false;
    }
    return this.descriptor.contentEquals(methodType.descriptor());
  }

  @Override
  public String toString() {
    return descriptor();
  }
}
