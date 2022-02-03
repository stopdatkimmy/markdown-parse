import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void tester() throws IOException {
        List<String> expected = List.of("[]","[]","[]","[https://something.com, some-page.html]");
        List<String> list = List.of("empty-file.md", "empty-link.md", "image-file.md", "test-file.md");
        for (int i=0; i<list.size(); i++) {
            Path fileName = Path.of(list.get(i));
            String contents = Files.readString(fileName);
            ArrayList<String> links = MarkdownParse.getLinks(contents);
            assertEquals(expected.get(i), links.toString());
        }
    }

}