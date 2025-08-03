package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ContactDao;
import model.dao.ContactItemDao;
import model.entity.Contact;
import model.entity.ContactItem;
import util.Validator;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//お問い合わせ項目を取得
		List<ContactItem> contactItem = new ContactItemDao().findAll();
		request.setAttribute("contactItem", contactItem);
		
		request
		.getRequestDispatcher("/WEB-INF/jsp/contact.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String item = request.getParameter("contactItem");
		String name = request.getParameter("name");
		String prefecture = request.getParameter("prefecture");
		String mail = request.getParameter("mail");
		String message = request.getParameter("message");
		
		//入力値チェック
		int vItem = item == null ? 0 : Integer.parseInt(item);
		Contact contact = new Contact(vItem, name, prefecture, mail, message);
		contact.setItem(item);
		List<String> errors = Validator.contactValidate(contact);
		
		//エラーの場合
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		    request.setAttribute("contact", contact);
		  //お問い合わせ項目を取得
			List<ContactItem> contactItem = new ContactItemDao().findAll();
			request.setAttribute("contactItem", contactItem);
			
		    request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
		    return;
		}
		
		
		//問い合わせの保存処理
		new ContactDao().add(contact);

		request.getRequestDispatcher("/WEB-INF/jsp/contactComplete.jsp").forward(request, response);
	}

}
