/*
 * Sin licencia.
 * Uso para capacitación
 * 2021 Año de la Prevención y Lucha contra el COVID-19.
 */
package com.mza.biblioteca.controladores;

import com.mza.biblioteca.entidades.Autor;
import com.mza.biblioteca.servicios.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Adrian E. Camus
 */
@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    AutorService autorServicio;

    @GetMapping("/1")
    public String autores(ModelMap modelo) {
        List<Autor> autores = autorServicio.buscaAutores();
       modelo.addAttribute("autores", autores);
        return "autores";// lo uso al final con el metodo para buscar todos 
    }
    @GetMapping("/lista")
    public String listaLibros(ModelMap modelo, @RequestParam(required = false) String buscar) {
        //si el parametro "buscar" NO es nulo, agrega al modelo una lista de libros buscados
        if (buscar != null)
        {
            modelo.addAttribute("libros", autorServicio.buscaPorNombre(buscar));
        } else //si no viene parametro de busqueda, agrega al modelo una lista con todos los libros
        {
            modelo.addAttribute("libros", autorServicio.buscaAutores());
        }
        return "autores";
    
    @GetMapping("/registroAutor")
    public String registroAutor (ModelMap modelo,@RequestParam(required = false)String id) {
            return "nAutor.html";
    }
    
    @PostMapping("/registroAutor")
    public String registro(ModelMap modelo, @RequestParam String nombre) {
                       
        try
        {
            autorServicio.creaAutor(nombre);
            modelo.put("exito", "Registro Exitoso");
            return "nAutor";
        } catch (Exception e)
        {
            modelo.put("error", "Ya existe un Autor con ese Nombre");
            return "nAutor";
        }
        
    }

    @GetMapping("/lista")
    public String listaLibros(ModelMap modelo, @RequestParam(required = false) String buscar) {
        //si el parametro "buscar" NO es nulo, agrega al modelo una lista de libros buscados
        if (buscar != null)
        {
            modelo.addAttribute("libros", autorServicio.buscaPorNombre(buscar));
        } else //si no viene parametro de busqueda, agrega al modelo una lista con todos los libros
        {
            modelo.addAttribute("libros", autorServicio.buscaAutores());
        }
        return "autores";
    }
//     @GetMapping("/borrar")// tengo que crear todo para modificar esto es solko una copia del controlador 
//    public String borrar(ModelMap modelo, @RequestParam(required = false) String id){
//    System.out.println("ACA ESTOY INGRESANDO AL GET");
//        if (id != null)
//        {
//            Optional<Libro> optional = libroServicio.buscarPorId(id);
//            if (optional.isPresent())
//            {
//                modelo.addAttribute("libro", optional.get());
//            } else
//            {
//                return "redirect:/libros/lista";
//            }
//        } else
//        {
//            return "borrar";
//        }
//        return "borrar";
//    }
//
//    @PostMapping("/borrar")
//    public String borrarAutor(ModelMap modelo, RedirectAttributes redirectAttributes, @ModelAttribute Autor autor){
//                        
//        try
//        {
//            autorServicio.;////tengo que crear el servicio de borrar 
//            modelo.put("exito", "El libro se dio de baja");
//            return "redirect:/libros/lista";
//
//        } catch (MiExcepcion e)
//        {
//            modelo.put("error", "NO SE BORRO EL LIBRO");
//            return "borrar";
//        }
}
