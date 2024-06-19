package Modelo.GenerarExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public void generarExcel(List<JTable> tablas, List<String> nombresHojas, String nombreArchivo) {
        try {
            Workbook workbook = new XSSFWorkbook();

            for (int t = 0; t < tablas.size(); t++) {
                JTable tabla = tablas.get(t);
                String nombreHoja = nombresHojas.get(t);

                TableModel model = tabla.getModel();
                Sheet sheet = workbook.createSheet(nombreHoja);

                // Crear la fila de encabezados
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(model.getColumnName(col));
                }

                // Agregar los datos de la tabla a las filas del Excel
                for (int row = 0; row < model.getRowCount(); row++) {
                    Row excelRow = sheet.createRow(row + 1);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Cell cell = excelRow.createCell(col);
                        Object value = model.getValueAt(row, col);
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Double) {
                            cell.setCellValue((Double) value);
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        } else {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }

            // Escribir el archivo Excel
            try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo)) {
                workbook.write(outputStream);
                System.out.println("Archivo Excel generado exitosamente: " + nombreArchivo);
            } catch (IOException e) {
                System.err.println("Error al escribir el archivo Excel: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Error en la generación del archivo Excel: " + e.getMessage());
        }
    }
}
