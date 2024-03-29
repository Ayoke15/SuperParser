package com.example.st.add.controller;


import com.example.st.add.service.XpathService;
import lombok.AllArgsConstructor;
import org.example.st.model.Xpath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xpath")
@AllArgsConstructor
public class XpathController {
    private final XpathService xpathService;

    @GetMapping("/all")
    public List<Xpath> getAll() {
        return xpathService.findAllXpath();
    }

    @GetMapping("/get-by-id")
    public Xpath getById(@RequestParam Long id) {
        return xpathService.findXpathById(id);
    }

    @GetMapping("/get-by-link")
    public Xpath getByWebsite(@RequestParam String link) {
        return xpathService.findByWebsiteLink(link);
    }

    @PostMapping("")
    public Xpath addXpath(@RequestBody Xpath xpath) {
        return xpathService.saveXpath(xpath);
    }

    @PatchMapping("/edit")
    public Xpath editXpath(@RequestParam Long id,
                           @RequestBody Xpath xpath) {
        xpath.setId(id);
        return xpathService.saveXpath(xpath);
    }

}
