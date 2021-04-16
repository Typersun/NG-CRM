package iluha.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import iluha.containers.DIContainer;
import iluha.dto.ErrorDto;
import iluha.dto.SuccessDto;
import iluha.exceptions.NotFoundException;
import iluha.model.Programmer;
import iluha.repository.ProgrammerRepository;
import iluha.repository.impl.ProgrammerRepositoryImpl;
import iluha.services.ProgrammerService;
import iluha.services.impl.ProgrammerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/programmer/*")
public class OneProgrammerServlet extends HttpServlet {
    private ProgrammerService programmerService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        programmerService = (ProgrammerServiceImpl) DIContainer.instance.getDependency("ProgrammerService");
        objectMapper = (ObjectMapper) DIContainer.instance.getDependency("ObjectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getPathInfo().substring(1));
            Programmer programmer = programmerService.getById(id);
            String json = objectMapper.writeValueAsString(programmer);
            response.getWriter().print(json);
        } catch (NotFoundException e) {
            String json = objectMapper.writeValueAsString(e);
            response.getWriter().print(json);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Programmer programmer = objectMapper.readValue(request.getReader(), Programmer.class);
            programmer.setId(Integer.parseInt(request.getPathInfo().substring(1)));
            programmerService.update(programmer);
            String json = objectMapper.writeValueAsString(new SuccessDto("Info updated"));
            response.getWriter().print(json);
        } catch (NotFoundException e) {
            String json = objectMapper.writeValueAsString(e);
            response.getWriter().print(json);
        } catch (Exception e) {
            response.setStatus(400);
            String json = objectMapper.writeValueAsString(new ErrorDto("Wrong request"));
            response.getWriter().print(json);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        try {
            programmerService.deleteById(id);
            String json = objectMapper.writeValueAsString(new SuccessDto("Programmer deleted"));
            response.getWriter().print(json);
        } catch (Exception e) {
            response.setStatus(400);
            String json = objectMapper.writeValueAsString(new ErrorDto("Wrong request"));
            response.getWriter().print(json);
        }
    }
}
