package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			InputStream usersIS = new FileInputStream (new File("./datos.json"));
			JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));
			DatabaseJSonReader dbjp = new DatabaseJSonReader(reader);
			
			
			ReadMedicines rm = new ReadMedicines(null);
			ReadInhalers in = new ReadInhalers(rm);
			ReadPhysiotherapies p = new ReadPhysiotherapies(in);
			ReadActiveIngredients ai = new ReadActiveIngredients(p);
			ReadPosologies po = new ReadPosologies(ai);
			ReadRescueMedicinePresentation rmp = new ReadRescueMedicinePresentation(po);
			ReadMedicinePresentations mp = new ReadMedicinePresentations(rmp);
			ReaduserManualPhysioSteps umps = new ReaduserManualPhysioSteps(mp);
			ReaduserManuallSteps rms = new ReaduserManuallSteps(umps);
			
			dbjp.setCadenaDeMando(rms);

			try {
				System.out.println(dbjp.parse("./resources/datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
