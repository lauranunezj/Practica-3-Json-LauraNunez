package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class ReaduserManualPhysioSteps extends CadenaDeMando{
	
	public ReaduserManualPhysioSteps(CadenaDeMando s) {
		super(s);
		CategoriaNombre = new String("userManualPhysioSteps");
		// TODO Auto-generated constructor stub
	}
	private static final String USERMANUALPHYSIO_TAGNAME = "userManualPhysioSteps";

	private static final String STEPTITLE_FIELD_NAME = "stepTitle";
	private static final String STEPIMAGE_FIELD_NAME = "stepImage";
	private static final String STEPTEST_FIELD_NAME = "stepText";
	private static final String PHYSIOREF_FIELD_NAME = "physioRef";
	private static final String FIELD_SEPARATOR = " ; ";
	@Override
	protected String readEntry(JsonReader reader) throws IOException {
		String sTitle = null;
		String sImage = null;
		String sText = null;
		String physioRef = null;
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals(STEPTITLE_FIELD_NAME)) {
				sTitle = reader.nextString();
			} else if (name.equals(STEPIMAGE_FIELD_NAME)) {
				sImage = reader.nextString();
			} else if (name.equals(STEPTEST_FIELD_NAME)) {
				sText = reader.nextString();
			} else if (name.equals(PHYSIOREF_FIELD_NAME)) {
				physioRef = reader.nextString();
			} else {
				reader.skipValue();
			}

		}
		return sTitle + FIELD_SEPARATOR + sImage + FIELD_SEPARATOR + sText + FIELD_SEPARATOR + physioRef
				+ FIELD_SEPARATOR;
	}
	
	
	
	
	
	public StringBuffer analizarCategoria(JsonReader reader, String categoria) throws IOException {
		if (categoria.equals(USERMANUALPHYSIO_TAGNAME)) {
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
