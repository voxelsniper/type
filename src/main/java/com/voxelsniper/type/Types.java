package com.voxelsniper.type;

public final class Types {

  public static final FieldType VOID    = new BaseType(/* term =*/ 'V', /* length =*/ 0);
  public static final FieldType BYTE    = new BaseType(/* term =*/ 'B', /* length =*/ 1);
  public static final FieldType CHAR    = new BaseType(/* term =*/ 'C', /* length =*/ 1);
  public static final FieldType DOUBLE  = new BaseType(/* term =*/ 'D', /* length =*/ 2);
  public static final FieldType FLOAT   = new BaseType(/* term =*/ 'F', /* length =*/ 1);
  public static final FieldType INT     = new BaseType(/* term =*/ 'I', /* length =*/ 1);
  public static final FieldType LONG    = new BaseType(/* term =*/ 'J', /* length =*/ 2);
  public static final FieldType SHORT   = new BaseType(/* term =*/ 'S', /* length =*/ 1);
  public static final FieldType BOOLEAN = new BaseType(/* term =*/ 'Z', /* length =*/ 1);

  private Types() {
  }
}
