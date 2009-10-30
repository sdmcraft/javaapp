package com.sdm.FLVParser.datatypes;

public class Markers {
	public static final U8 NUMBER_MARKER = new U8((byte) 0x00);
	public static final U8 BOOLEAN_MARKER = new U8((byte) 0x01);
	public static final U8 STRING_MARKER = new U8((byte) 0x02);
	public static final U8 OBJECT_MARKER = new U8((byte) 0x03);
	public static final U8 MOVIECLIP_MARKER = new U8((byte) 0x04);
	public static final U8 NULL_MARKER = new U8((byte) 0x05);
	public static final U8 UNDEFINED_MARKER = new U8((byte) 0x06);
	public static final U8 REFERENCE_MARKER = new U8((byte) 0x07);
	public static final U8 ECMA_ARRAY_MARKER = new U8((byte) 0x08);
	public static final U8 OBJECT_END_MARKER = new U8((byte) 0x09);
	public static final U8 STRICT_ARRAY_MARKER = new U8((byte) 0x0A);
	public static final U8 DATE_MARKER = new U8((byte) 0x0B);
	public static final U8 LONG_STRING_MARKER = new U8((byte) 0x0C);
	public static final U8 UNSUPPORTED_MARKER = new U8((byte) 0x0D);
	public static final U8 RECORDSET_MARKER = new U8((byte) 0x0E);
	public static final U8 XML_DOCUMENT_MARKER = new U8((byte) 0x0F);
	public static final U8 TYPED_OBJECT_MARKER = new U8((byte) 0x10);
	public static final U8 STRING_NULL_TERMINATOR = new U8((byte) 0x00);
}
