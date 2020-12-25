package com.voxelsniper.type;

public final class BaseType implements FieldType {

  private final char term;
  private final int length;

  BaseType(char term, int length) {
    this.term = term;
    this.length = length;
  }

  @Override
  public char term() {
    return this.term;
  }

  @Override
  public int length() {
    return this.length;
  }

  @Override
  public void append(StringBuilder builder) {
    builder.append(this.term);
  }

  @Override
  public String descriptor() {
    return String.valueOf(this.term);
  }

  @Override
  public void accept(TypeVisitor visitor) {
    visitor.visitBase(this);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof BaseType baseType)) {
      return false;
    }
    return this.term == baseType.term;
  }

  @Override
  public int hashCode() {
    return 31 * Character.hashCode(this.term);
  }

  @Override
  public String toString() {
    return descriptor();
  }
}
