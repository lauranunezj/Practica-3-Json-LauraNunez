package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReadRescueMedicinePresentation extends CadenaDeMando {
	
	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	
	private static final String NAME_FIELD_TAGNAME = "name";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";
	private static final String FIELD_SEPARATOR = ";";

	
	public ReadRescueMedicinePresentation(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("rescueMedicinePresentations");
	}

	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(MEDREF_FIELD_TAGNAME)) {
				medRef = reader.nextString();
			} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
				aiRef = reader.nextString();
			} else if (name.equals(INHREF_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				inhRef = new String(res);
			} else if (name.equals(DOSE_FIELD_TAGNAME)) {
				StringBuffer res = new StringBuffer();
				reader.beginArray();
				while (reader.hasNext()) {
					res.append(reader.nextString()).append(",");
				}
				reader.endArray();
				res.deleteCharAt(res.length() - 1);
				dose = new String(res);
			} else {
				reader.skipValue();
			}

		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}
	
	
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(RESCUEMEDPRES_TAGNAME)) {
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
