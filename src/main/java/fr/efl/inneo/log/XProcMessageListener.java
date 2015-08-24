package fr.efl.inneo.log;

import java.io.StringReader;

import javax.xml.transform.stream.StreamSource;

import com.xmlcalabash.core.XProcRunnable;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.value.TextFragmentValue;

public class XProcMessageListener implements com.xmlcalabash.core.XProcMessageListener {

    @Override
    public void error(Throwable arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void error(XProcRunnable arg0, XdmNode arg1, String arg2, QName arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void fine(XProcRunnable arg0, XdmNode arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void finer(XProcRunnable arg0, XdmNode arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void finest(XProcRunnable arg0, XdmNode arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void info(XProcRunnable step, XdmNode node, String message) {

        /* Chargement du document message via Saxon S9API & XdmNode */
        Processor proc = new Processor(false);
        XdmNode xdmMessage = null;
        if (message.startsWith("<log")) {
            try {
                xdmMessage = proc.newDocumentBuilder().build(new StreamSource(new StringReader(message)));
            } catch (SaxonApiException e) {
                e.printStackTrace(System.err);
            }
        } else {
            xdmMessage = new XdmNode(new TextFragmentValue(message, ""));
        }
        new XsltMessageListener().message(xdmMessage, false, null);
    }

    @Override
    public void warning(Throwable arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void warning(XProcRunnable arg0, XdmNode arg1, String arg2) {
        // TODO Auto-generated method stub

    }

}
