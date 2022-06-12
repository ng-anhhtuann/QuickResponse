package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "getrcm", params = {"id"})
public class GetRecommendationsById {
    @GetMapping
    public Object getRecommendById(@RequestBody String id) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getRecommendation(id);
    }
}
