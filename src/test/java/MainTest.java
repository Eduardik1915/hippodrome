import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Disabled
    @Timeout(value = 22)
    public void methodIisExecutedNoLongerThan22Seconds() throws Exception {
        Main.main(null);
    }
}
