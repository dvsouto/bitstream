package br.com.bitnary.bitstream.application.user.usecases;

import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.domain.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class FindAllUsersUseCase {
    @Autowired
    private UserRepository userRepository;

    public void execute() {
        List<User> users = userRepository.findAll();

        users.stream()
                .forEach(u -> System.out.println("User: " + u.getName()));

        System.out.println("ToString Find = " + userRepository.findByEmail("davi.souto@gmail.com").get());
        System.out.println("Email Find = " + userRepository.findByEmail("davi.souto@gmail.com").get().getName());


    }
}
