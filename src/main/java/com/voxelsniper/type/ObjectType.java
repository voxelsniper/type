package com.voxelsniper.type;

public final class ObjectType implements FieldType, InternalForm {

  private final String descriptor;

  ObjectType(String descriptor) {
    this.descriptor = descriptor;
  }

  @Override
  public String internalForm() {
    return this.descriptor.substring(1, this.descriptor.length() - 1);
  }

  @Override
  public char term() {
    return 'L';
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
    visitor.visitObject(this);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof ObjectType objectType)) {
      return false;
    }
    return this.descriptor.contentEquals(objectType.descriptor);
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
