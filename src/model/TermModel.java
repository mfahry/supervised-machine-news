/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfahry
 */
public class TermModel {
    Database db;
    ResultSet resultSet;
    PreparedStatement statement;
    
    public TermModel() {
        db = new Database("root", "");
    }
    
    public int insertTerm(String term){
        try {
            statement = db.getCon().prepareStatement(
                "INSERT INTO terms values (?)"
            );
            
            statement.setString(1, term);
            
            return statement.executeUpdate(); // jika insert data berhasil 
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(CorpusModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 2; // jika tidak terjadi apa - apa
    }
    
    public ResultSet selectTerm(){
        try {
            statement = db.getCon().prepareStatement("SELECT * FROM terms order by 1");
            resultSet = statement.executeQuery();
            return resultSet;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(StopWordsIndonesianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
