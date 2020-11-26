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
    ////--------------------remover privilegios-------------------------------------------
    @GetMapping(value = "/remove_Privilg_EditFILE/{id}", produces = "application/json")
    public @ResponseBody
    void pri_addFILE(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        user.setActive(false);
        userRepository.save(user);
    }
    //--------//
    @GetMapping(value = "/remove_Privilg_Deletar_FILE/{id}", produces = "application/json")
    public @ResponseBody
    void pri_remove_FILE(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        user.setRemoveFileAuthorization(false);
        userRepository.save(user);
    }

    @GetMapping(value = "/remove_Privilg_addUSER/{id}", produces = "application/json")
    public @ResponseBody
    void pri_addUSER(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        user.setAddUserAuthorization(false);
        userRepository.save(user);
    }

    @GetMapping(value = "/remove_Privilg_Delete_USER/{id}", produces = "application/json")
    public @ResponseBody
    void pri_Delete_USER(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        user.setDeleteUserAuthorization(false);
        userRepository.save(user);
    }


    @GetMapping(value = "/remove_Privilg_View_Link/{id}", produces = "application/json")
    public @ResponseBody
    void pri_View_Link(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        user.setAcess_userAuthorization(false);
        userRepository.save(user);
    }
//----------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/deleteUser/{id}", produces = "application/json")
    public @ResponseBody
      void showLogin(@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        String id2 = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(id2));
        userRepository.delete(user);
    }

    ////----------------------------------------------------------------------------------------------------

    @GetMapping(value = "/adicionar_Privilegios_de_Acessar_Pagina_De_Usuarios/{opcao}/{id}", produces = "application/json")
    public @ResponseBody
    void pivilegio_Acesso_a_Pagina(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao,@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        String  Id = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(Id));
        user.setAcess_userAuthorization(Opcao);
        userRepository.save(user);
    }

    @GetMapping(value = "/adicionar_Privilegios_de_editar_um_Documento/{opcao}/{id}", produces = "application/json")
    public @ResponseBody
    void pivilegio_editar_documento(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao,@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();;
        String  Id = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(Id));
        user.setActive(Opcao);
        userRepository.save(user);
    }


    @GetMapping(value = "/adicionar_Privilegios_de_remover_Documento/{opcao}/{id}", produces = "application/json")
    public @ResponseBody
    void pivilegio_remover_documento(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao,@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();;
        String  Id = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(Id));
        user.setRemoveFileAuthorization(Opcao);
        userRepository.save(user);
    }

    @GetMapping(value = "/adicionar_Privilegios_de_removerUsuario/{opcao}/{id}", produces = "application/json")
    public @ResponseBody
    void pivilegio_Remover_User(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao,@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();;
        String  Id = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(Id));
        user.setDeleteUserAuthorization(Opcao);
        userRepository.save(user);
    }

    @GetMapping(value = "/adicionar_Privilegios_de_Adicionar_Usuario/{opcao}/{id}", produces = "application/json")
    public @ResponseBody
    void pivilegio_adicao_User(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao,@Nullable @PathVariable(name="id") Optional<String> id)throws IOException {
        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();;
        String  Id = id.get().equals("null") ? null : id.get();
        User user=userRepository.getOne(Integer.parseInt(Id));
        user.setAddUserAuthorization(Opcao);
        userRepository.save(user);
    }

    ////----------------------------------------------------------------------------------------------------

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
