package com.journalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_journalapp")
public class ConfigJournalapp {
	@Id
	   @Column(name = "`keys`")
	    private String keys;

	    @Column(name = "`values`")
	    private String values;
	public String getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public ConfigJournalapp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConfigJournalapp(String keys, String values) {
		super();
		this.keys = keys;
		this.values = values;
	}

}
