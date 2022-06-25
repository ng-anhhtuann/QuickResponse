package com.api.QuickResponse.Controllers;

import com.api.QuickResponse.Model.Register.ItemRegister;
import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/register")
public class RegisterMethod {
    @PostMapping
    public Object register(@RequestBody ItemRegister itemRegister) throws SQLException {
        QuickResponseRepository quickResponseRepository = QuickResponseRepository.getQuickResponseRepository();
        return quickResponseRepository.register(itemRegister);
    }
}
