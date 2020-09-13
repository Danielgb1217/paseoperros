package com.paseoperros;

import co.edu.umanizales.listase.modelo.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;

/**
 *
 * @author daniel
 */
@Named(value = "listaSEController")
@SessionScoped
public class ListaSEController implements Serializable {

    private ListaSE listaPerros;
    private Perro perroMostrar;
    private Nodo temp;
    private String generoWeb;
    // private boolean stateButton;
    private int posicion;
    private Nodo cabezaInicial;

    public ListaSEController() {
    }

    @PostConstruct
    private void iniciar() {

        listaPerros = new ListaSE();
        //listaPerros.setCabeza(null);
        listaPerros.adicionarNodo(new Perro("Morgan", (byte) 1, (byte) 4, "macho"));
        listaPerros.adicionarNodo(new Perro("Tito", (byte) 2, (byte) 1, "macho"));
        listaPerros.adicionarNodo(new Perro("Maya", (byte) 3, (byte) 2, "hembra"));
        listaPerros.adicionarNodo(new Perro("Dohko", (byte) 4, (byte) 5, "macho"));
        listaPerros.adicionarNodo(new Perro("Teresa", (byte) 5, (byte) 2, "hembra"));
        listaPerros.adicionarNodo(new Perro("Violeta", (byte) 6, (byte) 2, "hembra"));
        listaPerros.adicionarNodo(new Perro("Martin", (byte) 7, (byte) 23, "macho"));
        listaPerros.adicionarNodo(new Perro("Helver", (byte) 8, (byte) 65, "macho"));
        listaPerros.adicionarNodo(new Perro("Fluflu", (byte) 9, (byte) 17, "hembra"));
        listaPerros.adicionarNodo(new Perro("Carlos", (byte) 10, (byte) 50, "macho"));

        perroMostrar = listaPerros.getCabeza().getDato();//para que quede mostrando el primero
        temp = listaPerros.getCabeza(); //al mismo objeto le pongo otro apodo
        cabezaInicial = listaPerros.getCabeza();
    }

    /* *******************************Mis metodos*********************************************************

    public void primero() {

        perroMostrar = listaPerros.getCabeza().getDato();

    }

    public void siguiente() {

        int numeroPerro = perroMostrar.getNumero() + 1;

        Nodo temp = listaPerros.getCabeza();
        while (temp.getSiguiente().getDato().getNumero() != numeroPerro) {
            temp = temp.getSiguiente();
        }
        //crea un nuevo perro cuando temp llego al u;timo nodo
        perroMostrar = temp.getSiguiente().getDato();
    }

    public void ultimo() {

//                if(cabeza==null){
//           //no tiene perros. El que llega queda de cabeza
//            cabeza = new Nodo(dato);
//        }else{// tiene perros
//            
//            Nodo temp = cabeza;
        Nodo temp = listaPerros.getCabeza();
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        //crea un nuevo perro cuando temp llego al u;timo nodo
        perroMostrar = temp.getDato();

    }

    public boolean validarCabeza() {

        if (listaPerros.getCabeza() != null) {
            return false;
        } else {
            return true;
        }

    }

    public boolean validarSegundo() {
        if (perroMostrar.getNumero() == 1) {
            return true;
        }
        return false;
    }

    public boolean validarSiguiente() {

//        try {
        int numeroPerro = perroMostrar.getNumero() + 1;

        Nodo temp = listaPerros.getCabeza();
        while (temp.getSiguiente().getDato().getNumero() != numeroPerro) {
            temp = temp.getSiguiente();

            if (temp.getSiguiente() == null) {
                return true;
            }

        }
//        } catch (Exception e) {
//            System.out.println("Error");
//        }

        return false;

    }

    public void eliminarPerro() {
        if (listaPerros.getCabeza() != null) {
            
            Nodo temp = listaPerros.getCabeza();
           
            while (temp.getSiguiente() != null ) {
                if(perroEliminar==temp.getDato().getNumero()){
                    temp=null;
                    
                }else{
                temp = temp.getSiguiente();
                }
            }
            //crea un nuevo perro cuando temp llego al u;timo nodo
            //perroMostrar = temp.getDato();
        } else {

        }

    }
    
    */
    
    //**********************Metodos del profe************************************
    public void irSiguiente() {

        temp = temp.getSiguiente();
        perroMostrar = temp.getDato();
    }

    public void irPrimero() {

        temp = listaPerros.getCabeza();
        perroMostrar = temp.getDato();
    }

    public void irUltimo() {
        temp = listaPerros.getCabeza();
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        perroMostrar = temp.getDato();

    }

    public void invertir() {
        //llamo al metodo de la ListaSE desde aca para crear un sincronismo
        listaPerros.invertirLista();
        irPrimero();//Me ubico en el primero dato despues de invertirlos

    }

    public void intercambiarExtremos() {
        //llamo al metodo de la ListaSE desde aca para crear un sincronismo
        listaPerros.intercambiarExtremos();
        irPrimero();//Me ubico en el primero dato despues de invertirlos

    }

    public void eliminar() {

        listaPerros.eliminarNodo(temp.getDato());
        irSiguiente();

    }

    public void buscar() {
        int cont = 1;
        int bandera = 1;
        temp = listaPerros.getCabeza();
        while (temp != null && bandera == 1) {
            if (posicion == cont) {
                perroMostrar = temp.getDato();
                bandera = 0;

            } else {
                temp = temp.getSiguiente();
                cont++;
            }

        }

    }

    public void ordenarGenero() {

        if (this.generoWeb.equals("default")) {
            listaPerros.setCabeza(cabezaInicial);
            irPrimero();
        } else {

            listaPerros.ordenarGenero(this.generoWeb);
            irPrimero();

        }

    }

    public Nodo getTemp() {
        return temp;
    }

    public void setTemp(Nodo temp) {
        this.temp = temp;
    }

    public Perro getPerroMostrar() {
        return perroMostrar;
    }

    public void setPerroMostrar(Perro perroMostrar) {
        this.perroMostrar = perroMostrar;
    }

    public ListaSE getListaPerros() {
        return listaPerros;
    }

    public void setListaPerros(ListaSE listaPerros) {
        this.listaPerros = listaPerros;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getGeneroWeb() {
        return generoWeb;
    }

    public void setGeneroWeb(String generoWeb) {
        this.generoWeb = generoWeb;
    }

}
