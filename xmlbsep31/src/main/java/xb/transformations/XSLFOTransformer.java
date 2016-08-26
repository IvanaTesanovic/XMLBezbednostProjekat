package xb.transformations;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;


import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 * 
 * Primer demonstrira koriscenje programskog API-a za 
 * renderovanje PDF-a na osnovu XSL-FO transformacije.
 *
 */
public class XSLFOTransformer {
	
	private FopFactory fopFactory;
	
	private TransformerFactory transformerFactory;
	
	public XSLFOTransformer() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("src/main/java/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();
	}

	public void transformFile(String foPath, String xmlPath, String pdfPath) throws Exception {
		File xsltFile = new File(foPath);
		StreamSource transformSource = new StreamSource(xsltFile);
		StreamSource source = new StreamSource(new File(xmlPath));
		FOUserAgent userAgent = fopFactory.newFOUserAgent();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);
		Result res = new SAXResult(fop.getDefaultHandler());
		xslFoTransformer.transform(source, res);

		File pdfFile = new File(pdfPath);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
		out.write(outStream.toByteArray());

		out.close();

	}
	
	public static void main(String[] args) throws Exception {
		new XSLFOTransformer().transformFile("transform/akt-fo.xsl", "transform/noviValidanAkt.xml", "transform/akt.pdf");
	}

}
