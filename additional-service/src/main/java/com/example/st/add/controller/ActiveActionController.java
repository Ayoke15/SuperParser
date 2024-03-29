package com.example.st.add.controller;

import com.example.st.add.service.ActiveActionService;
import lombok.AllArgsConstructor;
import org.example.st.model.ActiveAction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/action")
@AllArgsConstructor
public class ActiveActionController {
    private final ActiveActionService actionService;

    @PostMapping("")
    public ActiveAction addAction(@RequestBody ActiveAction action) {
        return actionService.saveAction(action);
    }

    @GetMapping("")
    public ActiveAction getActionById(@RequestParam Long id) {
        return actionService.findActionById(id);
    }
}