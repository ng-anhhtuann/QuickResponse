package com.api.QuickResponse.Controllers;

import com.api.QuickResponse.Model.Login.ItemLogin;
import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/login")
public class LoginMethod {
    @PostMapping
    public Object login(@RequestBody ItemLogin itemLogin) throws SQLException {
        QuickResponseRepository quickResponseRepository = QuickResponseRepository.getQuickResponseRepository();
        return quickResponseRepository.login(itemLogin);
    }
}
