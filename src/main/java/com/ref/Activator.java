package com.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ref.fdr.FdrWrapper;
import com.ref.openmbee.Communication;
import com.ref.openmbee.adapter.Activity;

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
		Activity activity = Communication.buildActivity(url,login,password,idActivity);
		System.out.println(activity);
		
		
	}


}