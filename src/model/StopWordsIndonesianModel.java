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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfahry
 */
public class StopWordsIndonesianModel {
    Database db;
    ResultSet resultSet;
    PreparedStatement statement;
    
    public StopWordsIndonesianModel() {
        db = new Database("root", "");
    }
    
    public ResultSet selectAll(){
        try {
            statement = db.getCon().prepareStatement("SELECT * FROM stop_words_indonesian");
            resultSet = statement.executeQuery();
            return resultSet;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(StopWordsIndonesianModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
}
