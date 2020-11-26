
var anterior=0;
var proximo=0;
var tamanho=0;
var seacher_user;
var opcao=0;
var opcao_User;
var id_user_a_lancar;
var a1op=false;
var a2op=false;
var a3op=false;
var a4op=false;
var a5op=false;
var delete_opcao=0;


function atualizar() {
    a1op=false;
    a2op=false;
    a3op=false;
    a4op=false;
    a5op=false;
}


function alertDelete(opcaodelete)
{
    var r=confirm(" DESEJA ELIMINAR O USUARIO ?");
    if (r==true)
    {

            $.ajax({
                url: "/deleteUser/"+opcaodelete,
            });
            tamanho=0;
            proximo=0;
            anterior=0;
            window.location.href="Usuarios.html";
            alert(" Usuario eliminado com sucesso...");
            usuarios();
    }
    else
    {
    }
    //document.getElementById("demo").innerHTML=x;
}




      function visualizar() {


      }
function  concatena(string) {
    var stringaux='';
    var convertstr='';

    for(var i=0;i<string.length;i++){
        if(string[i]!=' ') {
            stringaux+= string[i];
        }
    }
    convertstr=stringaux.toUpperCase();
    return convertstr;
}
function procurar() {
    var t='';
    seacher_user = $("#filter_user").val();
    opcao=1;
    usuarios();
}

function  inserTamanho(tamanh) {
          tamanho=tamanh;
          cont=0;
          if (proximo==0) {
              for (var i = 0; i < tamanh; i++, cont++) {
                  if (cont < 4) {
                      proximo++;
                  }
              }
          }
}

function  incProximo() {
    var cont =0;
    if(proximo<tamanho){
        anterior=proximo;
     }
    if(tamanho!=0){
        for(var inc=proximo;inc<=tamanho;inc++,cont++){
            if(cont>3 || inc>=tamanho){
                proximo=inc;
                break;
            }

        }
    }
      // 0 1 2 3 4 5 6 7 8 9
    usuarios();
}



function ircAnterior() {
    cont=0;
    if(anterior>0) {
        proximo = anterior;
        for (; anterior > 0; anterior--, cont++) {
            if (cont > 3) {
                break;
            }
        }
    }
    usuarios();
}


 function remover_Privilegios() {

    if(a1op==true) {
        var opcao1 = document.getElementsByName("a1");

        if(opcao1[0].checked==true){
            $.ajax({
                url: "/remove_Privilg_EditFILE/"+delete_opcao,
            });
        }
    }
    if(a2op==true) {
        var opcao2 = document.getElementsByName("a2");
        if(opcao2[0].checked==true){
                $.ajax({
                    url: "/remove_Privilg_addUSER/"+delete_opcao,
                });
        }
    }
    if(a3op==true) {
        var opcao3 = document.getElementsByName("a3");
        if(opcao3[0].checked==true) {
                $.ajax({
                    url: "/remove_Privilg_Delete_USER/"+delete_opcao,
                });
        }
    }

    if(a4op==true) {
        var opcao4 = document.getElementsByName("a4");

        if (opcao4[0].checked == true) {
                $.ajax({
                    url: "/remove_Privilg_View_Link/"+delete_opcao,
                });
        }

    }

    if(a5op==true) {
        var opcao5 = document.getElementsByName("a5");

        if (opcao5[0].checked == true) {
                $.ajax({
                    url: "/remove_Privilg_Deletar_FILE/"+delete_opcao,
                });
        }
    }
    }

    function lancar_Privilegios(id_user){

           var nome_user;
           delete_opcao=id_user;
            atualizar();
            var tr='';
            $.ajax({
                url: "/returnOllUserr",
            }).done(function (data) {
                   for(var i=0;i<data.length;i++){
                       if(data[i].id==id_user){
                           nome_user=data[i].firstName;
                               var tr='';
                               if(data[i].active==true) {
                                   a1op=true;
                                   tr += '<p>' +
                                       '<div>' +
                                       '<input type = "checkbox" id = "a1" name = "a1" valor = ""/>' +
                                       '<label id = "coding1">Previlegio de editar um determinado documento </label>' +
                                       '</div>' +
                                       '</p>';
                               }
                               if(data[i].addUserAuthorization==true){
                                   a2op=true;
                                   tr += '<p>' +
                                       '<div>' +
                                       '<input type = "checkbox" id = "a2" name = "a2" valor = ""/>' +
                                       '<label id = "coding2">Previlegio de adicionar um determinado usuario </label>' +
                                       '</div>' +
                                       '</p>';
                               }

                               if(data[i].deleteUserAuthorization==true){
                                   a3op=true;
                                   tr += '<p>' +
                                       '<div>' +
                                       '<input type = "checkbox" id = "a3" name = "a3" valor = ""/>' +
                                       '<label id = "coding3">Previlegio de eliminar um determinado usuario </label>' +
                                       '</div>' +
                                       '</p>';
                               }

                               if(data[i].acess_userAuthorization==true){
                                   a4op=true;
                                   tr += '<p>' +
                                       '<div>' +
                                       '<input type = "checkbox" id = "a4" name = "a4" valor = ""/>' +
                                       '<label id = "coding4">Previlegio de visualizar usuarios  </label>' +
                                       '</div>' +
                                       '</p>';
                               }

                               if(data[i].removeFileAuthorization==true) {
                                   a5op=true;
                                   tr += '<p>' +
                                       '<div>' +
                                       '<input type = "checkbox" id = "a5" name = "a5" valor = ""/>' +
                                       '<label id = "coding5">Previlegio de eliminar um determinado documento </label>' +
                                       '</div>' +
                                       '</p>';
                               }

                            break;
                       }
                   }
                   $("#privilegios").html(tr);
               $('#formAddfile2').modal('show');
               $('#modal-title2').text(nome_user);
            });

        }

function usuarios() {
    $.ajax({
        url: "/returnOllUserr",
    }).done(function (data) {

        $.ajax({
            url: "/get_Ocultar_Opcao_Remover_Usuarios",
        }).done(function (data2) {

        if(tamanho==0){
            inserTamanho(data.length);
        }
        var tr = '';
        if(opcao==1 && seacher_user!=''){
            opcao=0;
            tr = '';
            var str_seacher=concatena(seacher_user);
            for (var k = 0; k < data.length; k++) {
                var str=concatena(data[k].firstName);
                var cont=0;
                var j=0;
                for(var i=0;i<str.length;i++){
                    if(str_seacher[j++]==str[i]){
                        cont++;
                    }else {
                        if(cont>0 && i>0){
                            i--;
                        }
                        cont=0;
                        j=0;
                    }
                    if(cont==str_seacher.length){
                        break;
                    }
                }
                if(cont==str_seacher.length ||cont>0){
                    tr += '<tr>' +
                        '<td class="paragrafo">' + data[k].firstName + '</td>' +
                        '<td class="paragrafo" >' + data[k].email + '</td>' +
                        '<td class="paragrafo">' + data[k].password + '</td>' +
                        '<td>' + '<button class="button3" onclick="lancar_Privilegios('+data[k].id+')"> Previlegio</button>' + '</td>';
                       if(data2==true) {
                           tr+='<td>' + '<button class="button2" onclick="alertDelete('+data[k].id+')"> Eliminar</button>' + '</td>';
                       }else{
                           tr+='<td>' + '<button class="button2" style="display: none" onclick="alertDelete('+data[k].id+')"> Eliminar</button>' + '</td>';
                       }
                     tr += '<td></td>' +
                        '</tr>';
                }

            }
            tamanho=0;

        }else {
            tr = '';
            for (var i = anterior; i < proximo; i++) {
                opcaodelete=data[i].id;
                tr += '<tr>' +
                    '<td class="paragrafo">' + data[i].firstName + '</td>' +
                    '<td class="paragrafo">' + data[i].email + '</td>' +
                    '<td class="paragrafo">' + data[i].password + '</td>' +

                    '<td>' + '<button class="button3" onclick=lancar_Privilegios('+data[i].id+')> Privilegio</button>' + '</td>';
                if(data2==true) {
                    tr+='<td>' + '<button class="button2" onclick="alertDelete('+data[i].id+')"> Eliminar</button>' + '</td>';
                }else{
                    tr+='<td>' + '<button class="button2" style="display: none" onclick="alertDelete('+data[i].id+')"> Eliminar</button>' + '</td>';
                }
                tr += '<td></td>' +
                    '</tr>';            }
        }
        tr+= '<tr>'+
            '<td>'+'<button id="anterior" onclick="ircAnterior()"> Anterior</button> '+' <button id="proximo" onclick="incProximo()"> Proximor</button>'+'</td>'+
            +'</tr>';
        $("#tbo4").html(tr);

        });
    });
}

$(Document).ready(function () {
    usuarios();
});