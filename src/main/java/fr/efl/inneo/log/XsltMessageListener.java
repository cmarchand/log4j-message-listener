package fr.efl.inneo.log;

import javax.xml.transform.SourceLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.saxon.s9api.Axis;
import net.sf.saxon.s9api.MessageListener;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmNodeKind;

public class XsltMessageListener implements MessageListener {

	@Override
	public void message(XdmNode content, boolean terminate, SourceLocator locator) {
		/*
		 * Le contenu du message (content) est soit directement du texte,
		 * soit un document XML de type log qui suit la structure suivante : 
		 * <log level="niveau de log" 
		 * 		source="xslt ou schéma sch source" ... >Message du log</log>
		 */
		
		XdmNode xdmMessage = (XdmNode) content.axisIterator(Axis.CHILD).next();
		String textMessage = xdmMessage.getStringValue();
		XdmNodeKind kind = xdmMessage.getNodeKind(); 
		if (kind.equals(XdmNodeKind.TEXT)) {
			System.out.println(textMessage);
		} else {
			String level = xdmMessage.getAttributeValue(new QName("level"));
			String source = xdmMessage.getAttributeValue(new QName("source"));
			Logger logger = LoggerFactory.getLogger(source);
			switch (level) {
			case "info":
				logger.info(textMessage);
				break;
			case "warn":
				logger.warn(textMessage);
				break;
			case "error":
				logger.error(textMessage);
				break;			
			default:
				break;
			}			
		}		
	}
}
