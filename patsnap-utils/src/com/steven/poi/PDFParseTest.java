/*package com.steven.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;

public class PDFParseTest {
    @Test
    public void testPDFParseImage() {
        com.patsnap.parser.pdf.kr.KRPdfParser pdfParser = null;
        String fileName = "test.pdf";
        extractImage(fileName);
    }
    
    public static void extractImage(String filename) {
        
        PdfReader reader = null;
        try {
            //读取pdf文件
            reader = new PdfReader(filename);
            //获得pdf文件的页数
            int sumPage = reader.getNumberOfPages();
            //读取pdf文件中的每一页
            for (int i = 1; i <= sumPage; i++) {
                //得到pdf每一页的字典对象
                PdfDictionary dictionary = reader.getPageN(i);
                //通过RESOURCES得到对应的字典对象
                PdfDictionary res = (PdfDictionary)PdfReader.getPdfObject(dictionary.get(PdfName.RESOURCES));
                //得到XOBJECT图片对象
                PdfDictionary xobj = (PdfDictionary)PdfReader.getPdfObject(res.get(PdfName.XOBJECT));
                if (xobj != null) {
                    for (Iterator it = xobj.getKeys().iterator(); it.hasNext();) {
                        PdfObject obj = xobj.get((PdfName)it.next());
                        if (obj.isIndirect()) {
                            PdfDictionary tg = (PdfDictionary)PdfReader.getPdfObject(obj);
                            PdfName type = (PdfName)PdfReader.getPdfObject(tg.get(PdfName.SUBTYPE));
                            if (PdfName.IMAGE.equals(type)) {
                                PdfObject object = reader.getPdfObject(obj);
                                if (object.isStream()) {
                                    PRStream prstream = (PRStream)object;
                                    byte[] b;
                                    try {
                                        b = reader.getStreamBytes(prstream);
                                    }
                                    catch (UnsupportedPdfException e) {
                                        b = reader.getStreamBytesRaw(prstream);
                                    }
                                    FileOutputStream output =
                                        new FileOutputStream(String.format("e:/pdf/output%d.jpg", i));
                                    output.write(b);
                                    output.flush();
                                    output.close();
                                }
                            }
                        }
                    }
                }
            }
            
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
*/