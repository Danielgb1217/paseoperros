package com.paseoperros;

import co.edu.umanizales.listase.modelo.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

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
    private int datoBuscar;
    private Perro perroEncontrado;
    private int posicion;
    private Nodo cabezaInicial;
    private DefaultDiagramModel model;
    private int banderaPintarExtremos = 0;
    private int seleccionarUbicacion = 0;
    
    public ListaSEController() {
    }
    
    @PostConstruct
    private void iniciar() {
        
        listaPerros = new ListaSE();
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
        listaPerros.adicionarNodo(new Perro("Andres", (byte) 11, (byte) 50, "macho"));
        listaPerros.adicionarNodo(new Perro("Tania", (byte) 12, (byte) 50, "hembra"));
        listaPerros.adicionarNodo(new Perro("Camila", (byte) 13, (byte) 50, "hembra"));
        listaPerros.adicionarNodo(new Perro("Tobby", (byte) 14, (byte) 50, "macho"));
        listaPerros.adicionarNodo(new Perro("Javier", (byte) 15, (byte) 50, "macho"));
        listaPerros.adicionarNodo(new Perro("Ana", (byte) 16, (byte) 50, "hembra"));
        listaPerros.adicionarNodo(new Perro("Lupe", (byte) 17, (byte) 50, "hembra"));
        
        perroMostrar = listaPerros.getCabeza().getDato();//para que quede mostrando el primero
        temp = listaPerros.getCabeza(); //al mismo objeto le pongo otro apodo
        cabezaInicial = listaPerros.getCabeza();
        iniciarModelo();
        
    }

    //**********************************setter and getter***********************************
    public int getDatoBuscar() {
        return datoBuscar;
    }
    
    public void setDatoBuscar(int datoBuscar) {
        this.datoBuscar = datoBuscar;
    }
    
    public Perro getPerroEncontrado() {
        return perroEncontrado;
    }
    
    public void setPerroEncontrado(Perro perroEncontrado) {
        this.perroEncontrado = perroEncontrado;
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
    
    public DiagramModel getModel() {
        return model;
    }
    
    public int getSeleccionarUbicacion() {
        return seleccionarUbicacion;
    }
    
    public void setSeleccionarUbicacion(int seleccionarUbicacion) {
        this.seleccionarUbicacion = seleccionarUbicacion;
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
//        }else{// tiene perros//            
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
    
        public void buscar() {
        int cont = 1;
        int bandera = 1;
        temp = listaPerros.getCabeza();
        while (temp != null && bandera == 1) {
            if (posicion == cont) {
                perroMostrar = temp.getDato();
                bandera = 0;
                iniciarModelo();
            } else {
                temp = temp.getSiguiente();
                cont++;
            }
        }
    }
    <p:panelGrid id="buscarPerro" columns="3">
    <p:outputLabel value="Ingrese Posicion a Buscar"/>
    <p:inputText id="posicion" required="true" value="#{listaSEController.posicion}"/>
    <p:commandButton value="Buscar" action="#{listaSEController.buscar()}" update="pnlPerros,diagrama"/>
    </p:panelGrid>    
    
     */
    //**********************Metodos del profe************************************
    public void irSiguiente() {
        temp = temp.getSiguiente();
        perroMostrar = temp.getDato();
    }
    
    public void irPrimero() {
        if (listaPerros.getCabeza() != null) {
            temp = listaPerros.getCabeza();
            perroMostrar = temp.getDato();
        } else {
            JsfUtil.addErrorMessage("La lista de perros esta vacia");
        }
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
        iniciarModelo();
        irPrimero();//Me ubico en el primero dato despues de invertirlos
    }
    
    public void intercambiarExtremos() {
        //llamo al metodo de la ListaSE desde aca para crear un sincronismo
        listaPerros.intercambiarExtremos();
        
        irPrimero();//Me ubico en el primero dato despues de invertirlos
        banderaPintarExtremos = 1;
        iniciarModelo();
    }
    
    public void eliminar() {
        if (listaPerros.getCabeza() != null) {
            listaPerros.eliminarNodo(temp.getDato());
            iniciarModelo();
            irPrimero();
        } else {
            JsfUtil.addErrorMessage("NO hay Perros para eliminar");
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
        iniciarModelo();
    }
    
    public void buscarPerro() {
        if (listaPerros.getCabeza() != null) {
            perroEncontrado = listaPerros.encontrarxPosicion(datoBuscar);
            iniciarModelo();
        } else {
            JsfUtil.addErrorMessage("NO hay perros para mostrar");
        }
    }

    //*****************************************Muestra graficamente las conexiones de perros ****************************
    public void iniciarModelo() {

        //*******************************************Metodo pintar Perros Profe*****************************
        //Instanciar e modelo
        model = new DefaultDiagramModel();
        //Definirle al modelo la cantidad de enlaces -1 (infinito)
        model.setMaxConnections(-1);
        
        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        //pregunto si hay datos
        if (listaPerros.getCabeza() != null) {
            //Llamo a mi ayudante y lo ubico en el primero
            Nodo ayudante = listaPerros.getCabeza();
            //recorro mientras el ayudante tenga datos
            int posX = 2;
            int posY = 2;
            int contFila = 0;
            while (ayudante != null) {
                contFila++;
                Element perroPintar = new Element(ayudante.getDato().getNombre(), posX + "em", posY + "em");
                
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                perroPintar.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                model.addElement(perroPintar);
                ayudante = ayudante.getSiguiente();
                posX = posX + 8;

                //condicion para que salte de fila cuando se cuenten 7 perritos
                if (contFila % 10 == 0) {
                    posX = 2;
                    posY = posY + 6;
                }
                
                if (contFila == datoBuscar) {
                    perroPintar.setStyleClass("ui-diagram-success");
                }
                
                if (banderaPintarExtremos == 1) {
                    if (contFila == 1) {
                        perroPintar.setStyleClass("ui-diagram-invertirExtremos");
                    }
                    if (contFila == listaPerros.contarNodos()) {
                        perroPintar.setStyleClass("ui-diagram-invertirExtremos");                        
                        banderaPintarExtremos = 0;
                    }
                    
                }
            }

            // el ayudante quedo en el enlace del último
            //Ya pinte todos los elementos y los puntos de enlace
            for (int i = 0; i < model.getElements().size() - 1; i++) {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(0),
                        model.getElements().get(i + 1).getEndPoints().get(1), null));
            }
        }
        //Metodo profe**********************************************************************************************************

        /*Metodo mio**************************************************************************************************************     
       model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        FlowChartConnector connector = new FlowChartConnector();
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);

        int cont = 0;
        int posicionHorizontal = 1;
        int posicionVertical = 1;

        Element start = new Element();
        Element start2 = new Element();

        temp = listaPerros.getCabeza();
        while (temp != null) {
            cont++;
            if (cont % 8 == 0) {
                posicionVertical = posicionVertical + 6;
                posicionHorizontal = 1;
            }

            posicionHorizontal = posicionHorizontal + 8;

            if (cont % 2 != 0) {    //COntador es impar instancia el elmente a la izquierda
                start = new Element(temp.getDato().getNombre(), posicionHorizontal + "em", posicionVertical + "em");
                start.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                start.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                if (cont == datoBuscar) {
                    start.setStyleClass("ui-diagram-success");
                }
                model.addElement(start);
                if (cont > 2) {

                    model.connect(createConnection(start2.getEndPoints().get(1), start.getEndPoints().get(0), null));
                }

            }
            if (cont % 2 == 0) {

                start2 = new Element(temp.getDato().getNombre(), posicionHorizontal + "em", posicionVertical + "em");
                start2.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
                start2.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
                if (cont == datoBuscar) {
                    start2.setStyleClass("ui-diagram-success");
                }
                model.addElement(start2);
                model.connect(createConnection(start.getEndPoints().get(1), start2.getEndPoints().get(0), null));

            }

            temp = temp.getSiguiente();
        }
        irPrimero();   *///************************************************************************************************
    }
    
    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }
        return conn;
    }

    //metodo que retornoa la regla de navegacion y reinicia los datos...se llama desde el template principal para direccionarme a  la pag
    public String crearPerro() {        
        perroEncontrado = new Perro();  //inicio el perro para que no se lleve los datos del perro que busco        
        return "crear";                 //se va para el template con la palabra crear me dirigira a la pestania de crear perro
    }                                   //la palabra crear se creo desde el archivo faces config creando la regla correspondiente

    public void limpiarDiagrama() {
        datoBuscar = 0;
        iniciarModelo();
    }
    
    public void guardarPerro() {
        
        switch (seleccionarUbicacion) {
            
            case 1:
                listaPerros.adicionarNodoAlInicio(perroEncontrado);
                break;
            
            case 2:
                listaPerros.adicionarNodo(perroEncontrado);
                break;
            
        }
        
        perroEncontrado = new Perro();
        iniciarModelo();    //puedo crear un metodo que devuelva un string home para que el metodo me inicie el modelo y desde
        //el template llamo al metodo para que cada que me direccione a home se llame al metodo inicar modelo
        JsfUtil.addSuccessMessage("EL perro ha sido adicionado con exito");
    }
    
}
