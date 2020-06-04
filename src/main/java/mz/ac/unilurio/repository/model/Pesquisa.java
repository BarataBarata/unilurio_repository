package mz.ac.unilurio.repository.model;

public class Pesquisa {
       public static String ano;
       public static String titulo;
       public static String categoria;

    public Pesquisa(String ano, String titulo, String categoria) {
        this.ano = ano;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    public Pesquisa() {

    }

    public static String getAno() {
        return ano;
    }

    public static String getTitulo() {
        return titulo;
    }

    public static String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return ""+ano+""+titulo+""+categoria;
    }
}
