
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
public class PDFConsultaProyecto {
    public void generarPDF(JTable tablaProyecto) {
        try {
            // Obtener el modelo de la tabla
            TableModel model = tablaProyecto.getModel();

            // Generar el contenido LaTeX
            StringBuilder latexContent = new StringBuilder();
            latexContent.append("\\documentclass{article}\n");
            latexContent.append("\\usepackage[utf8]{inputenc}\n");
            latexContent.append("\\usepackage{longtable}\n"); // Para tablas largas
            latexContent.append("\\begin{document}\n");
            latexContent.append("\\section{Reporte de Proyectos}\n");
            latexContent.append("\\begin{longtable}{|c|c|c|c|c|c|c|c|c|c|c|c|}\n");
            latexContent.append("\\hline\n");
            latexContent.append("CodPyto & NomPyto & EmplJefeProy & CiaContrata & CodCliente & FecReg & EstPyto & FecEstado & CostoTotal & AnnoIni & AnnoFin & Vigente \\\\\n");
            latexContent.append("\\hline\n");
            latexContent.append("\\endfirsthead\n");
            latexContent.append("\\hline\n");
            latexContent.append("CodPyto & NomPyto & EmplJefeProy & CiaContrata & CodCliente & FecReg & EstPyto & FecEstado & CostoTotal & AnnoIni & AnnoFin & Vigente \\\\\n");
            latexContent.append("\\hline\n");
            latexContent.append("\\endhead\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                latexContent.append(model.getValueAt(i, 0)).append(" & ");
                latexContent.append(model.getValueAt(i, 1)).append(" & ");
                latexContent.append(model.getValueAt(i, 2)).append(" & ");
                latexContent.append(model.getValueAt(i, 3)).append(" & ");
                latexContent.append(model.getValueAt(i, 4)).append(" & ");
                latexContent.append(model.getValueAt(i, 5)).append(" & ");
                latexContent.append(model.getValueAt(i, 6)).append(" & ");
                latexContent.append(model.getValueAt(i, 7)).append(" & ");
                latexContent.append(model.getValueAt(i, 8)).append(" & ");
                latexContent.append(model.getValueAt(i, 9)).append(" & ");
                latexContent.append(model.getValueAt(i, 10)).append(" & ");
                latexContent.append(model.getValueAt(i, 11)).append(" \\\\\n");
            }

            latexContent.append("\\hline\n");
            latexContent.append("\\end{longtable}\n");
            latexContent.append("\\end{document}\n");

            // Escribir contenido LaTeX a un archivo .tex
            try (FileWriter fw = new FileWriter("reporteProyectos.tex")) {
                fw.write(latexContent.toString());
                System.out.println("Archivo .tex generado con éxito.");
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo .tex: " + e.getMessage());
            }

            // Compilar report.tex a PDF usando pdflatex
            ProcessBuilder pb = new ProcessBuilder("C:\\Users\\angel\\AppData\\Local\\Programs\\MiKTeX\\miktex\\bin\\x64\\pdflatex.exe", "reporteProyectos.tex");
            pb.directory(new File(".")); // Directorio actual
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("PDF compilado con éxito.");
                // Mover el PDF generado
                Files.move(Paths.get("reporteProyectos.pdf"), Paths.get("C:\\Users\\angel\\Desktop\\REPORTES PDF\\Consulta Proyecto\\reporteProyectos.pdf"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("PDF movido a la ubicación de destino.");
            } else {
                System.err.println("Error al compilar el archivo LaTeX. Código de salida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error en la generación de PDF: " + e.getMessage());
        }
    }
}
