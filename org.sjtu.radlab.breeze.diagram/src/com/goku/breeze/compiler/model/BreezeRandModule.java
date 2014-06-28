package com.goku.breeze.compiler.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;

public class BreezeRandModule extends BreezeObject {
	public static final String OUTPUT_PARA_NAME = "hit";
	private static String translation;

	public static String getId() {
		return "RandModule";
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		if (translation == null) {
			Bundle bundle = Platform.getBundle("org.sjtu.radlab.breeze.diagram");
			URL fileURL = bundle.getEntry("resource/rand.smv");
			File file = null;
			FileInputStream is = null;
			try {
				file = new File(FileLocator.resolve(fileURL).toURI());
				is = new FileInputStream(file);
				byte[] buf = new byte[1024];
				StringBuilder sb = new StringBuilder();
				int length = 0;
				while ((length = is.read(buf)) > 0) {
					sb.append(new String(buf, 0, length));
				}
				translation = sb.toString();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return translation;
	}

}
