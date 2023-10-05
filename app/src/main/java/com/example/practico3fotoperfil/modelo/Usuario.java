package com.example.practico3fotoperfil.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
        private String dni;
        private String apellido;
        private String nombre;
        private String correo;
        private String contraseña;
        private String foto;

        public Usuario() {
        }

        public Usuario(String dni, String apellido, String nombre, String correo, String contraseña) {
            this.dni = dni;
            this.apellido = apellido;
            this.nombre = nombre;
            this.correo = correo;
            this.contraseña = contraseña;
            this.foto = dni + ".png";
        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }
}
