package ua.artcode.billapp.controller;

import org.springframework.web.bind.annotation.*;
import ua.artcode.billapp.security.model.JwtUser;
import ua.artcode.billapp.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser){
        return jwtGenerator.generate(jwtUser);
    }
}
