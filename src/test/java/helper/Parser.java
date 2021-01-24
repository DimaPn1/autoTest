package helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private int globalPrice;
    private List<Computer> allComputer;

    public Parser() {
        inputParse();
    }

    public int getGlobalPrice() {
        return globalPrice;
    }

    public void setGlobalPrice(int globalPrice) {
        this.globalPrice = globalPrice;
    }

    public List<Computer> getAllComputer() {
        return allComputer;
    }

    public void setAllComputer(List<Computer> allComputer) {
        this.allComputer = allComputer;
    }

    public void inputParse(){
        searchGlobalPrice();
        searchManufacturer();
        validatePrice();
    }

    public void writeDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement =
                    doc.createElement("Computers");
            doc.appendChild(rootElement);

            for(int i = 0; i < allComputer.size(); i++){
                rootElement.appendChild(getNotebook(doc, Integer.toString(i), allComputer.get(i)));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File("src/test/resources/output.xml"));

            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getNotebook(Document doc, String id, Computer pc) {
        Element notebook = doc.createElement("Notebook");

        notebook.setAttribute("id", id);

        notebook.appendChild(getLanguageElements(doc, notebook, "manufacturer", pc.getManufacturerName()));

        String minPrice = Integer.toString(pc.getMinPrice());
        notebook.appendChild(getLanguageElements(doc, notebook, "minPrice", minPrice));

        String maxPrice = Integer.toString(pc.getMaxPrice());
        notebook.appendChild(getLanguageElements(doc, notebook, "maxPrice", maxPrice));

        notebook.appendChild(getLanguageElements(doc, notebook, "diagonal", Double.toString(pc.getDiagonal())));
        notebook.appendChild(getLanguageElements(doc, notebook, "weight", Double.toString(pc.getWeight())));

        return notebook;
    }

    private static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    public void searchManufacturer(){
        try{
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse("src/test/resources/input.xml");
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("Manufacturer");
            List<Computer> allPC = new ArrayList<Computer>();

            for(int i = 0; i < nodes.getLength(); i++){

                Node node = nodes.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Computer pc = new Computer();

                    Element eElement = (Element) node;

                    pc.setManufacturerName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                    String minPrice = eElement.getElementsByTagName("Min").item(0).getTextContent();
                    pc.setMinPrice(Integer.parseInt(minPrice));
                    String maxPrice = eElement.getElementsByTagName("Max").item(0).getTextContent();
                    pc.setMaxPrice(Integer.parseInt(maxPrice));
                    allPC.add(pc);
                }
            }
            setAllComputer(allPC);

        }catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void searchGlobalPrice(){
        try{
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse("src/test/resources/input.xml");
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("Global");

            Node node = nodes.item(0);
            Element element = (Element) node;

            int price = Integer.parseInt(element.getElementsByTagName("Max").item(0).getTextContent());
            setGlobalPrice(price);

        }catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void validatePrice(){
        for (Computer pc : allComputer) {
            if(pc.getMaxPrice() > globalPrice){
                pc.setMaxPrice(globalPrice);
                if(pc.getMinPrice() > globalPrice){
                    pc.setMinPrice(globalPrice);
                }
            }
        }
    }

    public void printPC(){
        for (Computer pc : allComputer) {
            System.out.println(pc.getManufacturerName());
            System.out.println(pc.getMinPrice());
            System.out.println(pc.getMaxPrice());
            System.out.println("-------------->");
        }
    }
}
