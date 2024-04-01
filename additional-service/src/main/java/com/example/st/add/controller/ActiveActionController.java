package com.example.st.add.controller;

import com.example.st.add.service.ActiveActionService;
import lombok.AllArgsConstructor;
import org.example.st.model.ActiveAction;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления действиями
 */
@RestController
@RequestMapping("/action")
@AllArgsConstructor
public class ActiveActionController {
    private final ActiveActionService actionService;

    /**
     * Метод для добавления нового действия.
     *
     * @param action Действие, которое необходимо добавить.
     * @return Добавленное действие.
     */
    @PostMapping("")
    public ActiveAction addAction(@RequestBody ActiveAction action) {
        return actionService.saveAction(action);
    }

    /**
     * Метод для получения действия по его идентификатору.
     *
     * @param id Идентификатор действия.
     * @return Действие с указанным идентификатором.
     */
    @GetMapping("")
    public ActiveAction getActionById(@RequestParam Long id) {
        return actionService.findActionById(id);
    }
}