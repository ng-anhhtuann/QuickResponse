package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "getvideodetails", params = {"videoId"})
public class GetVideoDetailsById {
    @GetMapping
    public Object getVideoDetails(@RequestParam(value = "videoId") String id) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getDetails(id);
    }
}
