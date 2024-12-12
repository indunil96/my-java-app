package aiwa.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiwa.entity.Item;
import aiwa.model.ItemModel;

@WebServlet("/ItemListController")
public class ItemListController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.PARAMETER
		String word = request.getParameter("keyword");
		if (word == null) {
			word = "";
		}

		String categoryId = request.getParameter("categoryid");
		if (categoryId == null) {
			categoryId = "0";
		}

		// 2.MODEL
		ItemModel im = new ItemModel();
		ArrayList<Item> items = im.findByKeywordAndCategory(getServletContext(), word, Integer.parseInt(categoryId));

		// 3.VIEW
		request.setAttribute("items", items);
		request.setAttribute("word", word);
		request.setAttribute("categoryid", Integer.parseInt(categoryId));
		request.getRequestDispatcher("/itemListView.jsp").forward(request, response);
	}

}
