package com.joe.domian.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Role implements Comparable<Role>{
	private Integer id;
	private String roleName;
	private Date createTime;
	private Date updateTime;
	private String description;
	private String status;

	@Override
	public int compareTo(Role o) {
		if(id == o.getId()){
			return 0;
		}else if(id > o.getId()){
			return 1;
		}else{
			return -1;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Role){
			if(this.id == ((Role)obj).getId()){
				return true;
			}
		}
		return false;
	}

}