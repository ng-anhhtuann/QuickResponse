package com.api.QuickResponse.Controllers;

import com.api.QuickResponse.Model.ItemRegister;
import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/register")
public class RegisterMethod {
    @GetMapping
    public Object register(ItemRegister itemRegister){
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.register(itemRegister);
    }
}
