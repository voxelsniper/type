package com.voxelsniper.type;

public class BinaryNameVisitor implements TypeVisitor {

  private final StringBuilder builder = new StringBuilder();

  public String visit(Type type) {
    type.accept(this);
    return this.builder.toString();
  }

  @Override
  public void visitBase(BaseType baseType) {
    throw new IllegalArgumentException();
  }

  @Override
  public void visitObject(ObjectType objectType) {
    this.builder.append(objectType.internalForm().replace('/', '.'));
  }

  @Override
  public void visitArray(ArrayType arrayType) {
    if (arrayType.componentType() instanceof ObjectType objectType) {
      this.builder.append(objectType.internalForm().replace('/', '.'))
              .append("[]".repeat(arrayType.dimensions()));
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void visitMethod(MethodType methodType) {
    throw new IllegalArgumentException();
  }
}
