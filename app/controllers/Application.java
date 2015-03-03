package controllers;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import java.util.List;
import models.*;
import play.data.*;
import static play.data.Form.*; 
import play.mvc.*;
import views.html.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import play.mvc.Result;

public class Application extends Controller {
	
	public static class SampleForm {
		public String message;
	}

	public static Result index() {
		List<Tweet> datas = Tweet.find.orderBy("postdate desc").findList();
		return ok(index.render("何か書いて", datas));
	}
	
	public static Result ajax(){
//		String input = request().body().asFormUrlEncoded().get("input")[0];
//		Tweet t = Tweet.findByTweet(input);
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		String str = "<xml><root><err>ERROR!</err></root>";
//		Document doc = null;
//		try{
//			doc = factory.newDocumentBuilder().newDocument();
//			Element root = doc.createElement("data");
//			Element el = doc.createElement("tweet");
//			el.appendChild(doc.createTextNode(t.tweet));
//			root.appendChild(el);
//			doc.appendChild(root);
//			TransformerFactory tfactory = TransformerFactory.newInstance();
//			StringWriter writer = new StringWriter();
//			StreamResult stream = new StreamResult(writer);
//			Transformer trans = tfactory.newTransformer();
//			trans.transform(new DOMSource(doc.getDocumentElement()), stream);
//		}catch(ParserConfigurationException e){
//			e.printStackTrace();
//		}catch(TransformerConfigurationException e){
//			e.printStackTrace();
//		}catch(TransformerException e){
//			e.printStackTrace();
//		}
//		
//		if(doc == null){
//			return badRequest(str);
//		}else{
//			return ok(str);
//		}
		
		String input = request().body().asFormUrlEncoded().get("input")[0];
		ObjectNode result = Json.newObject();
		if(input == null){
			result.put("tweet", "BAD");
			result.put("username", "Can't get sending data,,,");
			return badRequest(result);
		}else{
			result.put("tweet", "OK");
			result.put("username", "input");
			return ok(result);
		}
	}
	
//	public static Result send(){
//		Form<SampleForm> f = form(SampleForm.class).bindFromRequest();
//		if(!f.hasErrors()){
//			SampleForm data = f.get();
//			String msg = "you typed: " + data.message;
//			List<Tweet> datas = Tweet.find.all();
//			return ok(index.render(msg, f, datas));
//		}else{
//			List<Tweet> datas = Tweet.find.all();
//			return badRequest(index.render("ERROR", form(SampleForm.class), datas));
//		}
//	}
	
	public static Result add(){
		Form<Tweet> f = new Form(Tweet.class);
		return ok(add.render("投稿フォーム", f));
	}
	
	public static Result create(){
		Form<Tweet> f = new Form(Tweet.class).bindFromRequest();
		if(!f.hasErrors()){
			Tweet data = f.get();
			data.save();
			List<Tweet> datas = Tweet.find.orderBy("postdate desc").findList();
			return ok(index.render("tweet完了", datas));
		}else{
			List<Tweet> datas = Tweet.find.orderBy("postdate desc").findList();
			return badRequest(index.render("ERROR", datas));
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
