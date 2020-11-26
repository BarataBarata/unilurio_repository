package mz.ac.unilurio.repository.controller;

import mz.ac.unilurio.repository.model.*;
import mz.ac.unilurio.repository.repository.DocumentRepository;
import mz.ac.unilurio.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Nullable;
import java.io.IOException;

import java.util.*;

@Controller
public class HomeController {


    @Autowired
    private MailService mailService;


    @Autowired
    private DocumentRepository repository;
    private UserRepository userRepository;

    @GetMapping(value = "/dashboard")
    public String dashboard() throws Exception {
        return "dashboard.html";
    }


    @GetMapping(value = "/sendMail/{assunto}/{mensagem}/{email}", produces = "application/json")
    public @ResponseBody   String showEmail(@Nullable @PathVariable(name="assunto") Optional<String>  assunto, @Nullable @PathVariable(name="mensagem") Optional<String>  mensagem, @Nullable @PathVariable(name="email") Optional<String>  email)throws IOException {
        String Assunto = assunto.get().equals("null") ? null : assunto.get();
        String Mensagen = mensagem.get().equals("null") ? null : mensagem.get();
        String Email = email.get().equals("null") ? null : email.get();

        String message = Mensagen +"\n\n Datos de contacto: " + "\n Assunto: " + "\nE-mail: " + Email;
        mailService.sendMail("bbarata@unilurio.ac.mz",Email,Assunto,message);

        return "dashboard";
    }


    @GetMapping(value = "/")
    public String showPage() throws Exception {
        return "home.html";
    }

    @GetMapping(value = "/retornaDadosAfiltrar", produces = "application/json")
    public @ResponseBody ArrayList retorna(){
        Pesquisa pesquisa=new Pesquisa();
        ArrayList<String> listaDosDados=new ArrayList();
        listaDosDados.add(Pesquisa.getTitulo());
        listaDosDados.add(Pesquisa.getAno());
        listaDosDados.add(Pesquisa.getCategoria());


        return listaDosDados;
    }



    @GetMapping(value = "/retornaDadosLoginAfiltrar", produces = "application/json")
    public @ResponseBody ArrayList retornaDadosLogin(){
        Login login=new Login();
        ArrayList<String> listaLogin=new ArrayList();
        listaLogin.add(login.getEmail());
        listaLogin.add(login.getPassword());

        return listaLogin;
    }

//
//
//    @GetMapping(value = "/sendOpcaoOcultarUsuarioLink/{opcao}", produces = "application/json")
//    public @ResponseBody   void showUse(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
//        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();
//        Opcao opcao1=new Opcao();
//        opcao1.setIsOcultarOpUsuario(Opcao);
//    }
//
//    @GetMapping(value = "/RetornoOpcaoOcultarUsuario_Link", produces = "application/json")
//    public @ResponseBody boolean retornoOcultarOpcaoUsuario(){
//        Opcao opcao1=new Opcao();
//        return opcao1.getIsOcultarOpUsuario();
//    }
//
//    @GetMapping(value = "/send_OpcaoOcultarPagina_Usuarios/{opcao}", produces = "application/json")
//    public @ResponseBody   void showUser(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
//        Boolean Opcao = opcao.get().equals("null") ? null : opcao.get();
//        Opcao opca=new Opcao();
//        opca.setIsOcultarAddUser(Opcao);
//    }
//
//
//    @GetMapping(value = "/OpcaoOcultarOpcaoEditarDocumento", produces = "application/json")
//    public @ResponseBody void OcultarOpcao(){
//        Opcao opcao1=new Opcao();
//         opcao1.setOcultarOpDocumeno(true);
//    }
//
//    @GetMapping(value = "/OpcaoOcultarOpcaoEliminarDocumento", produces = "application/json")
//    public @ResponseBody void Ocultar(){
//           Opcao opcao=new Opcao();
//           opcao.oculta(true);
//    }

    @GetMapping(value = "/get_Ocultar_Opcao_Adicionar_Usuarios", produces = "application/json")
    public @ResponseBody boolean get_Ocultar_Opcao_Adicionar_Usuarios(){
        Opcao opcao=new Opcao();
        return opcao.getOcultar_Opcao_Adicionar_Usuario();
    }

    @GetMapping(value = "/send_Ocultar_Op_Adicionar_Usuario/{opcao}", produces = "application/json")
    public @ResponseBody   void send_Ocultar_Op_Adicionar_Usuario(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        Opcao opca=new Opcao();
        opca.setOcultar_Opcao_Adicionar_Usuario(Opcao);
    }


    @GetMapping(value = "/get_Ocultar_Opcao_Remover_Usuarios", produces = "application/json")
    public @ResponseBody boolean get_Ocultar_Opcao_Remover_Usuarios(){
        Opcao opcao=new Opcao();
        return opcao.getOcultar_Opcao_Remover_Usuario();
    }

    @GetMapping(value = "/send_Ocultar_Op_Remover_Usuario/{opcao}", produces = "application/json")
    public @ResponseBody   void send_Ocultar_Op_Remover_Usuario(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        Opcao opca=new Opcao();
        opca.setOcultar_Opcao_Remover_Usuario(Opcao);
    }



    @GetMapping(value = "/getOpcaoOcultarPagina_Usuarios", produces = "application/json")
    public @ResponseBody boolean getOcultarOpcaoAddUsuario(){
        Opcao opcao=new Opcao();
        return opcao.getOcultarLink_AddUser();
    }

    @GetMapping(value = "/send_Op_OcultarPagina_Usuario/{opcao}", produces = "application/json")
    public @ResponseBody   void send_Op_OcultarPagina_Usuario(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        Opcao opca=new Opcao();
        opca.setOcultarLink_AddUser(Opcao);
    }


    /// ocultar opcao eliminar documento

    @GetMapping(value = "/send_Op_EliminarDocumento/{opcao}", produces = "application/json")
    public @ResponseBody   void send_Op_EliminarDocumento(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        Opcao opca=new Opcao();
        opca.setOcultarOpcaoEliminarDocumento(Opcao);
    }

    @GetMapping(value = "/send_Op_EditarDocumento/{opcao}", produces = "application/json")
    public @ResponseBody   void send_Op_EditarDocumento(@Nullable @PathVariable(name="opcao") Optional<Boolean> opcao)throws IOException {
        boolean Opcao = opcao.get().equals("null") ? Boolean.parseBoolean(null) : opcao.get();
        Opcao opca=new Opcao();
        opca.setOcultar_Op_Editar_Documeno(Opcao);
    }


    @GetMapping(value = "/recebeDadosAfiltrar/{year}/{category}/{search}", produces = "application/json")
    public @ResponseBody   ArrayList showLogin(@Nullable @PathVariable(name="year") Optional<String>  year, @Nullable @PathVariable(name="category") Optional<String>  category, @Nullable @PathVariable(name="search") Optional<String>  search)throws IOException {
        String tempYear = year.get().equals("null") ? null : year.get();
        String tempCategory = category.get().equals("null") ? null : category.get();
        String tempSearch = search.get().equals("null") ? null : search.get();

        Pesquisa pesquisa=new Pesquisa(tempYear,tempSearch,tempCategory);
        ArrayList<String> lista=new ArrayList();

        lista.add(Pesquisa.getTitulo());
        lista.add(Pesquisa.getAno());
        lista.add(Pesquisa.getCategoria());


      return  lista;
    }


    @GetMapping(value = "/recebeDadosLoginAfiltrar/{email}/{password}", produces = "application/json")
    public @ResponseBody   ArrayList showbLogin(@Nullable @PathVariable(name="email") Optional<String>  email, @Nullable @PathVariable(name="password") Optional<String> password)throws IOException {
        String Email = email.get().equals("null") ? null : email.get();
        String Password = password.get().equals("null") ? null : password.get();

        Login login=new Login(Email,Password);
        ArrayList<String> lista=new ArrayList();

        lista.add(login.getEmail());
        lista.add(login.getPassword());

        return  lista;
    }

    @GetMapping(value = "/years/{year}/{category}/{search}", produces = "application/json")
    public @ResponseBody List<Document> showYears(@Nullable @PathVariable(name="year") Optional<String>  year, @Nullable @PathVariable(name="category") Optional<String>  category, @Nullable @PathVariable(name="search") Optional<String>  search)throws IOException {
        String tempYear = year.get().equals("null") ? null : year.get();
        String tempCategory = category.get().equals("null") ? null : category.get();
        String tempSearch = search.get().equals("null") ? null : search.get();

        List<Document> response = repository.findByFilter(tempYear, tempCategory, "GROUP BY d.year", tempSearch);
        return  response;
    }


    @GetMapping(value = "/categories/{year}/{category}/{search}", produces = "application/json")
    public @ResponseBody List<Document> showCategories(@Nullable @PathVariable(name="year") Optional<String>  year, @Nullable @PathVariable(name="category") Optional<String>  category, @Nullable @PathVariable(name="search") Optional<String>  search)throws IOException {
        String tempYear = year.get().equals("null") ? null : year.get();
        String tempCategory = category.get().equals("null") ? null : category.get();
        String tempSearch = search.get().equals("null") ? null : search.get();

        List<Document> response = repository.findByFilter(tempYear,tempCategory, "GROUP BY d.category",tempSearch);
        return  response;
    }

    @GetMapping(value = "/listdbfiles/{year}/{category}/{search}", produces = "application/json")
    public @ResponseBody
    ResponseEntity<Object> listDbFiles(@Nullable @PathVariable(name="year") Optional<String>  year, @Nullable @PathVariable(name="category") Optional<String>  category, @Nullable @PathVariable(name="search") Optional<String>  search)throws IOException {
        String tempYear = year.get().equals("null") ? null : year.get();
        String tempCategory = category.get().equals("null") ? null : category.get();
        String tempSearch = search.get().equals("null") ? null : search.get();

        Iterable<Document> documents = repository.findByFilter(tempYear, tempCategory, null,tempSearch);

        List<FileDisplay> list = new ArrayList<>();

        documents.forEach(document -> {

            FileDisplay display = new FileDisplay();
            display.setId(document.getGoogleId());

            String title = "<a href=\""+document.getUrl()+"\" target=\"_blank\">"+document.getTitle()+"</a>";

            display.setTitle(title);


            display.setYear(document.getYear());
            display.setUrl(document.getUrl());
            Opcao opcao =new Opcao();
//            String html = "<a href=\"#\" rel=\"" + document.getGoogleId()+ "\" class=\"view btn btn-default\" title=\"View\" data-toggle=\"tooltip\">Ver</a>";
            String html ="";
            if(opcao.getOcultar_Op_Editar_Documeno()){
              html += "&nbsp;&nbsp;<a href=\"#\" rel=\"" + document.getGoogleId() + "\" class=\"edit btn btn-default\" title=\"Edit\" data-toggle=\"tooltip\">Editar</a>";

            }else {
                html += "&nbsp;&nbsp;<a href=\"#\" style=\"display:none;\" rel=\"" + document.getGoogleId() + "\" class=\"edit btn btn-default\" title=\"Edit\" data-toggle=\"tooltip\">Editar</a>";

            }
            if (opcao.getOcultarOpcaoEliminarDocumento()){
                html += "&nbsp;&nbsp;<a href=\"#\" rel=\"" + document.getGoogleId()+ "\" class=\"delete btn btn-default\" title=\"Delete\" data-toggle=\"tooltip\">Remover</a>";

            }else {
              html += "&nbsp;&nbsp;<a href=\"#\" style=\"display:none;\"  rel=\"" + document.getGoogleId()+ "\" class=\"delete btn btn-default\" title=\"Delete\" data-toggle=\"tooltip\">Remover</a>";

            }
            String attach = "";
//            for (Attachment attachment:document.getAttachmentCollection()) {
////                attach+="<div><a href=\""+attachment.getUrl()+"\" target=\"_blank\">"+attachment.getTitle()+"</a></div>";
////            }

            display.setAttachments(attach);
            display.setAction(html);
            list.add(display);

        });


        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data",  list);
        result.put("recordsTotal", list.size());
        result.put("draw", 1);
        result.put("recordsFiltered", list.size());

        return new ResponseEntity<Object>(result, HttpStatus.OK);

//        return  documents;
    }


}
