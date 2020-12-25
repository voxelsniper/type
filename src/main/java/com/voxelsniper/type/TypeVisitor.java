package com.voxelsniper.type;

public interface TypeVisitor {

  void visitBase(BaseType baseType);

  void visitObject(ObjectType objectType);

  void visitArray(ArrayType arrayType);

  void visitMethod(MethodType methodType);
}
