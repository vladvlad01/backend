package org.vlad.Backend.app;

import org.vlad.Backend.entities.Track;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class ParseLibrary {

	private static List <Track> tracks;

	public List<Track> parseTracks(String path)  {

//		System.out.println(path);
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(path);
			Document document = builder.build(xmlFile);
			Element element = document.getRootElement();


			Element fristDict = element.getChild("dict");
			Element secondDict = fristDict.getChild("dict");
			List <Element> trackElems = secondDict.getChildren("dict");

			tracks = new ArrayList<>();
			for (Element trackElem: trackElems) {

				Track track = new Track();
				List<Element> keyValueElements = trackElem.getChildren();

				int index = 0;
				HashMap<String, String> keyValue = new HashMap<>();
				keyValue.clear();

				for(Element el : keyValueElements) {
					if(index < keyValueElements.size() - 1) {
						keyValue.put(keyValueElements.get(index).getValue(), keyValueElements.get(index + 1).getValue());
						el.getName();
					}
					index++;
				}

				track.setTrackID(Integer.parseInt(keyValue.get("Track ID")));
				track.setName(keyValue.get("Name"));
				track.setArtist(keyValue.get("Artist"));
				track.setComposer(keyValue.get("Composer"));
				track.setAlbum(keyValue.get("Album"));
				track.setGenre(keyValue.get("Genre"));
				track.setKind(keyValue.get("Kind"));
				track.setSortAlbum(keyValue.get("Sort Album"));
				tracks.add(track);

			}
		}
		catch (Exception e){
			e.printStackTrace();
		}	
		return tracks;
		
	}
}