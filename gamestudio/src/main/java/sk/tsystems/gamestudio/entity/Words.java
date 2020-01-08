package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Words {
	
	@Id
	@GeneratedValue
	private int ident;
	
	private String slovak;
	private String english;
	
	
	public Words(String slovak, String english) {
		super();
		this.slovak = slovak;
		this.english = english;
	}


	public String getSlovak() {
		return slovak;
	}


	public void setSlovak(String slovak) {
		this.slovak = slovak;
	}


	public String getEnglish() {
		return english;
	}


	public void setEnglish(String english) {
		this.english = english;
	}
	

	
}
