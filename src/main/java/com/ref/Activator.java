package com.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ref.ActivityController.VerificationType;
import com.ref.exceptions.FDRException;
import com.ref.exceptions.HttpException;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.fdr.FdrWrapper;
import com.ref.openmbee.Communication;
import com.ref.openmbee.adapter.Activity;
import com.ref.ui.CheckingProgressBar;

public class Activator implements BundleActivator {

	public void start(BundleContext context) {
		try {
			FdrWrapper wrapper = FdrWrapper.getInstance();
			Properties prop = new Properties();
			InputStream input;
			input = new FileInputStream("ref.properties");
			prop.load(input);
			wrapper.loadFDR(prop.getProperty("fdr3_jar_location"));
			wrapper.loadClasses();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void stop(BundleContext context) {
		String fs = System.getProperty("file.separator");
		String uh = System.getProperty("user.home");
		File directory = new File(uh + fs + "TempAstah");

		try {
			delete(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				delete(c);
		}
		if (!f.delete())
			throw new FileNotFoundException("Failed to delete file: " + f);
	}

	
	
	public static void main(String args[]) throws ClientProtocolException, IOException {
		
		String url = "http://18.188.75.184/projects/tmt/refs/master/elements/";
		String login = "ufrpe";
		String password = "thisisapassword";
		String idActivity = "_17_0_2_3_41e01aa_1386574999817_391486_76808";//setup aps
		String idActivity2 = "_17_0_2_3_41e01aa_1382473227299_833303_50967";//retrive reference
		String idActivity3 = "_18_0_2_baa02e2_1423614941566_531457_145750"; //teste pinos
		String idActivity4 = "_17_0_2_3_41e01aa_1386574999910_876392_76809";//activityDiagram setup aps
		String idActivity5 = "_17_0_2_3_41e01aa_1377898301447_305038_36370";//configure aps for sh
		String idActivity6 = "_17_0_2_3_41e01aa_1377758870344_371904_50126";//coarse tilt Alignment
		String idActivity7 = "_18_0_5_c0402fd_1467061114600_979199_185851";//Check If All Spots Present
		String idActivity8 = "_17_0_2_3_41e01aa_1380730256631_351922_41738";//Select Filter SH
		String idActivity9 = "_18_0_2_baa02e2_1423614729233_884141_145573";//Center Shear Plate
		String idActivity10 = "_17_0_2_3_41e01aa_1381599357817_363441_42743";//Select pupilMask SH
		String idActivity11 = "_17_0_2_3_41e01aa_1380730256631_351922_41738";//Select filter SH
		String idActivity12 = "_17_0_2_3_41e01aa_1377898460726_543592_36540";//start kmirror
		String idActivity13 = "_18_0_4_baa02e2_1432924083755_930185_152366";//Adjust shear plate peas
		//Activity activity = Communication.buildActivity(url,login,password,idActivity4);
		CheckingProgressBar progressBar = new CheckingProgressBar();
		
		try {
			ActivityController.getInstance().OpenMBEEInvocation(url, login, password, idActivity9, VerificationType.DEADLOCK, progressBar);
		} catch (FDRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WellFormedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}