package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
    private String nombre;
    private String apellido;
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Usuario> listaAmigos = new ArrayList<>();
    private ArrayList<Mensaje> mensajes = new ArrayList<>();
    private RedSocial red;

    public Usuario(RedSocial red, String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.red = red;

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    private Usuario crearUsuario() {
        System.out.println("Introduce el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido del usuario: ");
        String apellido = scanner.nextLine();
        Usuario usuario = new Usuario(red, nombre, apellido);
        return usuario;
    }

    private Boolean menu() {
        Boolean salir = false;
        System.out.println("#############################");
        System.out.println("########### Menu Usuario ##");
        System.out.println("#############################");
        System.out.println("1 Alta Amigo");
        System.out.println("2 Baja Amigo");
        System.out.println("3 Lista Amigos");
        System.out.println("4 Enviar Mensaje");
        System.out.println("5 Leer Mensaje");
        System.out.println("6 Responder mensaje");
        System.out.println("7 Ver mensajes no leidos");
        System.out.println("9 Salir");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                altaAmigo();
                break;
            case "2":
                bajaAmigo();
                break;
            case "3":
                listaAmigos();
                break;
            case "4":
                enviarMensaje();
                break;
            case "5":
                leerMensajes();
                break;
            case "6":
                responderMensajes();
                break;
            case "7":
                verMensajesNoleidos();
                break;

            case "9":
                System.out.println("Saliendo...");
                salir = true;
                break;

            default:
                System.out.println("opcion no válida");
                break;
        }
        return salir;

    }

    public void menuUsuario() {
        Boolean salir = false;
        while (!salir) {
            salir = menu();

        }
    }

    public void altaAmigo() {
        Usuario usuario = crearUsuario();
        Usuario user = red.comprobarUsuario(usuario);

        if (user == null) {
            System.out.println("Lo siento, el usuario no esta registrado");
        } else {
            if (comprobarUsuarioAmigo(user) != null) {
                System.out.println("Lo siento este usuario ya es tu amigo");
            } else {
                listaAmigos.add(user);
                System.out.println("El usuario ha sido añadido a tu lista de amigos.");
            }
        }

    }

    public void bajaAmigo() {
        Usuario usuario = crearUsuario();
        Usuario user = red.comprobarUsuario(usuario);

        if (user == null) {
            System.out.println("Lo siento, el usuario no esta registrado");
        } else {
            if (comprobarUsuarioAmigo(user) == null) {
                System.out.println("Lo siento este usuario no es tu amigo");
            } else {
                listaAmigos.remove(user);
                System.out.println("El usuario ha sido dado de baja de tu lista de amigos.");
            }
        }

    }

    public void listaAmigos() {
        System.out.println("LISTA DE AMIGOS");
        for (Usuario usuario : listaAmigos) {
            System.out.println("Nombre: " + usuario.getNombre() + "  Apellido: " + usuario.getApellido());
        }

    }

    private void agregarMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }

    public void enviarMensaje() {
        System.out.println("A quien quieres enviar el mensaje: ");
        Usuario usuario = crearUsuario();
        Usuario user = comprobarUsuarioAmigo(usuario);
        if (user == null) {
            System.out.println("Este usuario no es tu amigo.");
        } else {
            System.out.println("Escribe el mensaje que quieres mandar: ");
            String texto = scanner.nextLine();
            Mensaje mensaje = new Mensaje(texto, this);
            user.agregarMensaje(mensaje);
            System.out.println("MENSAJE ENVIADO");
        }
    }

    public void leerMensajes() {
        String patron = "dd/MM/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(patron);
        for (Mensaje mensaje : mensajes) {
            Usuario usuario = mensaje.getRemitente();
            System.out.println("Mensaje mandado por: " + usuario.getNombre() + " Fecha envio: "
                    + df.format(mensaje.getFechaEnvio()) + " Mensaje: " + mensaje.getTexto());
            mensaje.setLeido(true);
        }
    }

    public void verMensajesNoleidos() {
        String patron = "dd/MM/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(patron);
        for (Mensaje mensaje : mensajes) {
            Usuario usuario = mensaje.getRemitente();
            if (mensaje.getLeido() == false) {
                System.out.println("Mensaje mandado por: " + usuario.getNombre() + " Fecha envio: "
                        + df.format(mensaje.getFechaEnvio()) + " Mensaje: " + mensaje.getTexto());
                mensaje.setLeido(true);
            }
        }
    }

    public void responderMensajes() throws NumberFormatException {
        String patron = "dd/MM/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(patron);
        Integer contador = 0;
        for (Mensaje mensaje : mensajes) {
            Usuario usuario = mensaje.getRemitente();
            contador += 1;
            System.out.println("Codigo del mensaje: " + contador + " Mensaje mandado por: " + usuario.getNombre()
                    + " Fecha envio: "
                    + df.format(mensaje.getFechaEnvio()) + " Mensaje: " + mensaje.getTexto());
            mensaje.setLeido(true);
            mensaje.setContador(contador);
        }

        System.out.println("Introduce el codigo del mensaje al que quieres contestar: ");
        String codigoS = scanner.nextLine();
        Integer codigo = Integer.parseInt(codigoS);

        for (Mensaje mensaje : mensajes) {
            if (mensaje.getContador() == codigo) {
                Usuario destinatario = mensaje.getRemitente();
                if (destinatario == null) {
                    System.out.println("Este usuario no es tu amigo.");
                } else {
                    System.out.println("Escribe el mensaje que quieres mandar: ");
                    String texto = scanner.nextLine();
                    Mensaje mensajeEnviar = new Mensaje(texto, this);
                    destinatario.agregarMensaje(mensajeEnviar);
                    System.out.println("MENSAJE ENVIADO");
                }

            }
        }
    }

    private Usuario comprobarUsuarioAmigo(Usuario usuario) {
        for (Usuario user : listaAmigos) {
            if (user.getNombre().equals(usuario.getNombre()) && user.getApellido().equals(usuario.getApellido())) {
                return user;
            }
        }
        return null;
    }

}
