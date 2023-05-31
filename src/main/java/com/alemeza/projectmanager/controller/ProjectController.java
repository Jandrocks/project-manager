package com.alemeza.projectmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.alemeza.projectmanager.models.Project;
import com.alemeza.projectmanager.models.User;
import com.alemeza.projectmanager.services.ProjectService;
import com.alemeza.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

	// Inyeccion de dependencias (Servicio/s)
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	@GetMapping("/dashboard")
	public String dashboard(Model viewModel, HttpSession sesion) {
		// Validar si el usuario tiene la sesion activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}

		User usuarioLog = userService.findUserById(userId);

		viewModel.addAttribute("usuario", usuarioLog);
		viewModel.addAttribute("todos_proyectos", projectService.todosLosProyectos(usuarioLog.getId()));
		viewModel.addAttribute("proyectos_lider", projectService.proyectosLider(usuarioLog));
		return "dashboard.jsp";
	}

	@GetMapping("/projects/new")
	public String formularioNuevoProj(@ModelAttribute("proyecto") Project proyecto,
			HttpSession sesion, Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}

		User usuarioLog = userService.findUserById(userId);

		viewModel.addAttribute("usuario", usuarioLog);
		return "newproject.jsp";
	}
	
	@PostMapping("/projects/new")
	public String crearProj(@Valid @ModelAttribute("proyecto") Project proyecto, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuarioLog = userService.findUserById(userId);
		if(resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuarioLog);
			return "newproject.jsp";
		}
		projectService.crearProyecto(proyecto);
		return "redirect:/dashboard";
	}
	
	
	@GetMapping("/projects/edit/{idProyecto}")
	public String formularioEditProj(@ModelAttribute("proyecto") Project proyecto,
			HttpSession sesion, Model viewModel, @PathVariable("idProyecto") Long idProyecto ) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		Project proyectoEditar = projectService.findProjectById(idProyecto);
		User usuarioLog = userService.findUserById(userId);

		viewModel.addAttribute("usuario", usuarioLog);
		viewModel.addAttribute("proyecto", proyectoEditar);
		return "edit.jsp";
	}
	
	@PutMapping("/projects/edit/{id}")
    public String guardarEdit(@Valid @ModelAttribute("proyecto") Project project, BindingResult result,
            @PathVariable("id") Long id, HttpSession sesion, Model m) {
        Long userId = (Long) sesion.getAttribute("userID");
        if (userId == null) {
            return "redirect:/";
        }
        Project pro = projectService.findProjectById(id);
        if (result.hasErrors()) {
            if (pro == null) {
                return "redirect:/dashboard";
            }
            m.addAttribute("usuario", userService.findUserById(userId));
//            m.addAttribute("proyecto", pro);
            return "edit.jsp";
        }

        projectService.crearProyecto(project);
        return "redirect:/dashboard";
    }
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userID", null);
		return "redirect:/";
	}

}