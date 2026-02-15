package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idioma;
    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return
                "----- LIBRO -----" + "\n" +
                        "Título: " + titulo + "\n" +
                        "Autor: " + (autor != null ? autor.getNombre() : "Desconocido") + "\n" +
                        "Idioma: " + idioma + "\n" +
                        "Descargas: " + numeroDeDescargas + "\n" +
                        "-----------------";
    }

    public void setAutor(Autor autor) { this.autor = autor; }
    public Autor getAutor() { return autor; }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
}
