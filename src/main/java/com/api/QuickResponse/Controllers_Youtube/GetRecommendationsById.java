package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "getrcm", params = {"videoId"})
public class GetRecommendationsById {
    @GetMapping
    public Object getRecommendById(@RequestParam(value = "videoId") String id) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getRecommendation(id);
    }
}
