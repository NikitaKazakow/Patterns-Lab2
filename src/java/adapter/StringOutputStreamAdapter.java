package adapter;

import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class StringOutputStreamAdapter {

    public OutputStream stringArrayToOutputStream(String [] values) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            for (String value : values) {
                byteArrayOutputStream.write(value.getBytes(StandardCharsets.UTF_8));
            }
            return byteArrayOutputStream;
        }
    }

    public String OutputStreamToStringArray(OutputStream outputStream) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream) {
            return byteArrayOutputStream.toString();
        }
    }
}
