package com.voxelsniper.type;

import java.util.Map;

public class MappingVisitor implements TypeVisitor {

  private final Map<String, String> mapping;
  private Type type;

  public MappingVisitor(Map<String, String> mapping) {
    this.mapping = mapping;
  }

  public <T extends Type> T visit(T type) {
    type.accept(this);
    return (T) this.type;
  }

  @Override
  public void visitBase(BaseType baseType) {
    this.type = baseType;
  }

  @Override
  public void visitObject(ObjectType objectType) {
    this.type = mapObject(objectType);
  }

  @Override
  public void visitArray(ArrayType arrayType) {
    this.type = mapArray(arrayType);
  }

  @Override
  public void visitMethod(MethodType methodType) {
    FieldType[] parameterTypes = new FieldType[methodType.parameterTypes().length];
    for (int i = 0; i < parameterTypes.length; i++) {
      parameterTypes[i] = mapFieldType(methodType.parameterTypes()[i]);
    }
    FieldType returnType = mapFieldType(methodType.returnType());
    this.type = new MethodType(parameterTypes, returnType);
  }

  private FieldType mapObject(ObjectType objectType) {
    String mapped = this.mapping.get(objectType.internalForm());
    return mapped == null ? objectType : new ObjectType("L" + mapped + ";");
  }

  private FieldType mapArray(ArrayType arrayType) {
    if (arrayType.componentType() instanceof ObjectType objectType) {
      String mapped = this.mapping.get(objectType.internalForm());
      return mapped == null ? arrayType : new ArrayType(new ObjectType("L" + mapped + ";"), arrayType.dimensions());
    } else {
      return arrayType;
    }
  }

  private FieldType mapFieldType(FieldType fieldType) {
    if (fieldType instanceof ObjectType objectType) {
      return mapObject(objectType);
    } else if (fieldType instanceof ArrayType arrayType) {
      return mapArray(arrayType);
    } else {
      return fieldType;
    }
  }
}
