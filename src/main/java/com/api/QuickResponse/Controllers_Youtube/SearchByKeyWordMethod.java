package com.api.QuickResponse.Controllers_Youtube;

import com.api.QuickResponse.Repository.QuickResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/search", params = {"query"})
public class SearchByKeyWordMethod {
    @GetMapping
    private Object searchMachine(@RequestParam(value = "query") String query) throws IOException, InterruptedException {
        QuickResponseRepository quickResponseRepository = new QuickResponseRepository();
        return quickResponseRepository.searchByKeyword(query);
    }

}
