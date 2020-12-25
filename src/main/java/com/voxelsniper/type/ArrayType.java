package com.voxelsniper.type;

public final class ArrayType implements FieldType, InternalForm {

  private final String descriptor;
  private final FieldType componentType;
  private final int dimensions;

  ArrayType(String descriptor) {
    this.descriptor = descriptor;
    this.componentType = getComponentType(descriptor);
    this.dimensions = getDimensions(descriptor);
  }

  public ArrayType(FieldType componentType, int dimensions) {
    this.descriptor = String.valueOf('[').repeat(dimensions).concat(componentType.descriptor());
    this.componentType = componentType;
    this.dimensions = dimensions;
  }

  public static FieldType getComponentType(String descriptor) {
    return Type.getFieldType(descriptor.substring(getDimensions(descriptor)));
  }

  public static int getDimensions(String descriptor) {
    int dimensions = 1;
    while (descriptor.charAt(dimensions) == '[') {
      dimensions++;
    }
    return dimensions;
  }

  public FieldType componentType() {
    return this.componentType;
  }

  public int dimensions() {
    return this.dimensions;
  }

  @Override
  public String internalForm() {
    return descriptor();
  }

  @Override
  public char term() {
    return '[';
  }

  @Override
  public int length() {
    return 1;
  }

  @Override
  public void append(StringBuilder builder) {
    builder.append(descriptor());
  }

  @Override
  public String descriptor() {
    return this.descriptor;
  }

  @Override
  public void accept(TypeVisitor visitor) {
    visitor.visitArray(this);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof ArrayType arrayType)) {
      return false;
    }
    return this.descriptor.contentEquals(arrayType.descriptor);
  }

  @Override
  public int hashCode() {
    return 31 * this.descriptor.hashCode();
  }

  @Override
  public String toString() {
    return descriptor();
  }
}
