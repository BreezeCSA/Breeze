package com.goku.breeze.util.smv;

import java.io.IOException;

public class SMVFactory {
	private static SMVFactory instance = new SMVFactory();

	public static SMVFactory getInstance() {
		return instance;
	}

	private SMVFactory() {

	}

	public SMV getSMVInstance() throws IOException {
		SMV smv = new SMV("/home/goku/NuSMV-Breeze/bin/NuSMV -int");
		return smv;
	}
}
