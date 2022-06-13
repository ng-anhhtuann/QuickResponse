package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "getsub", params = {"videoId"})
public class GetSubtitlesByid {

    @GetMapping
    public Object getSubById(@RequestParam(value = "videoId") String id) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getSubtitles(id);
    }
}
