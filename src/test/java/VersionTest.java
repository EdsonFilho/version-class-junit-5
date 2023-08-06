import com.edsoncarmo.example.Version;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {

    @Test
    public void testNullArgument() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Version(null));
        assertEquals("version must not be null!", exception.getMessage());
    }

    @Test
    public void testEmptyArgument() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Version(""));
        assertEquals("version must not be empty!", exception.getMessage());
    }

    @Test
    public void testIllegalArgument() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Version("3.0.0-"));
        assertEquals("version must match: 'major.minor.patch(-SNAPSHOT)'!", exception.getMessage());
    }

    @Test
    public void testIsSnapshotArgument() {
        Version v = new Version("3.0.0-SNAPSHOT");
        assertEquals(true, v.isSnapshot(), "Version should be SNAPSHOT");
    }

    @Test
    public void testSnapshotArgument() {
        Version v = new Version("3.0.0");
        assertEquals(false, v.isSnapshot(), "Version should not be SNAPSHOT");
    }

    @Test
    public void testGreaterThan() {
        Version v1 = new Version("3.2.0");
        Version v2 = new Version("3.2.1");
        assertEquals(true, v1.compareTo(v2) < 0, "3.2.1 should be greater than 3.2.0");
    }

    @Test
    public void testMinorThan() {
        Version v1 = new Version("3.2.1");
        Version v2 = new Version("3.2.0");
        assertEquals(false, v1.compareTo(v2) < 0,"3.2.0 should be smaller than 3.2.1");
    }

    @Test
    public void testEqual() {
        Version v1 = new Version("3.2.0");
        Version v2 = new Version("3.2.0");
        assertEquals(true, v1.compareTo(v2) == 0, "3.2.0 should be equal than 3.2.0");
    }

    @Test
    public void testGreaterThanSnapshot() {
        Version v1 = new Version("3.2.0-SNAPSHOT");
        Version v2 = new Version("3.2.0");
        assertEquals(true, v1.compareTo(v2) < 0, "3.2.0 should be greater than 3.2.0-SNAPSHOT");
    }

    @Test
    public void testMinorThanSnapshot() {
        Version v1 = new Version("3.2.0");
        Version v2 = new Version("3.2.0-SNAPSHOT");
        assertEquals(false, v1.compareTo(v2) < 0,"3.2.0 should be smaller than 3.2.0-SNAPSHOT");
    }

    @Test
    public void testEqualThanSnapshot() {
        Version v1 = new Version("3.2.0-SNAPSHOT");
        Version v2 = new Version("3.2.0-SNAPSHOT");
        assertEquals(true, v1.compareTo(v2) == 0, "3.2.0-SNAPSHOT should be equals than 3.2.0-SNAPSHOT");
    }
}
