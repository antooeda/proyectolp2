/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.lp2.proyecto.demo.controller;

import cl.ufro.lp2.proyecto.demo.dao.PlanDao;
import cl.ufro.lp2.proyecto.demo.modelo.Plan;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Antonia
 */
@Controller
public class PlanController {
    
  @Autowired
  private PlanDao pDao;
  
  @GetMapping("/Plan")
    public String crearPlan(Model model){
       List<Plan>planes= pDao.findAll();
        model.addAttribute("listaPlanes", planes);
        return "Planes";
    }
    
      @PostMapping("/crearPlanesForm")
    public void obtenerPlanes(@ModelAttribute Plan plan, HttpServletResponse response) throws IOException{
       pDao.save(plan);
        response.sendRedirect("obtenerPlanes");
    }
    
     @GetMapping("/obtenerPlanes")
    public String getPlanesView(Model model){
      List<Plan> planes = pDao.findAll();
      model.addAttribute("planes", planes);
      return "planes";
    }
}
