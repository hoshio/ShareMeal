package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;


import play.db.ebean.*;
import play.data.validation.*;

import com.avaje.ebean.annotation.*;
import play.data.validation.Constraints.*;

@Entity
public class Tweet extends Model {

	@Id
	public Long id;

	@Required
	public String todo_name;
	
	public String todo_detail;
	
	public String author;
	
	@CreatedTimestamp
	public Date postdate;

	
	public static Finder<Long, Tweet> find = new Finder<Long, Tweet>(Long.class, Tweet.class);

}