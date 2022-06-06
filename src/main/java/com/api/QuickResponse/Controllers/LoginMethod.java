package com.api.QuickResponse.Controllers;

import com.api.QuickResponse.Model.ItemLogin;
import com.api.QuickResponse.Model.ItemRegister;
import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginMethod {
    @PostMapping
    public Object login(ItemLogin itemLogin){
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.login(itemLogin);
    }
}
