
var anterior=0;
var proximo=0;
var tamanho=0;
var seacher_user;
var opcao=0;
var opcaodelete=-8;


function alertDelete()
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



function alertAddUser()
{
    var x;
    var r=confirm("Adicionar! ?");
    if (r==true)
    {
            var nome_user=$('#nome_cad').val();
            var email_user=$('#email_cad').val();
            var password_user=$('#senha_cad').val();

            $.ajax({
                url: "/addteUser/"+nome_user+"/"+email_user+"/"+password_user,
            });
            alert(" Usuario adicionado com sucesso...");
            usuarios();
    }
    else
    {
    }
    //document.getElementById("demo").innerHTML=x;
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


function usuarios() {
    $.ajax({
        url: "returnOllUserr",
    }).done(function (data) {
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
                    opcaodelete=data[k].id;
                    tr += '<tr>' +
                        '<td class="paragrafo">' + data[k].firstName + '</td>' +
                        '<td class="paragrafo">' + data[k].email + '</td>' +
                        '<td class="paragrafo">' + data[k].password + '</td>' +
                        '<td>' + '<button class="button3"> Previlegio</button>' + '</td>' +
                        '<td>' + '<button class="button2" onclick="alertDelete()"> Eliminar</button>' + '</td>';
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
                    '<td>' + '<button class="button3"> Privilegio</button>' + '</td>' +
                    '<td>' + '<button class="button2" onclick="alertDelete()"> Eliminar</button>' + '</td>';
                tr += '<td></td>' +
                    '</tr>';
            }
        }

        tr+= '<tr>'+
            '<td>'+'<button id="anterior" onclick="ircAnterior()"> Anterior</button> '+' <button id="proximo" onclick="incProximo()"> Proximor</button>'+'</td>'+
            +'</tr>';
        $("#tbo4").html(tr);


    });
}

$(Document).ready(function () {
    usuarios();
});