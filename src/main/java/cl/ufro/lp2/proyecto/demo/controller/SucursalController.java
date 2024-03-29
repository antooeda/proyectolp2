/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ufro.lp2.proyecto.demo.controller;

import cl.ufro.lp2.proyecto.demo.dao.SucursalDao;
import cl.ufro.lp2.proyecto.demo.dao.UsuarioDao;
import cl.ufro.lp2.proyecto.demo.modelo.Sucursal;
import cl.ufro.lp2.proyecto.demo.modelo.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Antonia
 */
@Controller
public class SucursalController {
  
    
    @Autowired
    private SucursalDao sDAO;
    @Autowired
    private UsuarioDao uDAO;
    
    @GetMapping("/Sucursal")
    public String crearSucursal(Model model){
       List<Sucursal>sucursales= sDAO.findAll();
        model.addAttribute("listaSucursales", sucursales);
        return "Sucursales";
    }
    
     @PostMapping("/crearSucursal")
    public void crearSucursal(@ModelAttribute Sucursal sucursal, HttpServletResponse response) throws IOException{
        //System.out.println(sucursal.getNombre());
        sDAO.save(sucursal);
        response.sendRedirect("crearSucursal");
    }
    
    
    @GetMapping("/obtenerSucursales")
    public String getSucursalesView(Model model){
        
        List<Sucursal> sucursales = sDAO.findAll();
        
        model.addAttribute("sucursales", sucursales);
        
        
        return "sucursales";
    }

    
     
    @GetMapping("/Sucursal/{id}")
    public String cargarSucursal(Model model, @PathVariable(value="id") Integer id){
          //model.addAttribute("dato", "Temuco");
          //model.addAttribute("sucursal", new Sucursal());
          
          
          Sucursal aDesplegar = sDAO.findById(id.intValue());
          
          model.addAttribute("sucursal", aDesplegar);
      
        return "sucursalTemplate";
    }
    
}

