import org.apache.commons.io.IOUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TikaDetectorTest {

    class NonMarkableStream extends BufferedInputStream {
        NonMarkableStream(InputStream is) {
            super(is);
        }

        @Override
        public synchronized void mark(int readlimit) {
        }

        @Override
        public synchronized void reset() throws IOException {
        }

        @Override
        public boolean markSupported() {
            return false;
        }
    }

    @Test
    public void testInputStreamChange(){
        TikaConfig config = TikaConfig.getDefaultConfig();
        Detector detector = config.getDetector();

        final String filename = "this-is-actually-a-wav-file.mp3";


        NonMarkableStream inputStream = null;
        NonMarkableStream originalStream = null;

        try {
            inputStream = new NonMarkableStream(getClass().getResourceAsStream(filename));
            originalStream = new NonMarkableStream(getClass().getResourceAsStream(filename));
            assertNotNull("Expecting stream to be found:" + filename, inputStream);
            assertNotNull("Expecting stream to be found:" + filename, originalStream);

            TikaInputStream stream = TikaInputStream.get(inputStream);

            Metadata metadata = new Metadata();
            metadata.add(Metadata.RESOURCE_NAME_KEY, filename);

            MediaType mediaType = detector.detect(stream, metadata);
            assertEquals("audio/x-wav", mediaType.toString());

            //THIS TEST FAILS
            assertTrue(IOUtils.contentEquals(inputStream, originalStream));

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail();
                }
            }

            if(originalStream != null) {
                try {
                    originalStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail();
                }
            }

        }
    }
}


