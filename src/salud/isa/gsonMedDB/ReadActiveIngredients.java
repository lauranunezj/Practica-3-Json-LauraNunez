package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadActiveIngredients extends CadenaDeMando{
	private static final String ACTIVATEINGREDIENTS_TAGNAME = "activeIngredients";
	private static final String NAME_FIELD_TAGNAME = "name";

	
	public ReadActiveIngredients(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("activeIngredients");
	}

	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String activeIngredientsName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			switch (name) {
			case NAME_FIELD_TAGNAME:
				activeIngredientsName = reader.nextString();
				break;
			default:
				reader.skipValue();
			}
		}

		return activeIngredientsName;
	}
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(ACTIVATEINGREDIENTS_TAGNAME)) {
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
