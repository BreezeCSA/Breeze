package com.goku.breeze.compiler.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.goku.breeze.compiler.event.TranslationListener;
import com.goku.breeze.compiler.event.impl.ActionAddLTLSPEC;
import com.goku.breeze.compiler.exception.BaseException;
import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;
import com.goku.breeze.compiler.main.textFormatter.SimpleSMVSourceFormatter;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezeRandModule;

public class MainTranslator {
	public static void main(String[] args) {
		File file = new File("./example/deadlock2.xml");
		try {
			BreezeXMLParser parser = new BreezeXMLParser(file, null, new TranslationListener[] { new ActionAddLTLSPEC(null) });
			List<BaseException> exceps = parser.getExceptionList();
			for (BaseException be : exceps)
				System.out.println("" + be.getLineNumber() + ":" + be.getColumnNumber() + "->" + be.getMessage());
			MainTranslator mt = new MainTranslator(parser.getTopArch(), new SimpleSMVSourceFormatter());
			System.out.println("\n\n\n" + mt.getTranslation());
			File outputFile = new File("./example/test.smv");
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(mt.getTranslation().getBytes());
			fos.close();
			fos = new FileOutputStream("arch.out");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(parser.getTopArch());
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	BaseSMVSourceFormatter formatter;

	BreezeRandModule randModule = null;
	BreezeArch topArch;
	String translation = null;

	/**
	 * @author goku
	 * @param arch
	 */
	public MainTranslator(BreezeArch arch, BaseSMVSourceFormatter formatter) {
		this.topArch = arch;
		this.randModule = new BreezeRandModule();
		this.formatter = formatter;
		this.translation = this.translate(this.topArch);
	}

	public String getTranslation() {
		return this.translation;
	}

	private void postOrderWalk(StringBuilder buffer, BreezeObject node) {
		if (node instanceof BreezeArch) {
			BreezeArch ins = (BreezeArch) node;
			Map<String, BreezeNode> nodeMap = ins.getNodeList();
			if (nodeMap != null) for (String key : nodeMap.keySet())
				this.postOrderWalk(buffer, nodeMap.get(key));
			buffer.append(node.transalte(this.formatter)).append(ins.getConnectionGraph().transalte(this.formatter));
		} else if (node instanceof BreezeNode) {
			BreezeNode ins = (BreezeNode) node;
			this.postOrderWalk(buffer, ins.getChild());
			buffer.append(ins.transalte(this.formatter));
		}
	}

	private String translate(BreezeArch arch) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.randModule.transalte(this.formatter));
		this.postOrderWalk(sb, this.topArch);
		return sb.toString();
	}
}
