package at.spengergasse.haas;

import at.spengergasse.haas.pos.planner.HifPosSpringBootApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HifPosSpringBootApplication.class)
public class HifPosSpringBootApplicationTests {

    @Test
    public void contextLoads() {
    }

}
