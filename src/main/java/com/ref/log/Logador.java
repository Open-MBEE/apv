package com.ref.log;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logador {

	private static final Logger logger = Logger.getLogger(Logador.class.getName());

	private static FileHandler file;
	private static Logador instance;

	private Logador() {

	}

	public static Logador getInstance() {
		if (instance == null) {
			try {
				logger.setLevel(Level.INFO);
				logger.setLevel(Level.ALL);
				file = new FileHandler("C:\\log\\astahlog.log");
				file.setFormatter(new SimpleFormatter());
				logger.addHandler(file);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFileException e) {

			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = new Logador();
		}

		return instance;
	}

	public void log(String value) {
		logger.info(value);
	}

}
