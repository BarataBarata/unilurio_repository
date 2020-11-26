
 function ocultar_Opcao_Remover_Usuario(opcao) {
     $.ajax({
         url:"/send_Ocultar_Op_Remover_Usuario/"+opcao,
     });
 }

 function ocultar_Opcao_Adicionar_Usuario(opcao) {
     $.ajax({
         url:"/send_Ocultar_Op_Adicionar_Usuario/"+opcao,
     });
 }

function ocultar_Opc_Remover_Documentos(opcao) {
    $.ajax({
        url:"/send_Op_EliminarDocumento/"+opcao,
    });
}

function ocultar_Opcao_AddUser_Link(opcao) {
    $.ajax({
        url:"/send_Op_OcultarPagina_Usuario/"+opcao,
    });
}

function ocultar_Opc_Editar_Documento(opcao) {
    $.ajax({
        url:"/send_Op_EditarDocumento/"+opcao,
    });
}


 function alertAddUser()
 {
     var x;
     var r=confirm("Adicionar! ?");
     if (r==true)
     {
         var nome_user=$('#nome_cad').val();
         var email_user=$('#email_cad').val();
         var password_user=$('#senha_cad').val();
         var opcao;

         $.ajax({
             url: "/addteUser/"+nome_user+"/"+email_user+"/"+password_user,
         });
         alert(" Usuario adicionado com sucesso...");


         $.ajax({
             url:"/returnOllUserr",
         }).done(function (data) {
             var id;
                 for (var i = 0; i < data.length; i++) {
                     if (email_user == data[i].email && data[i].password == password_user) {
                        id=data[i].id;
                        break;
                     }

                 }
             atribuir_Privilegios(id);
         });

         usuarios(); // algo por estudar
     }
     else
     {
     }
     //document.getElementById("demo").innerHTML=x;
 }

  function atribuir_Privilegios(opcao) { // adiciona atraveis de ip
      var opcao1=document.getElementsByName("p1");
      var opcao2=document.getElementsByName("p2");
      var opcao3=document.getElementsByName("p3");
      var opcao4=document.getElementsByName("p4");
      var opcao5=document.getElementsByName("p5");

      if(opcao1[0].checked==true){
          $.ajax({
              url: "/adicionar_Privilegios_de_editar_um_Documento/"+true+"/"+opcao,
          });
      }
      if(opcao2[0].checked==true){
          $.ajax({
              url: "/adicionar_Privilegios_de_remover_Documento/"+true+"/"+opcao,
          });
      }
      if(opcao3[0].checked==true){
          $.ajax({
              url: "/adicionar_Privilegios_de_removerUsuario/"+true+"/"+opcao,
          });
      }
      if(opcao4[0].checked==true){
          $.ajax({
              url: "/adicionar_Privilegios_de_Adicionar_Usuario/"+true+"/"+opcao,
          });
      }
      if(opcao5[0].checked==true){
          $.ajax({
              url: "/adicionar_Privilegios_de_Acessar_Pagina_De_Usuarios/"+true+"/"+opcao,
          });
      }
  }


 function ocultar_Link_Adicionar_Usuarios() {
     var html='';
     $.ajax({
         url: "/get_Ocultar_Opcao_Adicionar_Usuarios",
     }).done(function (data) {
         if(data==true) {
             html += '<a href="/addUser">Adicionar Usuarios</a>';
             $("#adicionar_usuario").html(html);
         }else{
             html +='<a href="/addUser" style="display:none">Adicionar Usuarios</a>';
             $("#adicionar_usuario").html(html);
         }
     });
 }


function ocultar_Link_Usuarios() {
    var html='';
    $.ajax({
        url: "/getOpcaoOcultarPagina_Usuarios",
    }).done(function (data) {
        if(data==true) {
            html += '<a href="/usuario">Usuarios</a>';
            $(".ver").html(html);
        }else{
            html += '<a href="/usuario" style="display:none">Usuarios</a>';
            $(".ver").html(html);

        }
    });
}

function confirmaLogin() {
    $.ajax({
        url:"/returnOllUserr",
    }).done(function (data) {
        var email=$('#email_login').val();
        var password=$('#senha_login').val();
        var opcao=0;

        if(email!='' && password!='') {
            for (var i = 0; i < data.length; i++) {
                if (email == data[i].email && data[i].password == password) {
                    if(data[i].removeFileAuthorization==true){
                        ocultar_Opc_Remover_Documentos(true);
                    }else{
                        ocultar_Opc_Remover_Documentos(false);
                    }
                    if(data[i].acess_userAuthorization==true){
                        ocultar_Opcao_AddUser_Link(true);
                    }else{
                        ocultar_Opcao_AddUser_Link(false);
                     }
                    // //........
                    if(data[i].active==true){
                          ocultar_Opc_Editar_Documento(true);
                    }else{
                          ocultar_Opc_Editar_Documento(false);
                    }


                    if(data[i].deleteUserAuthorization==true){
                        ocultar_Opcao_Remover_Usuario(true);
                    }else{
                        ocultar_Opcao_Remover_Usuario(false);
                    }

                    if(data[i].addUserAuthorization==true){
                        ocultar_Opcao_Adicionar_Usuario(true);
                    }else{
                        ocultar_Opcao_Adicionar_Usuario(false);
                    }
                    opcao = 1;
                }
            }
            if (opcao == 1) {

                window.location.href = "dashboard.html";

            } else {
                $('#aviso').html("Nao tem permissao para acessar a area...");
            }
        }

    });
}


$(document).ready(function () {
    ocultar_Link_Adicionar_Usuarios();
    ocultar_Link_Usuarios();
    initBinds();
    loadYears();
    loadCategories();
    refreshTable();
    function initBinds() {

        $('#filter').on('click', function () {

            var search = $('#filter_title').val();
            var queryString = window.location.search;
            var urlParams = new URLSearchParams(queryString);

            urlParams.set("search", search);

            redirect("?"+urlParams.toString());

        });

        $('.tickbox').on('click', function () {


            var queryString = window.location.search;
            var urlParams = new URLSearchParams(queryString);

            var params = [];
            $('.tickbox input:checked').each(function () {

                var values = $(this).val();
                var item = values.split('_');
                var param = item[0] + "=" + item[1] + "&";

                params.push(param);

            });

            var url = "/?";
            for (i = 0; i < params.length; i++) {
                url = url + params[i];
            }

            url = url.substring(0, url.length - 1);

            url += urlParams.has('search') ? (params.length>0) ? "&search="+urlParams.get('search'): "?search="+urlParams.get('search') : "";
            redirect(url);
        });



    }

    function redirect(url) {
        window.location = url;
    }

    $("#refreshFileButton").click(function () {
        refreshTable();
    });


    function loadCategories() {

        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        var year = urlParams.get('ano');
        var category = urlParams.get('category');
        var search = urlParams.get('search') == '' ? 'null' : urlParams.get('search');

        $.ajax({
            url: "/categories/"+year+"/"+category+"/"+search
        }).done(function (data) {
           // console.dir(data);

            var queryString = window.location.search;
            var urlParams = new URLSearchParams(queryString);

           var html = '<ul class="nobull">';

            for (var i = 0; i < data.length; i++) {
                html +='<li>';
                html +='<div class="tickbox">';
                var checked = (urlParams.has('category') && parseInt(urlParams.get('category')) == data[i].category.id) ? 'checked="checked"' : '';
                html +='<input type="checkbox" name="CategoryCheckFilter" id="'+data[i].category.category+'" value="category_'+data[i].category.id+'" '+checked+'/>';
                html +='<span> '+data[i].category.category+' </span>';
                html +='<span class="pull-right">'+data.length+'</span>';
                html +='</div>';
                html +='</li>';
            }

            html += "</ul>";
             $("#doc_categories").html(html);
            initBinds();
        });
    }


    function loadYears() {

        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        var year = urlParams.get('ano');
        var category = urlParams.get('category');
        var search = urlParams.get('search') == '' ? 'null' : urlParams.get('search');
        $.ajax({
            url: "/years/"+year+"/"+category+"/"+search
        }).done(function (data) {
            console.dir(data);

            var queryString = window.location.search;
            var urlParams = new URLSearchParams(queryString);

            var html = '<ul class="nobull">';

            for (var i = 0; i < data.length; i++) {
                html +='<li>';
                html +='<div class="tickbox">';
                var checked = (urlParams.has('ano') && parseInt(urlParams.get('ano')) == data[i].year) ? 'checked="checked"' : '';
                html +='<input type="checkbox" name="YearCheckFilter" id="'+data[i].year+'" value="ano_'+data[i].year+'" '+checked+'/>';
                html +='<span> '+data[i].year+' </span>';
                html +='<span class="pull-right">'+data.length+'</span>';
                html +='</div>';
                html +='</li>';
            }

            html += "</ul>";
            $("#years_more").html(html);
            initBinds();
        });
    }

});

function refreshTable() {
    var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var year = urlParams.get('ano');
    var category = urlParams.get('category');
    var search = urlParams.get('search') == '' ? 'null' : urlParams.get('search');

    //alert('ano='+year+'categforia='+category+"seacher="+search);
    $.ajax({

        url: "/listdbfiles/"+year+"/"+category+"/"+search
    }).done(function (data) {
        // console.dir(data);


        var tr = '';
        for (var i = 0; i < data.data.length; i++) {
            tr += '<tr>' +
                '<td>' + data.data[i].year + '</td>' +
                // '<td>' + data.data[i].title + '</td>';
                '<td><a href="' + data.data[i].url + '" target="_blank">'+data.data[i].title +'</a></td>';


            tr += '<td>'

            tr += data.data[i].attachments;
            // for (var j = 0; j < data.data[i].attachmentCollection.length; j++) {
            //     tr+='<div><a href="'+data.data[i].attachmentCollection[j].url+'" target="_blank">'+data.data[i].attachmentCollection[j].title+'</a></div>';
            //
            // }
            tr += '</td>'


            tr +='<td></td>' +
                '</tr>';
        }
        $("#tbo").html(tr);
        var filter = (search == 'null') ? '' : search;
        $('#filter_title').val(filter);
    });
}

