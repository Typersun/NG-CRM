package iluha.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iluha.containers.DIContainer;
import iluha.dto.ErrorDto;
import iluha.exceptions.InvalidParameters;
import iluha.exceptions.SomethingWrongWithDatabaseException;
import iluha.model.Device;
import iluha.services.DeviceService;
import iluha.services.impl.DeviceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/device")
public class DeviceServlet extends HttpServlet {

    private DeviceService deviceService;
    private ObjectMapper objectMapper;
    @Override
    public void init() throws ServletException {
        deviceService = (DeviceServiceImpl) DIContainer.instance.getDependency("DeviceService");
        objectMapper = (ObjectMapper) DIContainer.instance.getDependency("ObjectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = objectMapper.writeValueAsString(deviceService.findAll());
            response.getWriter().print(json);
        } catch (SomethingWrongWithDatabaseException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Device device;
        try {
            device = objectMapper.readValue(request.getReader(), Device.class);
        } catch (Exception e) {
            String json = objectMapper.writeValueAsString(new ErrorDto("Wrong request"));
            response.getWriter().print(json);
            return;
        }
        try {
            deviceService.save(device);
        } catch (InvalidParameters invalidParameters) {
            invalidParameters.printStackTrace();
            return;
        }

    }

}
