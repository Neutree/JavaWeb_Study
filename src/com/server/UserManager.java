package com.server;

import java.sql.SQLException;

import com.function.GetConnectionToSQL;
import com.model.User;

public class UserManager {
	public int addUser(User user){
		GetConnectionToSQL MySQLConnect=new GetConnectionToSQL("192.243.118.141","3306","test","test","123456789");//建立一个自定义的数据库连接对象
		MySQLConnect.setSQLtype(1);//选择mysql
		MySQLConnect.registerDrver();//获取mysql驱动
		if(!MySQLConnect.getConnection())
		{
			return -1;//连接失败 返回-1
		}
		String selectCondition="insert into userlist values(?,?);";//插入新的用户名语句
		try {
			MySQLConnect.prepareStatement(selectCondition);
			MySQLConnect.getSql().setString(1, user.getName());
			MySQLConnect.getSql().setString(2, user.getPassWord());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			try {
				MySQLConnect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
			}
			return e.getErrorCode(); //其它错误，返回错误码
		}
		try {
			MySQLConnect.getSql().executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				MySQLConnect.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
			//	e1.printStackTrace();
			}
			if(e.getErrorCode()==1062)//主键重复 即用户名已经存在
				return -2;//用户名存在，返回-2
			return e.getErrorCode(); //其它错误，返回错误码
		}
		return 1;
	}
}
