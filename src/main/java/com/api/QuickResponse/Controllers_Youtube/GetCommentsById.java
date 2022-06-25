package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "getcomments", params = {"videoId"})
public class GetCommentsById {
    @GetMapping
    public Object getCmtById(@RequestParam(value = "videoId") String id) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.getComments(id);
    }
}
