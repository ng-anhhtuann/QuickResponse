package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "getchanneldetails", params = {"videoId"})
public class GetChannelDetailsFromVideoId {
    @GetMapping
    public Object getChannelDetails(@RequestParam(value = "videoId") String videoId) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getChannelDetails(videoId);
    }
}
