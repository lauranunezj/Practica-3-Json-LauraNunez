package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadMedicines extends CadenaDeMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	
	public ReadMedicines(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("medicines");
	}

	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String medicinesName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			switch (name) {
			case NAME_FIELD_TAGNAME:
				medicinesName = reader.nextString();
				break;
			default:
				reader.skipValue();
			}
		}

		return medicinesName;
	}
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(MEDICINES_TAGNAME)) {
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
