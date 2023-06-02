package com.example.productmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.productmanager.models.Project;
import com.example.productmanager.models.Task;
import com.example.productmanager.models.User;
import com.example.productmanager.services.ProjectService;
import com.example.productmanager.services.TaskService;
import com.example.productmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

	// Inyeccion de dependencias (Servicio/s)
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;

	@GetMapping("/dashboard")
	public String dashboard(Model viewModel, HttpSession sesion) {
		// Validar si el usuario tiene la sesion activa
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}

		User usuarioLog = userService.findUserById(userId);

		viewModel.addAttribute("usuario", usuarioLog);
//		viewModel.addAttribute("proyectos_no_suscritos", projectService.todosLosProyectos(usuarioLog.getId()));
		viewModel.addAttribute("proyectos_lider", projectService.proyectosLider(usuarioLog));
		viewModel.addAttribute("proyectos_asignados", projectService.getUsuariosAsignados(usuarioLog,usuarioLog ));
		viewModel.addAttribute("noasignados", projectService.getUsuariosNoAsignados(usuarioLog));
		
		return "dashboard.jsp";
	}

	@GetMapping("/projects/new")
	public String formularioNuevoProj(@ModelAttribute("proyecto") Project proyecto, HttpSession sesion,
			Model viewModel) {
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
		if (resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuarioLog);
			return "newproject.jsp";
		}
		projectService.crearProyecto(proyecto);
		return "redirect:/dashboard";
	}

	@GetMapping("/projects/edit/{idProyecto}")
	public String formularioEditProj(@ModelAttribute("proyecto") Project proyecto, HttpSession sesion, Model viewModel,
			@PathVariable("idProyecto") Long idProyecto) {
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
			return "edit.jsp";
		}

		projectService.crearProyecto(project);
		return "redirect:/dashboard";
	}

	@GetMapping("/projects/{idProyecto}")
	public String mostrarProyecto(Model viewModel, HttpSession sesion, @PathVariable("idProyecto") Long idProyecto) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		Project pro = projectService.findProjectById(idProyecto);
		viewModel.addAttribute("proyecto", pro);
		return "mostrar.jsp";
	}

	@GetMapping("/project/{idProyecto}/{idUsuario}/{accion}")
	public String crearVinculo(@PathVariable("idProyecto") Long idProyecto,
			@PathVariable("idUsuario") Long idUsuario,
			@PathVariable("accion") String accion ,HttpSession sesion) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		Project pro = projectService.findProjectById(idProyecto);
		boolean join = (accion.equals("join"));
		User usuario = userService.findUserById(idUsuario);
		
		projectService.unirseEquipo(pro, usuario, join);
		
		return "redirect:/dashboard";
	}
	
	// RUTA PARA BORRAR PROYECTO
	@GetMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id, HttpSession sesion) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/";
		}
//		User user = userService.findUserById(userLog);
//		if (user == null) {
//			return "redirect:/";
//		}
		Project project = projectService.findProjectById(id);
		if(userLog == project.getLider().getId()) {
			projectService.deleteProject(id);
			return"redirect:/dashboard";
		}
		return"redirect:/dashboard";
	}
	
	@GetMapping("/projects/{proyectoId}/tasks")
	public String formularioTasks(@ModelAttribute("task") Task task,
			@PathVariable("proyectoId") Long proyectoId,HttpSession sesion, Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		Project pro = projectService.findProjectById(proyectoId);
		viewModel.addAttribute("proyecto", pro);
		return "tasks.jsp";
	}
	
	@PostMapping("/project/{idEvento}/tarea")
	public String comentario(@Valid	@ModelAttribute("task") Task task, BindingResult resultado,
			HttpSession sesion, Model viewModel, @PathVariable("idEvento") Long idEvento) {	
		Long userId = (Long) sesion.getAttribute("userID");
		if(userId == null) {
			return "redirect:/"; 
		}
//		if(resultado.hasErrors()) {
//			Project pro = projectService.findProjectById(idEvento);
//			viewModel.addAttribute("proyecto", pro);
//			return "tasks.jsp";
//			
//		}
//		if(comentario.equals("")) {
//			redirecAttr.addFlashAttribute("error", "Por favor escribe un comentario");
//			return "redirect:/events/"+idEvento;
//		}
		Project proyecto = projectService.findProjectById(idEvento);
		User usuario = userService.findUserById(userId);
		taskService.crearTarea(task.getTask(), usuario, proyecto);
		
		return "redirect:/events/"+idEvento;
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userID", null);
		return "redirect:/";
	}

}
