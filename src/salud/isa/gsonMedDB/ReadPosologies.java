package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadPosologies extends CadenaDeMando {
	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESCRIPTION_FIELD_TAGNAME = "description";

	
	public ReadPosologies(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("posologies");
	}

	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String posologiesName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			switch (name) {
			case DESCRIPTION_FIELD_TAGNAME:
				posologiesName = reader.nextString();
				break;
			default:
				reader.skipValue();
			}
		}

		return posologiesName;
	}
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(POSOLOGIES_TAGNAME)) {
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
