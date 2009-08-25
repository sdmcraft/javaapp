package com.sdm.FLVParser.datatypes;

public class Markers {

	private static Markers instance;
	public final UI8 NUMBER_MARKER;
	public final UI8 BOOLEAN_MARKER;
	public final UI8 STRING_MARKER;
	public final UI8 OBJECT_MARKER;
	public final UI8 MOVIECLIP_MARKER;
	public final UI8 NULL_MARKER;
	public final UI8 UNDEFINED_MARKER;
	public final UI8 REFERENCE_MARKER;
	public final UI8 ECMA_ARRAY_MARKER;
	public final UI8 OBJECT_END_MARKER;
	public final UI8 STRICT_ARRAY_MARKER;
	public final UI8 DATE_MARKER;
	public final UI8 LONG_STRING_MARKER;
	public final UI8 UNSUPPORTED_MARKER;
	public final UI8 RECORDSET_MARKER;
	public final UI8 XML_DOCUMENT_MARKER;
	public final UI8 TYPED_OBJECT_MARKER;


	public final UI8 STRING_NULL_TERMINATOR;

	private Markers() throws Exception {
		NUMBER_MARKER      = new UI8((byte) 0x00);
		BOOLEAN_MARKER     = new UI8((byte) 0x01);
		STRING_MARKER      = new UI8((byte) 0x02);
		OBJECT_MARKER      = new UI8((byte) 0x03);
		MOVIECLIP_MARKER   = new UI8((byte) 0x04);
		NULL_MARKER        = new UI8((byte) 0x05);
		UNDEFINED_MARKER   = new UI8((byte) 0x06);
		REFERENCE_MARKER   = new UI8((byte) 0x07);
		ECMA_ARRAY_MARKER  = new UI8((byte) 0x08);
		OBJECT_END_MARKER  = new UI8((byte) 0x09);
		STRICT_ARRAY_MARKER= new UI8((byte) 0x0A);
		DATE_MARKER        = new UI8((byte) 0x0B);
		LONG_STRING_MARKER = new UI8((byte) 0x0C);
		UNSUPPORTED_MARKER = new UI8((byte) 0x0D);
		RECORDSET_MARKER   = new UI8((byte) 0x0E);
		XML_DOCUMENT_MARKER= new UI8((byte) 0x0F);
		TYPED_OBJECT_MARKER= new UI8((byte) 0x10);
		
		STRING_NULL_TERMINATOR = new UI8((byte) 0x00);
	}

	public static Markers getInstance() throws Exception {
		if (instance == null) {
			instance = new Markers();
		}
		return instance;
	}
}
