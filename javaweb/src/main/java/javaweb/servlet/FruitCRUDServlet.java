package javaweb.servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * 水果盤 CRUD(Create Read Update, Delete) 
 * 新增水果路徑: /fruit/create?name=apple or /fruit/create?name=banana etc...
 * 查詢水果路徑: /fruit/read
 * 修改水果路徑: /fruit/update?name=apple&newName=pineApple
 * 刪除水果路徑: /fruit/delete?name=banana
 * */
@WebServlet("/fruit/*")
public class FruitCRUDServlet extends HttpServlet {
	
	private List<String> fruits = new CopyOnWriteArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String newName = req.getParameter("newName");
		
		String pathInfo = req.getPathInfo();
		switch (pathInfo) {
			case "/create" :
				fruits.add(name);
				resp.getWriter().print("Create fruit OK");
				break;
			case "/read" :
				resp.getWriter().print(fruits);
				break;
			case "/update" :
				int idx = fruits.indexOf(name); // 找到修改水果的 index
				fruits.set(idx, newName); // 修改
				resp.getWriter().print("Update fruit OK");
				break;
			case "/delete" :
				fruits.remove(name);
				resp.getWriter().print("Delete fruit OK");
				break;	
		}
		
	}
	
}
