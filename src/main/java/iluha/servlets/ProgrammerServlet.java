package iluha.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iluha.containers.DIContainer;
import iluha.dto.ErrorDto;
import iluha.dto.SuccessDto;
import iluha.model.Programmer;
import iluha.repository.ProgrammerRepository;
import iluha.repository.impl.ProgrammerRepositoryImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/programmer")
public class ProgrammerServlet extends HttpServlet {

    private ProgrammerRepository programmerRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        programmerRepository = (ProgrammerRepositoryImpl) DIContainer.instance.getDependency("ProgrammerRepository");
        objectMapper = (ObjectMapper) DIContainer.instance.getDependency("ObjectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = objectMapper.writeValueAsString(programmerRepository.findAll());
        response.getWriter().print(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Programmer programmer;
        try {
            programmer = objectMapper.readValue(request.getReader(), Programmer.class);
        } catch (Exception e) {
            response.setStatus(400);
            String json = objectMapper.writeValueAsString(new ErrorDto("Wrong request"));
            response.getWriter().print(json);
            return;
        }
        programmerRepository.save(programmer);
        String json = objectMapper.writeValueAsString(new SuccessDto("Programmer saved"));
        response.getWriter().print(json);
    }

}
