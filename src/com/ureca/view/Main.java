package com.ureca.view;

import com.ureca.hall.model.service.HallService;
import com.ureca.hall.model.service.HallServiceImp;
import com.ureca.movie.model.dao.service.MovieService;
import com.ureca.movie.model.dao.service.MovieServiceImp;
import com.ureca.reservation.model.service.ReservationService;
import com.ureca.reservation.model.service.ReservationServiceImp;
import com.ureca.user.model.service.UserService;
import com.ureca.user.model.service.UserServiceImp;

public class Main
{
	public static void main(String[] args)
	{
		UserService userService = new UserServiceImp();
		MovieService movieService = new MovieServiceImp();
		ReservationService reservationService = new ReservationServiceImp();
		HallService hallService = new HallServiceImp();
		new MovieUI(userService, movieService, reservationService, hallService);
	}
}
