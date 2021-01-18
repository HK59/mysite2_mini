package com.javaex.vo;

public class GuestVo {

	//field
		private String no;
		private String id;
		private String password;
		private String name;
		private String gender;
		
	
	//constructor
		public GuestVo(int no2, String password2) {
			super();
		}



	public GuestVo(String no, String id, String password, String name, String gender) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
		
		
	//getter-setter
	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
// general-method



	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
				+ "]";
	}



	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}




	}
	
	
	

