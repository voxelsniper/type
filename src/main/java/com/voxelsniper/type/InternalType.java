package com.voxelsniper.type;

public final class InternalType implements Type, InternalForm {
  
  private final String internal;

  InternalType(String internal) {
    this.internal = internal;
  }

  @Override
  public String internalForm() {
    return this.internal;
  }

  @Override
  public String descriptor() {
    return "L" + this.internal + ";";
  }

  @Override
  public void accept(TypeVisitor visitor) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof InternalType internalType)) {
      return false;
    }
    return this.internal.contentEquals(internalType.internal);
  }

  @Override
  public int hashCode() {
    return 31 * this.internal.hashCode();
  }

  @Override
  public String toString() {
    return internalForm();
  }
}
