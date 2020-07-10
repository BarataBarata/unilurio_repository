package mz.ac.unilurio.repository.controller;

import mz.ac.unilurio.repository.model.Pesquisa;
import mz.ac.unilurio.repository.model.User;
import mz.ac.unilurio.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping(value = "/returnOllUserr", produces = "application/json")
    public @ResponseBody
    List<User> user() throws IOException {
        List<User> user = userRepository.findByUser();
        return user;
    }

    @GetMapping(value = "/usuario")
    public String showPage() throws Exception {
        return "Usuarios.html";
    }

    @GetMapping(value = "/addUser")
    public String showPage2() throws Exception {
        return "AddUser.html";
    }

    @GetMapping(value = "/deleteUser/{id}", produces = "application/json")
    public @ResponseBody
      void showLogin(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        userRepository.delete(user);
    }


    @GetMapping(value = "/addteUser/{nameUser}/{emailUser}/{passwordUser}", produces = "application/json")
    public @ResponseBody
    void adduser(@Nullable @PathVariable(name="nameUser") Optional<String> nameUser,@Nullable @PathVariable(name="emailUser") Optional<String> emailUser,@Nullable @PathVariable(name="passwordUser") Optional<String> passwordUser)throws IOException {
        String nameUsers = nameUser.get().equals("null") ? null : nameUser.get();
        String EmailUsers = emailUser.get().equals("null") ? null : emailUser.get();
        String PasswordUser = passwordUser.get().equals("null") ? null : passwordUser.get();
        User user =new User();

        user.setFirstName(nameUsers);
        user.setEmail(EmailUsers);
        user.setPassword(PasswordUser);
        userRepository.save(user);
    }




}
