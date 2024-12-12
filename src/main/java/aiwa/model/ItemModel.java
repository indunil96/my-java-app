package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import aiwa.entity.Category;
import aiwa.entity.Item;

public class ItemModel {

	public ArrayList<Item> findByKeywordAndCategory(ServletContext context, String word, int categoryId) {
		ArrayList<Item> result = new ArrayList<>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + context.getRealPath("WEB-INF/webapp14.db"));

			String sql = "select "
					+ "* "
					+ "from "
					+ "items i "
					+ "inner join "
					+ "categories c "
					+ "on "
					+ "i.categoryid = c.categoryid "
					+ "where "
					+ "(itemname like ? or detail like ? )";

			if (categoryId > 0) {
				sql += "and c.categoryid = ? ";
			}

			sql += "order by itemid";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + word + "%");
			stmt.setString(2, "%" + word + "%");

			if (categoryId > 0) {
				stmt.setInt(3, categoryId);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setImage(rs.getString("image"));
				item.setDetail(rs.getString("detail"));

				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryid"));
				category.setCategoryName(rs.getString("categoryname"));

				item.setCategory(category);

				result.add(item);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Item> findAll(ServletContext context) {
		ArrayList<Item> result = new ArrayList<>();

		try {

			Class.forName("org.sqlite.JDBC");

			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + context.getRealPath("WEB-INF/webapp14.db"));

			String sql = "select * from items i inner join categories c on i.categoryid = c.categoryid order by itemid";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getInt("itemid"));
				item.setItemName(rs.getString("itemname"));
				item.setPrice(rs.getInt("price"));
				item.setImage(rs.getString("image"));
				item.setDetail(rs.getString("detail"));

				Category category = new Category();
				category.setCategoryId(rs.getInt("categoryid"));
				category.setCategoryName(rs.getString("categoryname"));

				item.setCategory(category);

				result.add(item);
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
