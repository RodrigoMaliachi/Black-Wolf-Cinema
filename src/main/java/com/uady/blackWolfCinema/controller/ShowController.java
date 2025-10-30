package com.uady.blackWolfCinema.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uady.blackWolfCinema.model.CinemaRoom;
import com.uady.blackWolfCinema.model.Movie;
import com.uady.blackWolfCinema.model.Show;
import com.uady.blackWolfCinema.service.CinemaRoomService;
import com.uady.blackWolfCinema.service.MovieService;
import com.uady.blackWolfCinema.service.ShowService;

@Controller
@RequestMapping("")
public class ShowController {

    // Services for Show, Movie, and CinemaRoom
    private ShowService showService;
    private MovieService movieService;
    private CinemaRoomService cinemaRoomService;

    // Constructor for dependency injection
    public ShowController(ShowService theShowService, MovieService theMovieService, CinemaRoomService theCinemaRoomService) {
        cinemaRoomService = theCinemaRoomService;
        movieService = theMovieService;
        showService = theShowService;
    }

    @GetMapping("/show/{movieId}")
    public String getMovieShows(@PathVariable int movieId, Model model){
        List<Show> shows = showService.findShowsByMovieId(movieId);
        model.addAttribute("shows", shows);
        Movie movie = movieService.findById(movieId);
		model.addAttribute("movie", movie);
        return "show-movie";
    }

    // Displays the show schedule (cartelera)
    @GetMapping("/admin/listShows")
    public String showAdminPanel(Model model) {
        // Fetch all shows
        List<Show> shows = showService.findAll();
        // Add shows to the model
        model.addAttribute("shows", shows);
        return "shows/list-shows";
    }

    // Displays the form for adding a new show
    @GetMapping("/admin/shows/add-show")
    public String showFormToRegister(Model theModel) {
        // Create a new show
        Show theShow = new Show();
        // Add the show to the Spring model
        theModel.addAttribute("show", theShow);
        // Fetch all movies and cinema rooms for dropdowns
        List<Movie> movies = movieService.findAll();
        theModel.addAttribute("movies", movies);
        List<CinemaRoom> cinemaRooms = cinemaRoomService.getAllRooms();
        theModel.addAttribute("cinemaRooms", cinemaRooms);

        return "shows/show-form";
    }

    // Displays the form for updating an existing show
    @GetMapping("/admin/shows/update-show")
    public String showFormToUpdate(@RequestParam("showId") int showId, Model theModel) {
        // Get the show from the service
        Show theShow = showService.findById(showId);
        // Add the show to the model to populate the form
        theModel.addAttribute("show", theShow);
        // Fetch all movies and cinema rooms for dropdowns
        List<Movie> movies = movieService.findAll();
        theModel.addAttribute("movies", movies);
        List<CinemaRoom> cinemaRooms = cinemaRoomService.getAllRooms();
        theModel.addAttribute("cinemaRooms", cinemaRooms);
        return "shows/show-form";
    }

    // Deletes a show
    @GetMapping("/admin/shows/delete")
    public String delete(@RequestParam("showId") int showId, Model theModel) {
        showService.deleteById(showId);
        return "redirect:/admin/listShows";
    }

    // Saves a show (add or update)
    @PostMapping("/admin/shows/save")
    public String save(@Validated @ModelAttribute("show") Show theShow,
                       BindingResult bindingResult,
                       @RequestParam("cinemaRooms") int cinemaRoomId,
                       @RequestParam("movies") int movieId,
                       Model model) {
        
        // Validar que la fecha no esté vacía
        if(theShow.getShowDate() == null) {
            bindingResult.rejectValue("showDate", "error.showDate", "La fecha de inicio no es válida");
        }
        
        // Validar que la hora no esté vacía
        if(theShow.getShowHour() == null) {
            bindingResult.rejectValue("showHour", "error.showHour", "La hora no puede estar vacía");
        }
        
        // Validar que la fecha no sea en el pasado
        if(theShow.getShowDate() != null && theShow.getShowDate().isBefore(LocalDate.now())) {
            bindingResult.rejectValue("showDate", "error.showDate", "La fecha no puede ser en el pasado");
        }
        
        // Validar que se haya seleccionado una película
        if(movieId <= 0) {
            bindingResult.rejectValue("movie", "error.movie", "La película no puede estar vacía");
        }
        
        // Validar que se haya seleccionado una sala
        if(cinemaRoomId <= 0) {
            bindingResult.rejectValue("cinemaRoom", "error.cinemaRoom", "La sala no puede estar vacía");
        }
        
        if(bindingResult.hasErrors()) {
            List<Movie> movies = movieService.findAll();
            model.addAttribute("movies", movies);
            List<CinemaRoom> cinemaRooms = cinemaRoomService.getAllRooms();
            model.addAttribute("cinemaRooms", cinemaRooms);
            return "shows/show-form";
        }
        
        // Fetch cinema room and movie based on IDs
        CinemaRoom cinemaRoom = cinemaRoomService.findRoomById(cinemaRoomId);
        Movie movie = movieService.findById(movieId);
        // Set movie and cinema room for the show
        theShow.setMovie(movie);
        theShow.setCinemaRoom(cinemaRoom);
        // Save the show
        showService.save(theShow);
        return "redirect:/admin/listShows";
    }

}

