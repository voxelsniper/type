package com.voxelsniper.type;

public sealed interface FieldType extends Type permits BaseType, ObjectType, ArrayType {

  char term();

  int length();

  default void append(StringBuilder builder) {
    builder.append(descriptor());
  }
}
