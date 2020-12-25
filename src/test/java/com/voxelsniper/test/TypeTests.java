package com.voxelsniper.test;

import com.voxelsniper.type.BinaryNameVisitor;
import com.voxelsniper.type.FieldType;
import com.voxelsniper.type.MappingVisitor;
import com.voxelsniper.type.MethodType;
import com.voxelsniper.type.Type;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTests {

  @ParameterizedTest()
  @ValueSource(strings = "(FI[[J[Lcom.voxelsniper.type.Type;D)J")
  public void readMethod(String descriptor) {
    MethodType type = Type.getMethodType(descriptor);

    // Parameter and return type parse verification
    assertEquals("[[J", type.parameterTypes()[2].descriptor());
    assertEquals("J", type.returnType().descriptor());

    // Argument and return type lengths
    int lengths = type.argumentAndReturnLengths();
    int arglength = lengths >> 2;
    int retlength = lengths & 3;
    assertEquals(7, arglength);
    assertEquals(2, retlength);
  }

  @ParameterizedTest()
  @ValueSource(strings = "Lcom/voxelsniper/type/FieldType;")
  public void visitObject(String descriptor) {
    FieldType type = Type.getFieldType(descriptor);

    { // BinaryNameVisitor
      BinaryNameVisitor visitor = new BinaryNameVisitor();
      assertEquals("com.voxelsniper.type.FieldType", visitor.visit(type));
    }

    { // MappingVisitor
      Map<String, String> mapping = Map.of("com/voxelsniper/type/FieldType", "com/voxelsniper/type/FieldDescriptor");
      MappingVisitor visitor = new MappingVisitor(mapping);
      assertEquals("Lcom/voxelsniper/type/FieldDescriptor;", visitor.visit(type).descriptor());
    }
  }
}
