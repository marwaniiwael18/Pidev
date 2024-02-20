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
import trenna.services.ServiceArb;
import trenna.services.ServiceMatch;


/**
 *
 * @author Amirov
 */
public class Pdf_1 {
    
    
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceMatch m=new ServiceMatch();
        List<Match> list=m.afficher();    
        document.add(new Paragraph("La liste des Match :"));
        document.add(new Paragraph("     "));
         for(Match u:list)
        {
            
        document.add(new Paragraph("Id_Match :"+u.getIdMatch()));
        document.add(new Paragraph("Type_match:"+u.getTypeMatch()));
        document.add(new Paragraph("date_match :"+u.getDateMatch()));
                document.add(new Paragraph("Id_Arb :"+u.getIdArb()));


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
       
    public void GeneratePdfm(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        ServiceArb m=new ServiceArb();
        List<Arbitre> list=m.afficherArb();    
        document.add(new Paragraph("La liste des arbitres :"));
        document.add(new Paragraph("     "));
         for(Arbitre u:list)
        {
                            document.add(new Paragraph("Id_Arb :"+u.getIdArb()));
        document.add(new Paragraph("Nom_Arb :"+u.getNomArb()));
        document.add(new Paragraph("Specialité:"+u.getSpecialite()));


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
    
}