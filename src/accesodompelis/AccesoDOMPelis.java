package accesodompelis;

/**
 * Clases importadas
 */
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Clase que albergará los métodos necesarios para la realización del ejercicio
 *
 * @author javie
 */
public class AccesoDOMPelis {

    /**
     * Creación de un atributo de la clase Document *
     */
    /**
     * Este atributo va a contener el árbol DOM del documento XML *
     */
    private Document documento;

    /**
     * *
     * Método que se encargará de abrir el fichero XML
     *
     * @param fichero
     * @return
     */
    public int abrirXmlDom(File fichero) {
        // Instanciación de la variable documento a null
        documento = null;

        try {

            // Creación de un objeto de la clase DocumentBuilderFactory
            DocumentBuilderFactory dbc = DocumentBuilderFactory.newInstance();

            // Con el método setIgnoringComments, vamos a ignorar los comentarios que haya en el documento XML
            dbc.setIgnoringComments(true);

            // Con el método setIgnoringElementContentWhitespace, vamos a ignorar los espacios en blanco que haya en el XML
            dbc.setIgnoringElementContentWhitespace(true);

            // Con el método setValidating, vamos a validar el código
            dbc.setValidating(false);
            
            // Creación de un objeto de la clase DocumentBuilder
            DocumentBuilder db = dbc.newDocumentBuilder();

            // Con el objeto previamente creado, vamos a usar uno de sus métodos como es el parse, y le pasamos el fichero XML que queremos parsear
            documento = db.parse(fichero);
            
           

            return 0;

        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }

    }

    /**
     * Método String que se va a encargar de recorrer el árbol DOM del fichero
     * XML
     *
     * @return
     */
    public String recorrerDom() {

        // Creación de un array de tipo String
        String datos[] = null;

        // Creación de una variable vacía de tipo String
        String informacion = "";

        // Creación de una variable de la clase Node
        Node node;

        // Creación de un objeto de tipo Node, para la obtención de los nodos hijos del elemento raíz
        // En este caso, obtendremos el nodo Pelis
        Node nodoRaiz = documento.getFirstChild();

        // Creación de un objeto de la clase NodeList, que va a servir para almacenar los nodos hijos del nodo raíz
        // En este caso esta variable va a almacenar los cinco nodos hijos que tiene el elemento raíz
        NodeList nodoshijosraiz = nodoRaiz.getChildNodes();

        // Iteramos
        for (int i = 0; i < nodoshijosraiz.getLength(); i++) {
            node = nodoshijosraiz.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                datos = procesarPelis(node);
                informacion = informacion + "\n Tipo:  " + datos[0]
                        + "\n Título : " + datos[1]
                        + "\n Productora : " + datos[2]
                        + "\n Duración : " + datos[3]
                        + "\n -----------------------";
            }
            node.getTextContent();
        }
        return informacion;
    }

    /**
     * Método procesarPelis que se va a encargar de leer los nodos del fichero XML
     * @param node
     * @return 
     */
    public String[] procesarPelis(Node node) {

        // Creación de un array de Strings
        String[] pelis = new String[4];
        
        // Inicialización de una variable de la clase Node a null
        Node nula = null;
        
        // Inicialización de una variable contador a 1
        int contador = 1;

        // Se obtiene el valor del primer atributos
        if(node.hasAttributes() == false){
            
            pelis[0] = "vacio";
            
        }else{
            
            pelis[0] = node.getAttributes().item(0).getNodeValue();
        
        }
        
        // Obtenemos los nodos hijos 
        NodeList nodos = node.getChildNodes();
        
        // Iteramos
        for (int i = 0; i < nodos.getLength(); i++) {
            nula = nodos.item(i);
            
            if (nula.getNodeType() == Node.ELEMENT_NODE) {

                pelis[contador] = nula.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }

        return pelis;

    }
}
