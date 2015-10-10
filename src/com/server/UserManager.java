package com.server;

import java.sql.SQLException;

import com.function.GetConnectionToSQL;
import com.model.User;

public class UserManager {
	public int addUser(User user){
		GetConnectionToSQL MySQLConnect=new GetConnectionToSQL("192.243.118.141","3306","test","test","123456789");//����һ���Զ�������ݿ����Ӷ���
		MySQLConnect.setSQLtype(1);//ѡ��mysql
		MySQLConnect.registerDrver();//��ȡmysql����
		if(!MySQLConnect.getConnection())
		{
			return -1;//����ʧ�� ����-1
		}
		String selectCondition="insert into userlist values(?,?);";//�����µ��û������
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
			return e.getErrorCode(); //�������󣬷��ش�����
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
			if(e.getErrorCode()==1062)//�����ظ� ���û����Ѿ�����
				return -2;//�û������ڣ�����-2
			return e.getErrorCode(); //�������󣬷��ش�����
		}
		return 1;
	}
}
