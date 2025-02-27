package cs.service;

import java.util.List;

import cs.entity.UserBean;

public class
LoginService {
	public static String userName = null;
	private LoginService() {
	}
	
	public static LoginService service = new LoginService();
	
	public UserBean login(String userName, String pwd, Integer role) {
//		select * from user where pwd='123456' and user_name='admin' and role=1
		String sql = "select * from user where pwd=? and user_name=? and role=?";
		//²ÎÊýË³ÐòÓësqlÓï¾äÖÐÎÊºÅµÄË³ÐòÒ»ÖÂ
		Object[] params = new Object[] {pwd, userName, role};
		List<UserBean> list = DbService.dbUtil.genericQuery(sql, params, UserBean.class);
		//List<StudentBean> list = DbService.dbUtil.genericQuery(sql, params, StudentBean.class);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		//µÇÂ¼Ê§°Ü
		return null;
	}
	
	public Integer updatePwd(String userName, String oPassword,String nPassword) {
		String sql = " update user set pwd = ? where user_name = ? and pwd = ?";
		Object[] params = new Object[] {nPassword, userName, oPassword};
		Integer rtn = DbService.dbUtil.genericDML(sql, params);
		return rtn;
	}
	
	public static void main(String[] args) {
		UserBean userBean = LoginService.service.login("admin", "123456", 1);
		if (userBean == null) {
			System.out.println("µÇÂ¼Ê§°Ü");
		}else {
			System.out.println(userBean.getUserName() + ", " + userBean.getPwd());
		}

//		System.out.println(LoginService.service.updatePwd("219970906" ,"123456" , "123"));
	}
}
