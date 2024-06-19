
package Modelo.GenerarPDF;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author angel
 */
public class PDFVenta {
    public void generarPDF(JTable tablaVenta) {
        try {
            // Obtener el modelo de la tabla
            TableModel model = tablaVenta.getModel();

            // Generar el contenido LaTeX
            StringBuilder latexContent = new StringBuilder();
            latexContent.append("\\documentclass{article}\n");
            latexContent.append("\\usepackage[utf8]{inputenc}\n");
            latexContent.append("\\usepackage{longtable}\n"); // Para tablas largas
            latexContent.append("\\begin{document}\n");
            latexContent.append("\\section{Reporte de Ventas}\n");
            latexContent.append("\\begin{longtable}{|c|c|c|c|c|c|c|}\n");
            latexContent.append("\\hline\n");
            latexContent.append("Secuencia & CodPartida & DesPartida & Semilla & Neto & IGV & Total \\\\\n");
            latexContent.append("\\hline\n");
            latexContent.append("\\endfirsthead\n");
            latexContent.append("\\hline\n");
            latexContent.append("Secuencia & CodPartida & DesPartida & Semilla & Neto & IGV & Total \\\\\n");
            latexContent.append("\\hline\n");
            latexContent.append("\\endhead\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    latexContent.append(model.getValueAt(i, j)).append(j < model.getColumnCount() - 1 ? " & " : " \\\\\n");
                }
            }

            latexContent.append("\\hline\n");
            latexContent.append("\\end{longtable}\n");
            latexContent.append("\\end{document}\n");

            // Escribir contenido LaTeX a un archivo .tex
            try (FileWriter fw = new FileWriter("reporteVenta.tex")) {
                fw.write(latexContent.toString());
                System.out.println("Archivo .tex generado con éxito.");
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo .tex: " + e.getMessage());
            }

            // Compilar report.tex a PDF usando pdflatex
            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\angel\\AppData\\Local\\Programs\\MiKTeX\\miktex\\bin\\x64\\pdflatex.exe", "reporteVenta.tex");
            pb.directory(new File(".")); // Directorio actual
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("PDF compilado con éxito.");
                // Mover el PDF generado
                Files.move(Paths.get("reporteVenta.pdf"), Paths.get("C:\\Users\\angel\\Desktop\\REPORTES PDF\\Venta\\reporteVenta.pdf"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("PDF movido a la ubicación de destino.");
            } else {
                System.err.println("Error al compilar el archivo LaTeX. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error en la generación de PDF: " + e.getMessage());
        }
    }
}