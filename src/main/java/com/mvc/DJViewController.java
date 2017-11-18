package com.mvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DJView")
public class DJViewController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        BeatModelInterface beatModel = new BeatModel();
        beatModel.initialize();
        getServletContext().setAttribute("beatModel", beatModel);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BeatModelInterface beatModel = (BeatModel) getServletContext().getAttribute("beatModel");

        String bpm = req.getParameter("bpm");
        if (bpm == null) {
            bpm = String.valueOf(beatModel.getBPM());
        }

        String set = req.getParameter("set");
        if (set != null) {
            int bpmNumber = Integer.parseInt(bpm);
            beatModel.setBPM(bpmNumber);
        }

        String decrease = req.getParameter("decrease");
        if (decrease != null) {
            beatModel.setBPM(beatModel.getBPM() - 1);
        }
        String increase = req.getParameter("increase");
        if (increase != null) {
            beatModel.setBPM(beatModel.getBPM() + 1);
        }

        String on = req.getParameter("on");
        if (on != null) {
            beatModel.on();
        }
        String off = req.getParameter("off");
        if (off != null) {
            beatModel.off();
        }

        req.setAttribute("beatModel", beatModel);

        req.getRequestDispatcher("DJView.jsp").forward(req, resp);
    }
}
