package iluha.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iluha.containers.DIContainer;
import iluha.dto.SuccessDto;
import iluha.exceptions.InvalidParameters;
import iluha.exceptions.NotFoundException;
import iluha.model.Device;
import iluha.model.Programmer;
import iluha.services.DeviceService;
import iluha.services.impl.DeviceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/device/*")
public class OneDeviceServlet extends HttpServlet {
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
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            Device device = deviceService.getById(id);
            String json = objectMapper.writeValueAsString(device);
            response.getWriter().print(json);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Device device = objectMapper.readValue(request.getReader(), Device.class);
            device.setId(Integer.parseInt(request.getPathInfo().substring(1)));
            deviceService.update(device);
            String json = objectMapper.writeValueAsString(new SuccessDto("Info about device is updated"));
            response.getWriter().print(json);
        } catch (InvalidParameters invalidParameters) {
            invalidParameters.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        try {
            deviceService.deleteById(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }


}
