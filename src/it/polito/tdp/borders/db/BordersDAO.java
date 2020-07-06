package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Adiacenza;
import it.polito.tdp.borders.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
	public List<Country> loadAllCountries() {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public static void main(String[] args) {
		List<Country> list ;
		BordersDAO dao = new BordersDAO() ;
		list = dao.loadAllCountries() ;
		for(Country c: list) {
			System.out.println(c);
		}
	}

	public Collection<Adiacenza> getAdiacenze(int anno) {

		String sql = "SELECT co1.StateAbb abb1,co1.CCode id1,co1.StateNme nome1,co2.StateAbb abb2,co2.CCode id2,co2.StateNme nome2 " + 
				"FROM contiguity c1,country co1,country co2 " + 
				"WHERE c1.year<= ? AND c1.state1no = co1.CCode AND c1.state2no=co2.CCode AND c1.conttype= 1 AND c1.state1no> c1.state2no" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery() ;
			
			List<Adiacenza> list = new LinkedList<Adiacenza>() ;
			
			while( rs.next() ) {
				
				
				Country c1=new Country(rs.getInt("id1"),rs.getString("abb1"),rs.getString("nome1"));
				Country c2=new Country(rs.getInt("id2"),rs.getString("abb2"),rs.getString("nome2"));
				Adiacenza a=new Adiacenza(c1,c2);
				
				list.add(a) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	
}
