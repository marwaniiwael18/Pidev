/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;


import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import trenna.services.evenementservice;

/**
 *
 * @author medom
 */
public class Pdf {
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        evenementservice su = new evenementservice() ;        
        List<evenement> list=su.readAll();    
        document.add(new Paragraph("La liste des users :"));
        document.add(new Paragraph("     "));
         for(evenement u:list)
        {
            
        document.add(new Paragraph("numero d'evenement :"+u.getIdEven()));
        document.add(new Paragraph("nom :"+u.getNom()));
        document.add(new Paragraph("prix:"+u.getPrix()));
        document.add(new Paragraph("date d evenement :"+u.getDateEven()));
        document.add(new Paragraph("les recompense :"+u.getRecompense()));
        
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }    
    
}
