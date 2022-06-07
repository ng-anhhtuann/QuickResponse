package com.api.QuickResponse.Controllers;

import com.api.QuickResponse.Model.User;
import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "getall")
public class GetAllMethod {
    @GetMapping
    public List<User> getall() {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getall();
    }
}
