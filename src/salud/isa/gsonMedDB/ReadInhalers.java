package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadInhalers extends CadenaDeMando {
	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";

	public ReadInhalers(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("inhalers");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String inhalersName = null;
		String image= null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				inhalersName = reader.nextString();
			}else if(name.equals(IMAGE_FIELD_TAGNAME)){
				image = reader.nextString();	
			}else {
				reader.skipValue();
			}
		}
		return inhalersName + ',' + image;
	}
	
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(INHALERS_TAGNAME)) {
			return super.parseCategoriasGeneral(reader);
		} else {
			if (successor != null) {
				return super.analizarCategoria(reader,categoria);
			} else {
				reader.skipValue();
				System.err.println("Category " + categoria + " not processed.");
				return new StringBuffer();
			}
		}
	}
	

}
