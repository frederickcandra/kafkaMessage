package com.event.kafkamessage.controller;

import com.event.kafkamessage.model.User;
import com.event.kafkamessage.service.EventProducerService;
import com.event.kafkamessage.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

        private final UserService userService;
        private final EventProducerService eventProducerService;

        public UserController(UserService userService, EventProducerService eventProducerService) {
            this.userService = userService;
            this.eventProducerService = eventProducerService;
        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody User user) {
            String result = userService.register(user);
            eventProducerService.sendRegisterEvent(String.valueOf(user.getUsername()));
            return ResponseEntity.ok(result);
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody User user) {
            String result = userService.login(user);
            eventProducerService.sendLoginEvent(String.valueOf(user.getUsername()));
            return ResponseEntity.ok(result);
        }
}
