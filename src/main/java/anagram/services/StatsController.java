package anagram.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    @RequestMapping("/stats")
    public Statistics getStats() {
        return Statistics.getInstance();
    }
}
