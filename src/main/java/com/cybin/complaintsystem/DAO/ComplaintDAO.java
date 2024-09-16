package com.cybin.complaintsystem.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cybin.complaintsystem.entities.Complaint;

public class ComplaintDAO {

	private Session session; // hibernate session

	public ComplaintDAO(SessionFactory sessionFact) {
		session = sessionFact.openSession();
	}

	public void insertComplaint(Complaint complaint) {
		session.save(complaint); // saves into the session
		session.flush(); // flushes the session into the database
	}

	public List<Complaint> getAllComplaints() {
		List<Complaint> list = new ArrayList<Complaint>();

		Query q = session.createQuery("from Complaint"); // from (EntityName)
		list = q.list();

		return list;
	}

	public void deleteComplaint(Integer id_complaint) {
		Query q = session.createQuery("delete from Complaint where id = :idcomp").setParameter("idcomp", id_complaint);
		q.executeUpdate();
	}
	
	public void close() {
		session.close();
	}
}
