package controllers;

import java.util.List;
import models.*;
import play.data.*;
import static play.data.Form.*; 
import play.mvc.*;
import views.html.*;


public class Application extends Controller {
	
	public static class SampleForm {
		public String message;
	}

	public static Result index() {
		List<Tweet> datas = Tweet.find.all();
		return ok(index.render("何か書いて",new Form(SampleForm.class), datas));
	}
		
	public static Result send(){
		Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
		List<Tweet> datas = Tweet.find.all();
		if(!f.hasErrors()){
			SampleForm data = f.get();
			String msg = "you typed: " + data.message;
			return ok(index.render(msg, f, datas));
		}else{
			return badRequest(index.render("ERROR", form(SampleForm.class), datas));
		}
	}
	
	public static Result add(){
		Form<Tweet> f = new Form(Tweet.class);
		return ok(add.render("投稿フォーム", f));
	}
	
	public static Result create(){
		Form<Tweet> f = new Form(Tweet.class).bindFromRequest();
		if(!f.hasErrors()){
			Tweet data = f.get();
			data.save();
			return redirect("/");
		}else{
			return badRequest(add.render("ERROR", f));
		}
	}
	
	public static Result set(){
		Form<Tweet> f = new Form(Tweet.class);
		return ok(setid.render("IDを入力", f));
	}
	
	public static Result edit(){
		Form<Tweet> f = new Form(Tweet.class).bindFromRequest();
		if(!f.hasErrors()){
			Tweet obj = f.get();
			Long id = obj.id;
			obj = Tweet.find.byId(id);
			if(obj != null){
				f = new Form(Tweet.class).fill(obj);
				return ok(edit.render("ID=" + id + "の投稿を編集", f));
			}else{
				return ok(setid.render("ERROR:IDの投稿が見つかりません", f));
			}
		}else{
			return ok(setid.render("ERROR:投稿に問題があります", f));
		}
	}
	
	public static Result update(){
		Form<Tweet> f = new Form(Tweet.class).bindFromRequest();
		if(!f.hasErrors()){
			Tweet data = f.get();
			data.update();
			return ok(edit.render("update完了", f));
		}else{
			return ok(edit.render("ERRO:再度入力ください", f));
		}
	}


}
