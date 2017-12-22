package com.cimc.datahub.mining.parquet;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;

public class ParquetFileProcessor {

	public static void main(String[] args) throws Exception {

		Path file = new Path("D:\\56430add55fb2150-ff50ccb43080b2bd_838327854_data.0.parq");
		@SuppressWarnings("resource")
		AvroParquetReader<GenericRecord> reader = new AvroParquetReader<GenericRecord>(file);
		GenericRecord gr = reader.read();
		while (gr != null) {
			ByteBuffer number = (ByteBuffer) gr.get("user_number");
			ByteBuffer obj2 = (ByteBuffer) gr.get("sn");
			if (number != null && obj2 != null) {
				System.out.println(getStringV2(number));
				System.out.println(getStringV2(obj2));
			}
			gr = reader.read();
		}

	}

	public static String getStringV2(ByteBuffer buf) {
		if (buf != null) {
			return StandardCharsets.UTF_8.decode(buf).toString();
		}
		return "";
	}

	public static String getStringV1(ByteBuffer buffer) {
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
			// 用这个的话，只能输出来一次结果，第二次显示为空
			// charBuffer = decoder.decode(buffer);
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
