package gb.demo;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getMessage() {
        return  "Whats'up dude";
    }
}
