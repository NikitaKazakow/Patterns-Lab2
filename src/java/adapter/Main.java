package adapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class Main {

    private static final String [] testValues = new String[] {
            "Hello ",
            "my ",
            "dear ",
            "friend! "
    };

    public static void main(String[] args) {

        System.out.println("Test String values: ");
        for (String value : testValues) {
            System.out.print(value);
        }

        StringOutputStreamAdapter adapter = new StringOutputStreamAdapter();

        try (OutputStream outputStream = adapter.stringArrayToOutputStream(testValues)){

            System.out.println("\n\"stringToOutputStream\" method result:");
            for (byte byteValue : ( (ByteArrayOutputStream) outputStream).toByteArray()) {
                System.out.print((char) byteValue);
            }

            System.out.println("\n\"outputStreamToString\" method result:");
            System.out.println(adapter.OutputStreamToStringArray(outputStream));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
