package camiwilliams.MavenDemo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;

@Controller
public class MavenDemoController {
	
	private static String APP_ID = "1cJCqL0GyMOsI9V7_ZJWEfuoOeYYJtMdb3PmgkQm";
	private static String APP_SECRET = "M2pYGnT5Eeq-w_fvtbXooJSipvdB4S-9jXm-flLx"; 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView handleIndexGET(Model map) {	
		String str = "Cami clicked on a GET!";
		return new ModelAndView("index", "message", str);
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(Model map, @RequestParam("pic") MultipartFile file) throws ClarifaiException, IOException {	
		String message = "";
		String image = "";
		
		try {
			if(!file.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				sb.append("data:image/png;base64,");
				sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(file.getBytes(), false)));
				ClarifaiClient clarifai = new ClarifaiClient(APP_ID, APP_SECRET);
				List<RecognitionResult> results =
				    clarifai.recognize(new RecognitionRequest(file.getBytes()));

				for (Tag tag : results.get(0).getTags()) {
				  message += tag.getName() + " ";
				}
				image = sb.toString();
				
			}
		} catch (NullPointerException e) {
			message = "There was an error!";
		}
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("message", message);
		model.put("image", image);
		return new ModelAndView("upload", "model", model);
	}
}
