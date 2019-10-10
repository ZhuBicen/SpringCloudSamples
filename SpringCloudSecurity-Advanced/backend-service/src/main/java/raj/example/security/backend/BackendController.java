package raj.example.security.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller.
 *
 * @author bharat
 */
@RestController
public class BackendController {

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello User!";
    }

    @GetMapping("/guest")
    public String guest() {
        return "Hello Guest!";
    }
}

