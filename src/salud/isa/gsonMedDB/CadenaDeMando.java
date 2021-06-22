package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class CadenaDeMando {
	
	protected CadenaDeMando successor;
	protected String CategoriaNombre;
	
	
	public CadenaDeMando(CadenaDeMando s) {
		successor=s;
	}
	
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		
		 return successor.analizarCategoria(reader, categoria);
	}
	
	public StringBuffer parseCategoriasGeneral(JsonReader reader) throws IOException {
		StringBuffer categoria = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			categoria.append(readEntry(reader)).append("\n");
			reader.endObject();
		}
		categoria.append("\n");
		reader.endArray();
		return categoria;
	}

	protected abstract String readEntry(JsonReader reader) throws IOException;

}
