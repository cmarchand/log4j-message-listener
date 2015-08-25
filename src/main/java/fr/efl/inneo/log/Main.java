package fr.efl.inneo.log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.MessageListener;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

public class Main {

	public static void main(String[] args) throws SaxonApiException, IOException {
		
		String process = args[0];
		String[] process_args = Arrays.copyOfRange(args, 1, args.length);
		
		switch (process) {
		case "saxon":
			LaunchSaxon(process_args);
			break;
		case "calabash" :
			LaunchCalabash(process_args);
			break;
		default:
			break;
		}
	}

	public static void LaunchSaxon(String[] args) throws SaxonApiException {
		
		String inputURI = args[0];
		String xslURI = args[1];
		String outputURI = args[2];
		
		Processor proc = new Processor(false);
		XsltCompiler comp = proc.newXsltCompiler();
		XsltExecutable exp = comp.compile(new StreamSource(new File(xslURI)));
		XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File(inputURI)));
		Serializer out = new Serializer();
		out.setOutputProperty(Serializer.Property.METHOD, "xml");
		out.setOutputProperty(Serializer.Property.INDENT, "yes");
		out.setOutputFile(new File(outputURI));
		XsltTransformer trans = exp.load();
		trans.setInitialContextNode(source);
		trans.setDestination(out);
		MessageListener listener = new XsltMessageListener();
		trans.setMessageListener(listener);
		trans.transform();
	}
	
	public static void LaunchCalabash(String[] args) throws IOException {
		com.xmlcalabash.drivers.Main.main(args);
	}

}
