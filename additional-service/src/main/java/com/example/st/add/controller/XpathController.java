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

    @GetMapping("/id")
    public Xpath getByParam(@RequestParam(required = false) Long id) {
        return xpathService.findXpathById(id);
    }

    @GetMapping("/link")
    public Xpath getByWebsite(@RequestParam String link) {
        return xpathService.findByWebsiteLink(link);
    }

    @PostMapping("")
    public Xpath addXpath(@RequestBody Xpath xpath) {
        return xpathService.saveXpath(xpath);
    }

}
