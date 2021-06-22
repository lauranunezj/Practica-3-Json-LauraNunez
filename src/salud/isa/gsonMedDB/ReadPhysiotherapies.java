package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadPhysiotherapies extends CadenaDeMando{
	
	public ReadPhysiotherapies(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("physiotherapies");
		// TODO Auto-generated constructor stub
	}
	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String IMAGE_FIELD_TAGNAME = "image";
	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String physiotherapiesName = null;
		String image= null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals(NAME_FIELD_TAGNAME)) {
				physiotherapiesName = reader.nextString();
			}else if(name.equals(IMAGE_FIELD_TAGNAME)){
				image = reader.nextString();	
			}else {
				reader.skipValue();
			}
		}
		return physiotherapiesName + ',' + image;
	}
	
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(PHYSIOTHERAPIES_TAGNAME)) {
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
