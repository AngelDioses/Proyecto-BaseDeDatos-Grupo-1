
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
public class PDFPartidaIngreso {
    public void generarPDF(JTable tablaPartida_I) {
        try {
            // Obtener el modelo de la tabla
            TableModel model = tablaPartida_I.getModel();

            // Generar el contenido LaTeX
            StringBuilder latexContent = new StringBuilder();
            latexContent.append("\\documentclass{article}\n");
            latexContent.append("\\usepackage[utf8]{inputenc}\n");
            latexContent.append("\\begin{document}\n");
            latexContent.append("\\section{Reporte de Partidas}\n");
            latexContent.append("\\begin{tabular}{|c|c|c|c|c|c|}\n");
            latexContent.append("\\hline\n");
            latexContent.append("CodPartida & CodPartidas & DesPartida & tUniMed & eUniMed & Vigente \\\\\n");
            latexContent.append("\\hline\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                latexContent.append(model.getValueAt(i, 0)).append(" & ");
                latexContent.append(model.getValueAt(i, 1)).append(" & ");
                latexContent.append(model.getValueAt(i, 2)).append(" & ");
                latexContent.append(model.getValueAt(i, 3)).append(" & ");
                latexContent.append(model.getValueAt(i, 4)).append(" & ");
                latexContent.append(model.getValueAt(i, 5)).append(" \\\\\n");
            }

            latexContent.append("\\hline\n");
            latexContent.append("\\end{tabular}\n");
            latexContent.append("\\end{document}\n");

            // Escribir contenido LaTeX a un archivo .tex
            try (FileWriter fw = new FileWriter("reportePartidasIngreso.tex")) {
                fw.write(latexContent.toString());
                System.out.println("Archivo .tex generado con éxito.");
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo .tex: " + e.getMessage());
            }

            // Compilar report.tex a PDF usando pdflatex
            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\angel\\AppData\\Local\\Programs\\MiKTeX\\miktex\\bin\\x64\\pdflatex.exe", "reportePartidasIngreso.tex");
            pb.directory(new File(".")); // Directorio actual
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("PDF compilado con éxito.");
                // Mover el PDF generado
                Files.move(Paths.get("reportePartidasIngreso.pdf"), Paths.get("C:\\Users\\angel\\Desktop\\REPORTES PDF\\Partidas\\reportePartidasIngreso.pdf"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("PDF movido a la ubicación de destino.");
            } else {
                System.err.println("Error al compilar el archivo LaTeX. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error en la generación de PDF: " + e.getMessage());
        }
    }
}
