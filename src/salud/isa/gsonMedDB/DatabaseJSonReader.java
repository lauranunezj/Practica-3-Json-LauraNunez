package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	protected CadenaDeMando successor;
	protected String CategoriaNombre;
	private JsonReader json;
	
	public DatabaseJSonReader(JsonReader j){
		json=j;
	}

	public void setCadenaDeMando(CadenaDeMando d) {
		successor=d;
	}
	
	public String parse(String jsonFileName) throws IOException {

		json.beginObject();
		StringBuffer readData = new StringBuffer();
		
		while (json.hasNext()) {
			String name = json.nextName();
			readData.append(successor.analizarCategoria(json,name));
		}

		json.endObject();
		json.close();
		json.close();

		return new String(readData);
	}

}
