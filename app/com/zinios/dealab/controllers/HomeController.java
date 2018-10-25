package com.zinios.dealab.controllers;

import play.Configuration;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	@Inject
	Configuration configuration;

	/**
	 * An action that renders an HTML page with a welcome message.
	 * The configuration in the <code>routes</code> file means that
	 * this method will be called when the application receives a
	 * <code>GET</code> request with a path of <code>/</code>.
	 */
	public Result index() {
		return ok("Dealab-Backend started \nApp version - "
				+ configuration.getString("app.version") + "\n\n\n                           ______                     \n" +
				" _________        .---\"\"\"      \"\"\"---.              \n" +
				":______.-':      :  .--------------.  :             \n" +
				"| ______  |      | :                : |             \n" +
				"|:______B:|      | | >Dealab is     | |             \n" +
				"|:______B:|      | |  Running...    | |             \n" +
				"|:______B:|      | |                | |             \n" +
				"|:_____:  |      | | >(Y)_(Y)       | |             \n" +
				"|    ==   |      | :                : |             \n" +
				"|       O |      :  '--------------'  :             \n" +
				"|       o |      :'---...______...---'              \n" +
				"|       o |-._.-i___/'            \\._              \n" +
				"|'-.____o_|   '-.   '-...______...-'  `-._          \n" +
				":_________:      `.____________________   `-.___.-. \n" +
				"                 .'.eeeeeeeeeeeeeeeeee.'.      :___:\n" +
				"    ****       .'.eeeeeeeeeeeeeeeeeeeeee.'.         \n" +
				"              :____________________________:\n");
	}

}
