import org.hibernate.Session;
import org.hibernate.Transaction;

import com.baykus.butget.models.Role;
import com.baykus.butget.models.Users;
import com.baykus.butget.models.income;
import com.baykus.butget.utils.MyHBUtil;
import com.baykus.butget.utils.dao.UsersDBDao;
import com.baykus.butget.utils.ui.Login;

public class Runner {
	public static void main(String[] args) {

	
		
//		UsersDBDao dao = new UsersDBDao();
//		Users temp = new Users();
//		temp.setUserName("Bayram");
//		temp.setEmail("byrm@gmail.com");
//		temp.setPassword("123");
//		temp.setUserRole(Role.MANAGER);
//		dao.save(temp);
		
		Login lg = new Login();
		lg.setVisible(true);
		
		
	} 
}
 