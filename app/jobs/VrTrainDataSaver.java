package jobs;

import akka.actor.UntypedActor;
import models.AvarageLateness;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import play.Logger;
import play.mvc.Result;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static play.libs.Json.toJson;

/**
 * Created
 * Date: 2/10/13
 * Time: 9:58 AM
 */
public class VrTrainDataSaver extends UntypedActor {

    private static final String ALL_TRAINS_URL = "http://188.117.35.14/TrainRSS/TrainService.svc/AllTrains";
    private static final String TRAIN_URL = "http://188.117.35.14/TrainRSS/TrainService.svc/trainInfo?train=";

    @Override
    public void onReceive(Object message) throws Exception {
        Logger.debug("Saving new VR train lateness info...");
        List<String> allTrains = getAllTrainIds();

        long lateInTotal = 0;
        for (String train : allTrains) {
            Document feed = createRssFeedDoc(TRAIN_URL + train);
            Node latenessItem = feed.getElementsByTagName("lateness").item(0);
            int lateness = Integer.parseInt(latenessItem.getTextContent());
            lateInTotal += lateness;
        }
        long lateInAvarage = lateInTotal / allTrains.size();

        AvarageLateness lateness = new AvarageLateness();
        lateness.date = new Date();
        lateness.lateOnAvarage = lateInAvarage;
        lateness.serviceProviderName = "VR";
        lateness.save();
    }

    public static List<String> getAllTrainIds() throws Exception {
        List<String> result = new ArrayList<String>();

        Document doc = createRssFeedDoc(ALL_TRAINS_URL);

        NodeList guids = doc.getElementsByTagName("guid");
        for (int i = 0; i < guids.getLength(); i++) {
            Node item = guids.item(i);
            result.add(item.getTextContent());
        }

        return result;
    }

    private static Document createRssFeedDoc(String rssFeedUrl) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.parse(new URL(rssFeedUrl).openStream());
    }
}

