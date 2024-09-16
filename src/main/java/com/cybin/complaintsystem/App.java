package com.cybin.complaintsystem;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cybin.complaintsystem.DAO.ComplaintDAO;
import com.cybin.complaintsystem.entities.Complaint;

@Controller
@PropertySource ({"classpath:admin-properties.properties"}) 
public class App 
{
	@Autowired
	private Environment env;
	
	@Autowired
	SessionFactory sessionFact;
	
	@RequestMapping("/fileComplaint")
	public String fileComplaint() {
		
//		ComplaintDAO complaintDao = new ComplaintDAO(sessionFact);
//		complaintDao.insertComplaint(complaint);
//		for(Complaint compl : complaintDao.getAllComplaints()) {
//			System.out.println("COMPLAINT: " + compl.getSenderName());
//		}
		return "fileComplaint";
	}
	
	@RequestMapping("/submitComplaint")
	public String cubmitComplaint(
			@RequestParam("complaint") String complaintMsg,
			@RequestParam("email") String complaintEmail,
			@RequestParam("name") String complaintName){
		
		Complaint compl = new Complaint(complaintMsg, complaintEmail, complaintName);
		
		ComplaintDAO dao = new ComplaintDAO(sessionFact);
		
		System.out.println("MSG : " + compl.getMessage() + " EMAIL : " + compl.getSenderEmail());
		
		dao.insertComplaint(compl);
		
		System.out.println("COMPLAINT INSERTED");
		
		return "submitComplaint";
	}
	
	// enter admin password and take us to showComplaints but throgh the Post method which will show us the complaints
	@RequestMapping(name="/showComplaints", method = RequestMethod.GET)
	public String showComplaints() {
		return "showPasswordPage";
	}
	
	@RequestMapping(name="/showComplaints", method = RequestMethod.POST)
	public ModelAndView showComplaintsPost(@RequestParam("username") String username,
			@RequestParam("userpwd") String userpwd, ModelAndView modelandview) {
		if(userpwd.equals(env.getProperty("admin.password"))) {
			ComplaintDAO dao = new ComplaintDAO(sessionFact);
			List<Complaint> list = new ArrayList<Complaint>();
			list = dao.getAllComplaints();
			modelandview.addObject("complaints", list);
			modelandview.setViewName("showComplaints");
			return modelandview;
		} else {
			modelandview.setViewName("showPasswordPage");
			return modelandview;
		}
	}
}
