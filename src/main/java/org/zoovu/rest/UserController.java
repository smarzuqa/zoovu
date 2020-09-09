package org.zoovu.rest;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zoovu.model.User;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserRepository repository;

    @GetMapping("/login")
    public User login(@RequestParam(value = "username") String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            user = new User(username, 0, LocalDate.now());
            repository.save(user);
        }
        return user;
    }

    @GetMapping("/leaderboard/{id}")
    public List<User> leaderboard(@PathVariable int id) {
        switch (id) {
            case 1:
                return repository.findAll(UserSpecs.dailyLeaderboard());
            case 2:
                return repository.findAll(UserSpecs.weeklyLeaderboard());
            case 3:
                return repository.findAll(UserSpecs.yearlyLeaderboard());
            case 4:
                return repository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        }
        throw new RuntimeException("You need to provide a valid value (1-4) to get the leaderboard.");
    }
}
