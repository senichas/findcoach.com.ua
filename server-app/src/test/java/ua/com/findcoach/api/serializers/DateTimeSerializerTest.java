package ua.com.findcoach.api.serializers;

import com.fasterxml.jackson.core.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateTimeSerializerTest {

    private DateTimeSerializer dateTimeSerializer;

    private TestJsonGenerator jsonGenerator;

    @Before
    public void setup() {
        dateTimeSerializer = new DateTimeSerializer();
        jsonGenerator = new TestJsonGenerator();
    }

    @Test
    public void convert_date_to_js_format() throws Exception {
        LocalDateTime date = LocalDateTime.of(2016, 6, 12, 8, 54, 22);

        dateTimeSerializer.serialize(date, jsonGenerator, null);
        assertThat(jsonGenerator.getResult(), equalTo("2016-06-12T08:54:22"));

    }

    private class TestJsonGenerator extends JsonGenerator {

        private String result;

        public String getResult() {
            return result;
        }

        @Override
        public JsonGenerator setCodec(ObjectCodec oc) {
            return null;
        }

        @Override
        public ObjectCodec getCodec() {
            return null;
        }

        @Override
        public Version version() {
            return null;
        }

        @Override
        public JsonGenerator enable(Feature f) {
            return null;
        }

        @Override
        public JsonGenerator disable(Feature f) {
            return null;
        }

        @Override
        public boolean isEnabled(Feature f) {
            return false;
        }

        @Override
        public int getFeatureMask() {
            return 0;
        }

        @Override
        public JsonGenerator setFeatureMask(int values) {
            return null;
        }

        @Override
        public JsonGenerator useDefaultPrettyPrinter() {
            return null;
        }

        @Override
        public void writeStartArray() throws IOException {

        }

        @Override
        public void writeEndArray() throws IOException {

        }

        @Override
        public void writeStartObject() throws IOException {

        }

        @Override
        public void writeEndObject() throws IOException {

        }

        @Override
        public void writeFieldName(String name) throws IOException {

        }

        @Override
        public void writeFieldName(SerializableString name) throws IOException {

        }

        @Override
        public void writeString(String text) throws IOException {
            result = text;
        }

        @Override
        public void writeString(char[] text, int offset, int len) throws IOException {

        }

        @Override
        public void writeString(SerializableString text) throws IOException {

        }

        @Override
        public void writeRawUTF8String(byte[] text, int offset, int length) throws IOException {

        }

        @Override
        public void writeUTF8String(byte[] text, int offset, int length) throws IOException {

        }

        @Override
        public void writeRaw(String text) throws IOException {

        }

        @Override
        public void writeRaw(String text, int offset, int len) throws IOException {

        }

        @Override
        public void writeRaw(char[] text, int offset, int len) throws IOException {

        }

        @Override
        public void writeRaw(char c) throws IOException {

        }

        @Override
        public void writeRawValue(String text) throws IOException {

        }

        @Override
        public void writeRawValue(String text, int offset, int len) throws IOException {

        }

        @Override
        public void writeRawValue(char[] text, int offset, int len) throws IOException {

        }

        @Override
        public void writeBinary(Base64Variant bv, byte[] data, int offset, int len) throws IOException {

        }

        @Override
        public int writeBinary(Base64Variant bv, InputStream data, int dataLength) throws IOException {
            return 0;
        }

        @Override
        public void writeNumber(int v) throws IOException {

        }

        @Override
        public void writeNumber(long v) throws IOException {

        }

        @Override
        public void writeNumber(BigInteger v) throws IOException {

        }

        @Override
        public void writeNumber(double v) throws IOException {

        }

        @Override
        public void writeNumber(float v) throws IOException {

        }

        @Override
        public void writeNumber(BigDecimal v) throws IOException {

        }

        @Override
        public void writeNumber(String encodedValue) throws IOException {

        }

        @Override
        public void writeBoolean(boolean state) throws IOException {

        }

        @Override
        public void writeNull() throws IOException {

        }

        @Override
        public void writeObject(Object pojo) throws IOException {

        }

        @Override
        public void writeTree(TreeNode rootNode) throws IOException {

        }

        @Override
        public JsonStreamContext getOutputContext() {
            return null;
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public void close() throws IOException {

        }

    }
}