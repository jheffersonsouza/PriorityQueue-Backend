package com.jheffersonsouza.priorityqueue.controllers;

import com.jheffersonsouza.priorityqueue.dto.User;
import com.jheffersonsouza.priorityqueue.services.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class QueueController {
    private final QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping
    @Operation(summary = "Adiciona um usuário na fila",
            responses = {
                    @ApiResponse(responseCode = "201"),
            })
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        queueService.add(user);
        int userIndex = queueService.getQueue().indexOf(user);
        return ResponseEntity.created(URI.create("/users/" + userIndex)).body(userIndex);
    }

    @GetMapping
    @Operation(summary = "Pega a lista de usuários na fila",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "204", description = "Fila vazia")
            })
    public ResponseEntity<ArrayList<User>> getQueue() {
        return queueService.getQueue().isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(queueService.getQueue());
    }

    @GetMapping("/size")
    @Operation(summary = "Quantia de usuários na fila.",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    public int getQueueSize() {
        return queueService.size();
    }

    @GetMapping("/{index}")
    @Operation(summary = "Posição de um suário na fila.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")

            })
    public ResponseEntity<User> getUser(@PathVariable int index) {
        try {
            User u = queueService.getUser(index);
            return ResponseEntity.ok(u);
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
