package com.ref;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ref.fdr.FdrWrapper;

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
		File directory = new File(uh+fs+"TempAstah");
		
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
}
