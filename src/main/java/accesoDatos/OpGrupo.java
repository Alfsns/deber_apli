/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDatos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Entidades.Grupo;

/**
 *
 * @author Admin
 */
public class OpGrupo {

    private String filePath;        //para definir ruta de acceso y fichero
    private File xmlFile;           //para inicializar el fichero
    private DocumentBuilderFactory dbFactory;   //para generar documentos xml
    private DocumentBuilder dBuilder;   //generador de documentos xml
    private Document doc;           //para procesar documentos
    private NodeList nodeList;      //lista de nodos del xml

    public OpGrupo() throws ParserConfigurationException, SAXException, IOException {
        filePath = "Grupos.xml";
        xmlFile = new File(filePath);  //inicializa el fichero
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        existeXml();            //metodo para determinar presencia del xml
        doc = dBuilder.parse(xmlFile); //parsear el xml y cargarlo en document
        doc.getDocumentElement().normalize();  //getDocumentElement accede a nodo raiz, normalize elimina nodos vacios y combina adyacentes si los hay
        nodeList = doc.getElementsByTagName("Grupo"); //XML esta en memoria como Document=> convertirlo a arraylist
    }

    public boolean insertar(Grupo gru) {          //metodo para listar cuentas de ahorros
        boolean save = true;        //bandera para indicar si se almacenaron los cambios

        try {
            nodeList = doc.getElementsByTagName("Grupos"); //genera lista de nodos Titulares (debe haber uno solo)
            for (int i = 0; i < nodeList.getLength(); i++) {        //recorre los nodos obtenidos
                Node nNode = nodeList.item(i);                  //extrae un nodo (el unico)
                nNode.appendChild(crearNodoGrupo(doc, String.valueOf(doc.getElementsByTagName("Grupo").getLength() + 1), gru.getCodigo(), gru.getDescripcion()));  //añade nuevo nodo dentro de grupos, con un nuevo grupo
            }

            escribirXML(doc);      //graba cambios en el xml
        } catch (Exception e) {     //manejo de error
            e.printStackTrace();
            save = false;           //si se genera error, indicador a falso
        }
        return save;                //devuelve valor del indicador
    }

    public Grupo consultar(int id) {          //metodo para consultar datos de un titular
        Grupo gru = new Grupo();           //nueva entidad

        for (int i = 0; i < nodeList.getLength(); i++) {    //recorre lista de nodos titular
            Node nNode = nodeList.item(i);              //extrae datos de un titular

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {     //si es un nodo titular
                Element vGru = (Element) nNode;     //guarda datos del titular
                if (id == Integer.parseInt(vGru.getAttribute("id"))) {  //si el titular tiene el id buscado
                    gru = getGrupo(nodeList.item(i));    //parsea datos del xml a objeto titular
                }
            }
        }
        return gru;             //devuelve el objeto creado
    }

    public boolean modificar(Grupo gru) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {          //metodo para modificar datos de un titular
        boolean save = false;        //bandera para indicar si se almacenaron los cambios

        for (int i = 0; i < nodeList.getLength(); i++) {    //recorre lista de nodos titular
            Element vGru = (Element) nodeList.item(i);  //extrae datos de un titular
            if (vGru != null) {                                 //contiene datos
                if (vGru.getAttribute("id").equalsIgnoreCase(String.valueOf(2))) {  //si el id es igual al buscado
                    Node cod = vGru.getElementsByTagName("codigo").item(0).getFirstChild(); //extrae el nodo de cedula
                    cod.setNodeValue(gru.getCodigo()); //almacena cedula obtenida del parametro
                    Node desc = vGru.getElementsByTagName("descripcion").item(0).getFirstChild(); //extrae nombre del titular
                    desc.setNodeValue(gru.getDescripcion()); //almacena nombre de titular recibido como parametro
                    save = true;    //indicador de exito en la operacion
                }
            }
        }
        if (save) {
            escribirXML(doc);  //actualiza el xml
        }
        return save;    //devuelve resultado de la operacion
    }

    public boolean eliminar(int id) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {          //metodo para eliminar titular de cuenta
        boolean delete = false;                     //bandera que indica resultado de operacion

        for (int i = 0; i < nodeList.getLength(); i++) {    //recorre lista de nodos del xml
            Element vGru = (Element) nodeList.item(i);  //extrae un elemento
            if (vGru != null) {     //contiene datos
                if (vGru.getAttribute("id").equalsIgnoreCase(String.valueOf(id))) { //los datos obtenidos coinciden con identificador recibido como parametro
                    vGru.getParentNode().removeChild(vGru); //elimina datos de este titular
                    delete = true;  //indicador de exito de operacion
                }
            }
        }
        if (delete) {   //si se eliminaron datos de este titular
            escribirXML(doc);  //actualiza xml
        }
        return delete;   //devuelve el valor de la bandera
    }

    public ArrayList<Grupo> listar() {          //metodo para listar titulares de cuentas
        ArrayList<Grupo> list = new ArrayList<>(); //list es la lista de titulares
        System.out.println("Elemento Root: " + doc.getDocumentElement().getNodeName());
        for (int i = 0; i < nodeList.getLength(); i++) {    //recorre los nodos extraidos del xml
            list.add(getGrupo(nodeList.item(i))); //incluye datos del titular el lista respuesta
        }
        return list;    //devuelve la lista generada
    }

    private static Grupo getGrupo(Node node) {  //obtiene datos de un titular
        Grupo grup = new Grupo();   //crea objeto vacio
        if (node.getNodeType() == Node.ELEMENT_NODE) {  //lo extraido es un nodo
            Element element = (Element) node;   //genera un elemento desde xml
            grup.setCodigo(getValorCampo("codigo", element)); //extrae nodo cedula del elemento y lo añade en el objeto
            grup.setDescripcion(getValorCampo("descripcion", element)); //extrae nodo nombre del elemento y lo añade en el objeto
        }
        return grup;    //devuelve el objeto creado
    }

    private static String getValorCampo(String tag, Element elem) {    //metodo para obtener valor de un nodo de un elemento
        NodeList nodeList = elem.getElementsByTagName(tag).item(0).getChildNodes();  //obtiene el valor asociado al nodo
        Node node = (Node) nodeList.item(0);    //guarda el primer nodo extraido
        return node.getNodeValue(); //devuleve el valor asociado al nodo guardado
    }

    private static Node crearNodoGrupo(Document doc, String id, String codigo, String descripcion) {   //crear un elemento para xml con datos de titular
        Element grupo = doc.createElement("Grupo"); //crea un elemento titular

        grupo.setAttribute("id", id); // actualiza atributo id
        grupo.appendChild(crearNodosEnGrupo(doc, "codigo", codigo)); // crea elemento cedula
        grupo.appendChild(crearNodosEnGrupo(doc, "descripcion", descripcion)); // crea elemento nombre
        return grupo; //devuelve el elemento creado
    }

    private static Node crearNodosEnGrupo(Document doc, String nom, String val) {     //metodo para crear elementos de tipo titular
        Element node = doc.createElement(nom); //crea un nuevo elemento
        node.appendChild(doc.createTextNode(val));    //añade nuevo nodo de texto
        return node;    //devuelve el nodo creado
    }

    private void escribirXML(Document doc) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {  //metodo para grabar fichero xml
        doc.getDocumentElement().normalize();       //eliminar nodos vacios o en blanco
        TransformerFactory transformerFactory = TransformerFactory.newInstance();   //crea una instancia de objeto transformador
        Transformer transformer = transformerFactory.newTransformer(); //instancia un objeto transformador
        DOMSource source = new DOMSource(doc);  //define documento origen
        StreamResult result = new StreamResult(new File(filePath)); //define el resultado esperado: fichero xml
        //StreamResult result = new StreamResult(System.out);       //cuando se quiere generar xml a consola
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");    //configura salida para que sea identada
        transformer.transform(source, result);  //realiza transformación de formato
        System.out.println("XML generado satisfactoriamente");
    }

    public boolean existeXml() {     //metodo para determinar si el xml existe
        if (!xmlFile.exists()) {     //no existe el fichero xml
            try {

                doc = dBuilder.newDocument();   // añade elementos al documento
                Element rootElement = doc.createElement("Grupos");   //define el nodo raiz
                doc.appendChild(rootElement);   //añade el nodo raiz al documento
                escribirXML(doc);  //genera el fichero xml
            } catch (Exception e) { //manejo de error
                e.printStackTrace();
            }
        }
        return true;    //devuelve indicador de presencia de fichero
    }
}
