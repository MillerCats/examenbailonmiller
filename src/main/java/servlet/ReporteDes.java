package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@WebServlet(name = "ReporteDes", urlPatterns = {"/reportdes"})
public class ReporteDes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReporteDes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReporteDes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonArray("data"); // Obtiene "data" del JSON
            List<Map<String, Object>> data = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                Map<String, Object> fila = new HashMap<>();
                fila.put("codiUsua", obj.get("codiUsua").getAsInt());
                fila.put("ndniUsua", obj.get("ndniUsua").getAsString());
                fila.put("logiUsua", obj.get("logiUsua").getAsString());
                fila.put("passUsua", obj.get("passUsua").getAsString());
                fila.put("niveUsua", obj.get("niveUsua").getAsInt());
                data.add(fila);
            }

            // Cargar el reporte
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);
            String rutaReporte = getServletContext().getRealPath("/reporte/usuariodes.jasper");
            File reporte = new File(rutaReporte);

            byte[] pdf = JasperRunManager.runReportToPdf(reporte.getPath(), null, datasource);

            response.setContentType("application/pdf");
            response.setContentLength(pdf.length);

            OutputStream out = response.getOutputStream();
            out.write(pdf);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generando PDF: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
