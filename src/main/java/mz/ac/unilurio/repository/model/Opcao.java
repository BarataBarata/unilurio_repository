package mz.ac.unilurio.repository.model;

public class Opcao {
    public static boolean ocultarOpcaoEliminarDocumento;
    public static boolean ocultar_Op_Editar_Documeno;
    public static boolean ocultarLink_AddUser;
    public  static boolean ocultar_Opcao_Remover_Usuario;
    public  static boolean ocultar_Opcao_Adicionar_Usuario;


      public void setOcultar_Opcao_Remover_Usuario(boolean ocultar_Opcao_Remover_Usuario){
            this.ocultar_Opcao_Remover_Usuario=ocultar_Opcao_Remover_Usuario;
      }
      public boolean getOcultar_Opcao_Remover_Usuario(){
          return this.ocultar_Opcao_Remover_Usuario;
      }

      public void setOcultar_Opcao_Adicionar_Usuario(boolean ocultar_Opcao_Adicionar_Usuario){
          this.ocultar_Opcao_Adicionar_Usuario=ocultar_Opcao_Adicionar_Usuario;
      }

      public boolean getOcultar_Opcao_Adicionar_Usuario(){return this.ocultar_Opcao_Adicionar_Usuario;}

     public void setOcultarOpcaoEliminarDocumento(boolean ocultarOpcaoEliminarDocumento){
         this.ocultarOpcaoEliminarDocumento=ocultarOpcaoEliminarDocumento;
     }
     public boolean getOcultarOpcaoEliminarDocumento(){
        return this.ocultarOpcaoEliminarDocumento;
     }

     public void setOcultar_Op_Editar_Documeno(boolean ocultar_Op_Editar_Documeno){
             this.ocultar_Op_Editar_Documeno=ocultar_Op_Editar_Documeno;
     }
     public  boolean getOcultar_Op_Editar_Documeno(){
             return this.ocultar_Op_Editar_Documeno;
     }

     public void setOcultarLink_AddUser(boolean ocultarLink_AddUser){
         this.ocultarLink_AddUser=ocultarLink_AddUser;
     }
     public boolean getOcultarLink_AddUser(){
         return this.ocultarLink_AddUser;
     }

}
